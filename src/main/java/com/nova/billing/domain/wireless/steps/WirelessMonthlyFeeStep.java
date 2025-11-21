package com.nova.billing.domain.wireless.steps;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.SubscriptionProduct;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.calculators.StandardMonthlyFeeCalculator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessMonthlyFeeStep implements CalculationStep {

    private final StandardMonthlyFeeCalculator standardCalculator;

    @Override
    public void execute(BillingContext context) {
        for (SubscriptionProduct product : context.getProducts()) {
            if (standardCalculator.supports(product)) {
                standardCalculator.calculate(context, product);
            }
        }
    }
}
