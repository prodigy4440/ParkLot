package com.fahdisa.pklot.ParkLot.factory;

import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Policy;

import java.math.BigDecimal;

public class SedanPolicy implements Policy {

    private BigDecimal hourPrice;

    public SedanPolicy(BigDecimal hourPrice){
        this.hourPrice = hourPrice;
    }

    @Override
    public BigDecimal getPrice(long numberOfHour) {
        return hourPrice.multiply(new BigDecimal(numberOfHour));
    }
}
