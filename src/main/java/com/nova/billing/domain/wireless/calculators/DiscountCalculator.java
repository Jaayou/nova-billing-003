package com.nova.billing.domain.wireless.calculators;

import com.nova.billing.core.model.BillingContext;

public interface DiscountCalculator {
    boolean supports(BillingContext context);
    void apply(BillingContext context);
}
