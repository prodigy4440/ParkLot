package com.fahdisa.pklot.ParkLot.controller;

import com.fahdisa.pklot.ParkLot.request.EntryPayload;
import com.fahdisa.pklot.ParkLot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("entry")
    public ResponseEntity<?> entry(@Valid @RequestBody EntryPayload entryPayload){
        return ResponseEntity.ok(vehicleService.entry(entryPayload));
    }

    @PostMapping("/exit")
    public ResponseEntity<?>  exit(@RequestParam("entryId") String entryIdOrRegNo){
        return ResponseEntity.ok(vehicleService.exit(entryIdOrRegNo));
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<?>  findByEntryId(@PathVariable("entryId") String entryID, PageRequest pageRequest){
        return ResponseEntity.ok(vehicleService.findByEntryId(entryID, pageRequest));
    }

    @GetMapping("/entry")
    public ResponseEntity<?>  findByRegNo(@RequestParam("regNo") String regNo, PageRequest pageRequest){
        return ResponseEntity.ok(vehicleService.findByRegNo(regNo, pageRequest));
    }

    @GetMapping("/entries")
    public ResponseEntity<?>  findAll(PageRequest pageRequest){
        return ResponseEntity.ok(vehicleService.findAll(pageRequest));
    }
}
