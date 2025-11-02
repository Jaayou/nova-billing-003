package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class UsageFeeStep implements CalculationStep {

    private static final BigDecimal USAGE_FEE = new BigDecimal("20000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();    

        System.out.println("    [v0.05 Step] -> 02. UsageFeeStep Executed");

        bill.addCharge("Usage Fee", USAGE_FEE);
    }
}
