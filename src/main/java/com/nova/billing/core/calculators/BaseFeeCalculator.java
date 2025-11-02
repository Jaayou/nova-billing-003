package com.nova.billing.core.calculators;

import com.nova.billing.core.BillingContext;

public interface BaseFeeCalculator {

    boolean supports(BillingContext context);

    void apply(BillingContext context);
}
