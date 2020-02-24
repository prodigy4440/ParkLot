package com.fahdisa.pklot.ParkLot.controller;

import com.fahdisa.pklot.ParkLot.models.Slot;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import com.fahdisa.pklot.ParkLot.request.EntryResponse;
import com.fahdisa.pklot.ParkLot.service.SlotService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = {"Slots"})
@RestController
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService){
        this.slotService = slotService;
    }

    @PostMapping("/slot")
    public ResponseEntity<?> createSlot(@Valid @RequestBody Slot slot){
        Optional<Slot> optionalSlot = slotService.createSlot(slot);
        if(!optionalSlot.isPresent()){
            return ResponseEntity.badRequest().body(new EntryResponse<>(EntryResponse.Status.FAILED,
                    "Invalid slot price id", null));
        }
        return ResponseEntity.ok(optionalSlot.get());
    }

    @PutMapping("/slot")
    public ResponseEntity<?> updateSlot(Slot slot){
        Optional<Slot> optionalSlot = slotService.updateSlot(slot);
        return ResponseEntity.ok(optionalSlot.get());
    }

    @DeleteMapping("/slot/{slotId}")
    public ResponseEntity<?> delete(@PathVariable("slotId") Long id){
        Optional<Slot> optionalSlot = slotService.delete(id);
        if(!optionalSlot.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/slots")
    public ResponseEntity<?> findAll(@PageableDefault(page = 0, size = 20)
                                         @SortDefault.SortDefaults({
                                                 @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                                                 @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                         }) Pageable pageable){
        Page<Slot> all = slotService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/slots/{slotType}")
    public ResponseEntity<?> findAll(@PathVariable("slotType") SlotType slotType,
                                     @RequestParam("isOccupied") Boolean isOccupied, @RequestParam("page") int page,
                                     @RequestParam("size") int size){
        List<Slot> slots = slotService.find(slotType, isOccupied, PageRequest.of(page, size));
        return ResponseEntity.ok(slots);
    }
}
