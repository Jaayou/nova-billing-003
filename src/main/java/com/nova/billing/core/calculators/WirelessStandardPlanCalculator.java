package com.nova.billing.core.calculators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;

@Component
public class WirelessStandardPlanCalculator implements BaseFeeCalculator {

    private static final BigDecimal BASE_FEE = new BigDecimal("80000");

    @Override
    public boolean supports(BillingContext context) {
        CalculationParameter param = context.getParam();
        return param.getDomainType() == DomainType.WIRELESS &&
                param.getProductType().equals("WL_STANDARD_PLAN");
    }

    @Override
    public void apply(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("      [Calculator] -> Executing 'WirelessStandardPlanCalculator' (+80,000)");

        bill.addCharge("Wireless Base Fee (Standard)", BASE_FEE);
    }
}
