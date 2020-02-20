package com.fahdisa.pklot.ParkLot.repository;

import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SlotRepository extends PagingAndSortingRepository<Slot, Long> {

    List<Slot> findByName(String name, Pageable pageable);

    List<Slot> findBySlotType(SlotType slotType, Pageable pageable);

    List<Slot> findBySlotTypeAndOccupied(SlotType slotType, boolean occupied, Pageable pageable);

    Page<Slot> findAll(Pageable pageable);
}
