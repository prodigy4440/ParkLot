package com.fahdisa.pklot.ParkLot.factory;

import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Policy;
import com.fahdisa.pklot.ParkLot.models.SlotType;

public abstract class AbstractFactory {

    abstract Policy getPolicy(SlotType slotType);
}
