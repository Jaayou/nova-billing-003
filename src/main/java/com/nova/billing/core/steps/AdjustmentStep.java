package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class AdjustmentStep implements CalculationStep {

    private static final BigDecimal ADJUSTMENT_FEE = new BigDecimal("-5000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [v0.05 Step] -> 06. AdjustmentStep Executed");

        bill.addCharge("Adjustment Fee", ADJUSTMENT_FEE);
    }
}
