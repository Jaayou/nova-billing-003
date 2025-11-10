package com.nova.billing.domain.wireless.calculators.monthly;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.SubscriptionProduct;

public interface WirelessMonthlyFeeCalculator {
    String getSupportedProductId();
    void calculate(BillingContext context, SubscriptionProduct targetProduct);
}
