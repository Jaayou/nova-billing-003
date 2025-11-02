package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class WirelessBaseFeeStep implements CalculationStep {

    private static final BigDecimal BASE_FEE = new BigDecimal("80000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [v0.03 Step] -> 01. Executing 'WirelessBaseFeeStep' (+80,000)");

        bill.addCharge("Wireless Base Fee", BASE_FEE);
    }
}
