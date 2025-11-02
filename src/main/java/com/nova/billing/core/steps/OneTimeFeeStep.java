package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class OneTimeFeeStep implements CalculationStep {

    private static final BigDecimal ONETIME_FEE = new BigDecimal("10000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [v0.05 Step] -> 03. OneTimeFeeStep Executed");

        bill.addCharge("Onetime Fee", ONETIME_FEE);
    }
}
