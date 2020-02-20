package com.fahdisa.pklot.ParkLot.service;

import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import com.fahdisa.pklot.ParkLot.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SlotServiceImpl implements SlotService{

    private final SlotRepository slotRepository;

    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository){
        this.slotRepository = slotRepository;
    }

    public Optional<Slot> createSlot(Slot slot){
        slot.setCreatedOn(Instant.now());
        slot.setId(null);
        slot.setOccupied(false);
        if(Objects.isNull(slot.getSlotType())){
            slot.setSlotType(SlotType.GASOLINE_SEDAN);
        }
        return Optional.of(slotRepository.save(slot));
    }

    public Optional<Slot> updateSlot(Slot slot){
        return Optional.of(slotRepository.save(slot));
    }

    public Optional<Slot> delete(Long id){
        Optional<Slot> optionalSlot = slotRepository.findById(id);
        if(!optionalSlot.isPresent()){
            return Optional.empty();
        }
        slotRepository.deleteById(id);
        return optionalSlot;
    }

    public Page<Slot> findAll(Pageable pageable){
        return slotRepository.findAll(pageable);
    }

    public List<Slot> find(SlotType slotType, boolean isOccupied, Pageable pageable){
        return slotRepository.findBySlotTypeAndOccupied(slotType, isOccupied, pageable);
    }

}
