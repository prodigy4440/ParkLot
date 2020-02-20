package com.fahdisa.pklot.ParkLot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryResponse <E>{

    private Status status;

    private String message;

    private E data;

    public enum Status{
        SUCCESS, FAILED
    }
}
