package com.fahdisa.pklot.ParkLot.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Configuration
public class PktLotConfiguration {

    @Value("${app.sedanRate:150}")
    private BigDecimal sedanRate;

    @Value("${app.electric20k.rate:200}")
    private BigDecimal electric20kRate;

    @Value("${app.electric20k.fixed:250}")
    private BigDecimal electric20kFixed;

    @Value("${app.electric50k.rate:300}")
    private BigDecimal electric50kRate;

    @Value("${app.electric50k.fixed:500}")
    private BigDecimal electric50kFixed;
}
