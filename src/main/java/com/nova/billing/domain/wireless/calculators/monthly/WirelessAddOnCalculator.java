package com.nova.billing.domain.wireless.calculators.monthly;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;

@Component
public class WirelessAddOnCalculator implements WirelessMonthlyFeeCalculator {

    @Override
    public String getSupportedProductId() {
        return "WL_ADD_ON_001";
    }

    @Override
    public void calculate(BillingContext context, com.nova.billing.core.model.SubscriptionProduct targetProduct) {
        System.out.println("      [Calculator] -> Executing 'WirelessAddOnCalculator' (+10,000)");
        context.getBill().addCharge("Addon Fee (" + targetProduct.getProductName() + ")", java.math.BigDecimal.valueOf(10000));
    }

}
