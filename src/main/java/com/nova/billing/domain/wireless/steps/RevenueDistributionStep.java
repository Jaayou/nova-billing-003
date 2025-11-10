package com.nova.billing.domain.wireless.steps;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

@Component
public class RevenueDistributionStep implements CalculationStep {

    @Override
    public void execute(BillingContext context) {
        System.out.println("    [Step] -> 12. RevenueDistributionStep Executed");
    }

}
