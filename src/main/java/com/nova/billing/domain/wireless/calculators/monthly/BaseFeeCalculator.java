package com.nova.billing.domain.wireless.calculators.monthly;

import com.nova.billing.core.model.BillingContext;

public interface BaseFeeCalculator {

    boolean supports(BillingContext context);

    void apply(BillingContext context);
}
