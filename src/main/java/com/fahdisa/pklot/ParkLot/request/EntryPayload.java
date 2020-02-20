package com.fahdisa.pklot.ParkLot.request;

import com.fahdisa.pklot.ParkLot.models.SlotType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntryPayload {

    private String regNo;

    private SlotType slotType;

    private String color;
}
