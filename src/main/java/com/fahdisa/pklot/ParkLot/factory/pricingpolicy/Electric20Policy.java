package com.fahdisa.pklot.ParkLot.factory.pricingpolicy;

import java.math.BigDecimal;

public class Electric20Policy implements Policy {

    private BigDecimal hourPrice;

    private BigDecimal fixedAmount;

    public Electric20Policy(BigDecimal hourPrice, BigDecimal fixedAmount){
        this.hourPrice = hourPrice;
        this.fixedAmount = fixedAmount;
    }

    @Override
    public BigDecimal getPrice(long numberOfHour) {
        return this.fixedAmount.add(hourPrice.multiply(new BigDecimal(numberOfHour)));
    }
}
