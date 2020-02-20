package com.fahdisa.pklot.ParkLot.factory.pricingpolicy;

import java.math.BigDecimal;

public interface Policy {

    BigDecimal getPrice(long numberOfHour);

}
