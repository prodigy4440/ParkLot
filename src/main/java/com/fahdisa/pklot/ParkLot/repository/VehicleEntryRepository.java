package com.fahdisa.pklot.ParkLot.repository;

import com.fahdisa.pklot.ParkLot.models.VehicleEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VehicleEntryRepository extends PagingAndSortingRepository<VehicleEntry, Long> {

    Page<VehicleEntry> findAll(Pageable pageable);

    List<VehicleEntry> findByRegNo(String regNo, Pageable pageable);

    List<VehicleEntry> findByRegNoAndStatus(String regNo, VehicleEntry.Status status, Pageable pageable);

    List<VehicleEntry> findByEntryID(String entriID, Pageable pageable);

    List<VehicleEntry> findByRegNoOrEntryID(String regNo, String entryID, Pageable pageable);
}
