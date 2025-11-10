package com.nova.billing.core.pipeline.standard;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

@Component
public class SplitBillStep implements CalculationStep {

    @Override
    public void execute(BillingContext context) {
        System.out.println("    [Step] -> 11. SplitBillStep Executed");
    }

}
