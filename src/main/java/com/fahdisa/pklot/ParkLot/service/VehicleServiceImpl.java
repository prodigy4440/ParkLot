package com.fahdisa.pklot.ParkLot.service;

import com.fahdisa.pklot.ParkLot.factory.PriceFactory;
import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Policy;
import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.VehicleEntry;
import com.fahdisa.pklot.ParkLot.repository.SlotRepository;
import com.fahdisa.pklot.ParkLot.repository.VehicleEntryRepository;
import com.fahdisa.pklot.ParkLot.request.EntryPayload;
import com.fahdisa.pklot.ParkLot.request.EntryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleEntryRepository vehicleEntryRepository;

    private final SlotRepository slotRepository;

    private final PriceFactory priceFactory;

    @Autowired
    public VehicleServiceImpl(VehicleEntryRepository vehicleEntryRepository,
                              SlotRepository slotRepository,
                              PriceFactory priceFactory){
        this.vehicleEntryRepository = vehicleEntryRepository;
        this.slotRepository = slotRepository;
        this.priceFactory = priceFactory;
    }

    @Transactional
    public EntryResponse<VehicleEntry> entry(EntryPayload entryPayload){

        List<VehicleEntry> loggedVehicles = vehicleEntryRepository.findByRegNoAndStatus(entryPayload.getRegNo(),
                VehicleEntry.Status.IN, PageRequest.of(0, 2, Sort.by("entryAt").descending()));

        if(!loggedVehicles.isEmpty()){
            return new EntryResponse<>(EntryResponse.Status.FAILED,
                    "Vehicle has already been assigned to a slot", null);
        }

        List<Slot> slots = slotRepository.findBySlotTypeAndOccupied(entryPayload.getSlotType(),
                false, PageRequest.of(0, 5));
        if(slots.isEmpty()){
            return new EntryResponse<>(EntryResponse.Status.FAILED,
                    "No parking space available", null);
        }

        Slot slot = slots.get(0);
        slot.setOccupied(true);
        slotRepository.save(slot);

        VehicleEntry vehicleEntry = new VehicleEntry();
        vehicleEntry.setColor(entryPayload.getColor());
        vehicleEntry.setEntryAt(Instant.now());
        vehicleEntry.setEntryID(UUID.randomUUID().toString().replaceAll("-","").substring(0, 19).toUpperCase());
        vehicleEntry.setRegNo(entryPayload.getRegNo());
        vehicleEntry.setSlot(slot.getId());
        vehicleEntry.setStatus(VehicleEntry.Status.IN);
        return new EntryResponse<>(EntryResponse.Status.SUCCESS, "Success",
                vehicleEntryRepository.save(vehicleEntry));
    }

    @Transactional
    public EntryResponse exit(String entryIdOrRegNo){
        List<VehicleEntry> vehicleEntryList = vehicleEntryRepository.findByRegNoOrEntryID(entryIdOrRegNo,
                entryIdOrRegNo, PageRequest.of(0, 2,
                        Sort.Direction.DESC, "entryAt"));
        if(vehicleEntryList.isEmpty()){
            return new EntryResponse(EntryResponse.Status.FAILED, "Entry not found", null);
        }
        VehicleEntry vehicleEntry = vehicleEntryList.get(0);
        if(vehicleEntry.getStatus() == VehicleEntry.Status.OUT){
            return new EntryResponse(EntryResponse.Status.FAILED, "Vehicle has already exited",
                    null);
        }
        vehicleEntry.setEntryAt(Instant.now());
        long between = ChronoUnit.HOURS.between(vehicleEntry.getExitAt(), vehicleEntry.getEntryAt());
        Slot slot = slotRepository.findById(vehicleEntry.getSlot()).get();
        slot.setOccupied(false);

        Policy policy = priceFactory.getPolicy(slot.getSlotType());
        BigDecimal cost = policy.getPrice(between);

        vehicleEntry.setCost(cost);
        vehicleEntry.setStatus(VehicleEntry.Status.OUT);

        slotRepository.save(slot);
        vehicleEntryRepository.save(vehicleEntry);

        return new EntryResponse(EntryResponse.Status.SUCCESS, "Success", vehicleEntry);
    }

    public List<VehicleEntry> findByEntryId(String entryID, PageRequest pageRequest){
        return vehicleEntryRepository.findByEntryID(entryID, pageRequest);
    }

    public List<VehicleEntry> findByRegNo(String regNo, PageRequest pageRequest){
        return vehicleEntryRepository.findByRegNo(regNo, pageRequest);
    }

    public Page<VehicleEntry> findAll(PageRequest pageRequest){
        return vehicleEntryRepository.findAll(pageRequest);
    }
}
