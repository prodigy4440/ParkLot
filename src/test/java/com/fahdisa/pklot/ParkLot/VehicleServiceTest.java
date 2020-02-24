package com.fahdisa.pklot.ParkLot;

import com.fahdisa.pklot.ParkLot.config.PktLotConfiguration;
import com.fahdisa.pklot.ParkLot.factory.PriceFactory;
import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import com.fahdisa.pklot.ParkLot.models.VehicleEntry;
import com.fahdisa.pklot.ParkLot.repository.SlotRepository;
import com.fahdisa.pklot.ParkLot.repository.VehicleEntryRepository;
import com.fahdisa.pklot.ParkLot.request.EntryPayload;
import com.fahdisa.pklot.ParkLot.request.EntryResponse;
import com.fahdisa.pklot.ParkLot.service.SlotService;
import com.fahdisa.pklot.ParkLot.service.SlotServiceImpl;
import com.fahdisa.pklot.ParkLot.service.VehicleService;
import com.fahdisa.pklot.ParkLot.service.VehicleServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class VehicleServiceTest {

    @Mock
    private VehicleEntryRepository vehicleEntryRepository;

    @Mock
    private SlotRepository slotRepository;

    private VehicleService vehicleService;

    private SlotService slotService;

    @BeforeEach
    public void initUseCase(){

        PktLotConfiguration pktLotConfiguration = new PktLotConfiguration();
        pktLotConfiguration.setElectric20kFixed(new BigDecimal(20));
        pktLotConfiguration.setElectric20kRate(new BigDecimal(200));
        pktLotConfiguration.setElectric50kFixed(new BigDecimal(200));
        pktLotConfiguration.setElectric50kRate(new BigDecimal(200));
        pktLotConfiguration.setSedanRate(new BigDecimal(200));

        PriceFactory priceFactory = new PriceFactory(pktLotConfiguration);

        vehicleEntryRepository = Mockito.mock(VehicleEntryRepository.class);
        slotRepository = Mockito.mock(SlotRepository.class);
        vehicleService = new VehicleServiceImpl(vehicleEntryRepository, slotRepository, priceFactory);
        slotService = new SlotServiceImpl(slotRepository);

        Slot slot = new Slot();
        slot.setName("Sedan 1");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        Slot slot1 = new Slot();
        slot1.setName("Sedan 2");
        slot1.setOccupied(false);
        slot1.setSlotType(SlotType.GASOLINE_SEDAN);
        slot1.setId(2L);
        slot1.setCreatedOn(Instant.now());

        List<Slot> slots = Arrays.asList(slot, slot1);
        Mockito.when(slotRepository.findBySlotTypeAndOccupied(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 5))).thenReturn(slots);

        Mockito.when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        VehicleEntry vehicleEntry = new VehicleEntry();
        vehicleEntry.setColor("blue");
        vehicleEntry.setEntryAt(Instant.now());
        vehicleEntry.setExitAt(Instant.now().plus(3, ChronoUnit.HOURS));
        vehicleEntry.setEntryID(UUID.randomUUID().toString().replaceAll("-","").substring(0, 19).toUpperCase());
        vehicleEntry.setRegNo("123456");
        vehicleEntry.setSlot(1L);
        vehicleEntry.setStatus(VehicleEntry.Status.IN);

        Mockito.when(vehicleEntryRepository.save(vehicleEntry)).thenReturn(vehicleEntry);

        Mockito.when(vehicleEntryRepository.findByRegNoOrEntryID("123456",
                "123456", PageRequest.of(0, 2,
                        Sort.Direction.DESC, "entryAt"))).thenReturn(Arrays.asList(vehicleEntry));
    }

    @Test
    public void vehicleEntry(){
        EntryPayload payload = new EntryPayload("123456",SlotType.GASOLINE_SEDAN,"blue");
        EntryResponse<VehicleEntry> entryResponse = vehicleService.entry(payload);
        Assertions.assertThat(entryResponse.getStatus()).isEqualTo(EntryResponse.Status.SUCCESS);
    }

    @Test
    public void vehicleExit(){
        Slot slot = new Slot();
        slot.setName("Sedan 1");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        Optional<Slot> updatedSlot = slotService.updateSlot(slot);

        EntryResponse entryResponse = vehicleService.exit("123456");

        Assertions.assertThat(entryResponse.getStatus()).isEqualTo(EntryResponse.Status.SUCCESS);
    }

    @Test
    public void deleteSlot(){
        Slot slot = new Slot();
        slot.setName("Toyota Camry");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(2L);
        slot.setCreatedOn(Instant.now());

        Mockito.when(slotRepository.findById(2L)).thenReturn(Optional.of(slot));
        Optional<Slot> optionalSlot = slotService.delete(2L);
        Assertions.assertThat(optionalSlot.get().getId()).isEqualTo(2L);
    }

    @Test
    public void find(){

        Slot slot = new Slot();
        slot.setName("Sedan 1");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Slot slot1 = new Slot();
        slot1.setName("Sedan 2");
        slot1.setOccupied(false);
        slot1.setSlotType(SlotType.GASOLINE_SEDAN);
        slot1.setId(2L);
        slot1.setCreatedOn(Instant.now());

        List<Slot> slots = Arrays.asList(slot, slot1);
        Mockito.when(slotRepository.findBySlotTypeAndOccupied(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 2))).thenReturn(slots);

        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        List<Slot> result = slotService.find(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 2));
        Assertions.assertThat(result).isEqualTo(slots);
    }
}
