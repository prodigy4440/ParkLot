package com.fahdisa.pklot.ParkLot;

import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import com.fahdisa.pklot.ParkLot.repository.SlotRepository;
import com.fahdisa.pklot.ParkLot.service.SlotService;
import com.fahdisa.pklot.ParkLot.service.SlotServiceImpl;
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

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SlotServiceTest {

    @Mock
    private SlotRepository slotRepository;

    private SlotService slotService;

    @BeforeEach
    public void initUseCase(){

        slotRepository = Mockito.mock(SlotRepository.class);
        slotService = new SlotServiceImpl(slotRepository);

        Slot slot = new Slot();
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setOccupied(false);
        slot.setName("Sedan 1");
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Slot slot1 = new Slot();
        slot1.setName("Sedan 2");
        slot1.setOccupied(false);
        slot1.setSlotType(SlotType.GASOLINE_SEDAN);
        slot1.setId(2L);
        slot1.setCreatedOn(Instant.now());

        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        Mockito.when(slotRepository.save(slot1)).thenReturn(slot1);

        Mockito.when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        List<Slot> slots = Arrays.asList(slot, slot1);
        Mockito.when(slotRepository.findBySlotTypeAndOccupied(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 2))).thenReturn(slots);
    }

    @Test
    public void creatSlot(){
        Slot slot = new Slot();
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setOccupied(false);
        slot.setName("Sedan 1");
        slot.setCreatedOn(Instant.now());


        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        Optional<Slot> createdSlot = slotService.createSlot(slot);
        Assertions.assertThat(createdSlot.get()).isNotNull();
    }

    @Test
    public void updateSlot(){
        Slot slot = new Slot();
        slot.setName("Sedan 1");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Mockito.when(slotRepository.save(slot)).thenReturn(slot);

        Optional<Slot> updatedSlot = slotService.updateSlot(slot);

        Assertions.assertThat(updatedSlot.get().getSlotType()).isEqualTo(SlotType.GASOLINE_SEDAN);
    }

    @Test
    public void deleteSlot(){
        Optional<Slot> optionalSlot = slotService.delete(1L);
        Assertions.assertThat(optionalSlot.get().getId()).isEqualTo(1L);
    }

    @Test
    public void find(){

        Slot slot = new Slot();
        slot.setName("Toyota Corola");
        slot.setOccupied(false);
        slot.setSlotType(SlotType.GASOLINE_SEDAN);
        slot.setId(1L);
        slot.setCreatedOn(Instant.now());

        Slot slot1 = new Slot();
        slot1.setName("Toyota Camry");
        slot1.setOccupied(false);
        slot1.setSlotType(SlotType.GASOLINE_SEDAN);
        slot1.setId(2L);
        slot1.setCreatedOn(Instant.now());

        List<Slot> slots = Arrays.asList(slot, slot1);
        Mockito.when(slotRepository.findBySlotTypeAndOccupied(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 2))).thenReturn(slots);

        List<Slot> result = slotService.find(SlotType.GASOLINE_SEDAN,
                false, PageRequest.of(0, 2));
        Assertions.assertThat(result).isEqualTo(slots);
    }
}
