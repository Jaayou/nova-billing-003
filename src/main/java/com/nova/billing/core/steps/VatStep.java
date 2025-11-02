package com.nova.billing.core.steps;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;

@Component
public class VatStep implements CalculationStep {
    @Override
    public void execute(BillingContext context) {
        System.out.println("    [v0.05 Step] -> 05. VatStep Executed");
    }
}
