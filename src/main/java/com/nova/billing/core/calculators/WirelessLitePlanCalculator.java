package com.nova.billing.core.calculators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;

@Component
public class WirelessLitePlanCalculator implements BaseFeeCalculator {

    private static final BigDecimal BASE_FEE = new BigDecimal("55000");

    @Override
    public boolean supports(BillingContext context) {
        CalculationParameter param = context.getParam();
        return param.getDomainType() == DomainType.WIRELESS &&
                param.getProductType().equals("WL_LITE_PLAN");
    }

    @Override
    public void apply(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("      [v0.06 Calculator] -> Executing 'WirelessLitePlanCalculator' (+55,000)");

        bill.addCharge("Wireless Base Fee (Lite)", BASE_FEE);
    }
}
