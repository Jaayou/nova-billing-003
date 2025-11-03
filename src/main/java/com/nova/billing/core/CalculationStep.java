package com.nova.billing.core;

@FunctionalInterface
public interface CalculationStep {

    void execute(BillingContext context);
}
