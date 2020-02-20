package com.fahdisa.pklot.ParkLot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "slot")
public class Slot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "slot_type", length = 20, unique = true)
    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    @Column(name = "occupied")
    private Boolean occupied;

    private Instant createdOn;
}
