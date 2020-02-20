package com.fahdisa.pklot.ParkLot.service;

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

public interface VehicleService {

    EntryResponse<VehicleEntry> entry(EntryPayload entryPayload);

    EntryResponse exit(String entryIdOrRegNo);

    List<VehicleEntry> findByEntryId(String entryID, PageRequest pageRequest);

    List<VehicleEntry> findByRegNo(String regNo, PageRequest pageRequest);

    Page<VehicleEntry> findAll(PageRequest pageRequest);
}
