package com.nova.billing.domain.wireless.calculators.monthly;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.SubscriptionProduct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WirelessStandardPlanCalculator implements WirelessMonthlyFeeCalculator {

    @Override
    public String getSupportedProductId() {
        return "WL_STANDARD_PLAN";
    }

    @Override
    public void calculate(BillingContext context, SubscriptionProduct targetProduct) {
        BigDecimal fee = BigDecimal.valueOf(80000);
        String desc = "Standard Plan Fee (" + targetProduct.getProductName() + ")";
        
        context.getBill().addCharge(desc, fee);
        log.info("      [Calculator] -> Executing 'WirelessStandardPlanCalculator' (+80,000)");
    }
}
