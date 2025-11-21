package com.nova.billing.domain.wireless.steps;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RevenueDistributionStep implements CalculationStep {

    @Override
    public void execute(BillingContext context) {
        log.info("    [Step] -> 12. RevenueDistributionStep Executed");
    }

}
