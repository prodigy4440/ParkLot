package com.fahdisa.pklot.ParkLot.service;

import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SlotService {

    Optional<Slot> createSlot(Slot slot);

    Optional<Slot> updateSlot(Slot slot);

    Optional<Slot> delete(Long id);

    Page<Slot> findAll(Pageable pageable);

    List<Slot> find(SlotType slotType, boolean isOccupied, Pageable pageable);;
}
