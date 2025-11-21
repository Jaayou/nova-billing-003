package com.nova.billing.domain.wired.calculators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.domain.wireless.calculators.monthly.BaseFeeCalculator;

@Component
public class WiredBaseFeeCalculator implements BaseFeeCalculator {
    private static final BigDecimal BASE_FEE = new BigDecimal("45000");

    @Override
    public boolean supports(BillingContext context) {
        CalculationParameter param = context.getParam();
        return param.domainType() == DomainType.WIRED;
    }

    @Override
    public void apply(BillingContext context) {
        Bill bill = context.getBill();
        System.out.println("      [Calculator] -> Executing 'WiredStandardPlanCalculator' (+45,000)");
        bill.addCharge("Wired Base Fee", BASE_FEE);
    }
}
