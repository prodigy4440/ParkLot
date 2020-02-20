package com.fahdisa.pklot.ParkLot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle_entry")
public class VehicleEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_id", length = 30, unique = true)
    private String entryID;

    @Column(name = "reg_no", length = 60, unique = true)
    private String regNo;

    @Column(name = "color", length = 15)
    private String color;

    @Column(name = "slot_id")
    private Long slot;

    @Column(name = "cost")
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private Status status;

    @Column(name = "entry_at")
    private Instant entryAt;

    @Column(name = "exit_at")
    private Instant exitAt;

    public enum Status{
        IN, OUT;
    }
}
