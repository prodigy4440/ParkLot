package com.fahdisa.pklot.ParkLot.factory;

import com.fahdisa.pklot.ParkLot.config.PktLotConfiguration;
import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Electric20Policy;
import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Electric50Policy;
import com.fahdisa.pklot.ParkLot.factory.pricingpolicy.Policy;
import com.fahdisa.pklot.ParkLot.models.SlotType;
import org.springframework.stereotype.Service;

@Service
public class PriceFactory extends AbstractFactory {

    private final PktLotConfiguration pktLotConfiguration;

    public PriceFactory(PktLotConfiguration pktLotConfiguration){
        this.pktLotConfiguration = pktLotConfiguration;
    }

    @Override
    public Policy getPolicy(SlotType slotType) {
        switch (slotType){
            case ELECTRIC_20:
                return new Electric20Policy(pktLotConfiguration.getElectric20kRate(), pktLotConfiguration.getElectric20kFixed());
            case ELECTRIC_50:
                return new Electric50Policy(pktLotConfiguration.getElectric50kRate(), pktLotConfiguration.getElectric50kFixed());
            case GASOLINE_SEDAN:
            default:
                return new SedanPolicy(pktLotConfiguration.getSedanRate());
        }
    }
}
