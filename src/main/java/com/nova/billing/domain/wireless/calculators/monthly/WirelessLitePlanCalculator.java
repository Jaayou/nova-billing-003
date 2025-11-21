package com.nova.billing.domain.wireless.calculators.monthly;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;

@Component
public class WirelessLitePlanCalculator implements BaseFeeCalculator {

    private static final BigDecimal BASE_FEE = new BigDecimal("55000");

    @Override
    public boolean supports(BillingContext context) {
        CalculationParameter param = context.getParam();
        return param.domainType() == DomainType.WIRELESS;
    }

    @Override
    public void apply(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("      [Calculator] -> Executing 'WirelessLitePlanCalculator' (+55,000)");

        bill.addCharge("Wireless Base Fee (Lite)", BASE_FEE);
    }
}
