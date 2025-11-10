package com.nova.billing.core.pipeline;

import com.nova.billing.core.model.BillingContext;

@FunctionalInterface
public interface CalculationStep {
    void execute(BillingContext context);
}
