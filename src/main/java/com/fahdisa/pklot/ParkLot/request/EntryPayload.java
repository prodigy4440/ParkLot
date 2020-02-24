package com.fahdisa.pklot.ParkLot.request;

import com.fahdisa.pklot.ParkLot.models.SlotType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryPayload {

    private String regNo;

    private SlotType slotType;

    private String color;
}
