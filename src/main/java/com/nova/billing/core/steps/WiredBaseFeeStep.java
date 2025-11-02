package com.nova.billing.core.steps;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;

@Component
public class WiredBaseFeeStep implements CalculationStep {

    @Override
    public void execute(BillingContext context) {
        System.out.println("aa");
    }
}
