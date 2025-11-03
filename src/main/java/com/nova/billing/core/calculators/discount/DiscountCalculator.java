package com.nova.billing.core.calculators.discount;

import com.nova.billing.core.BillingContext;

public interface DiscountCalculator {
    boolean supports(BillingContext context);
    void apply(BillingContext context);
}
