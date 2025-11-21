package com.nova.billing.core.pipeline.standard;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SplitBillStep implements CalculationStep {

    @Override
    public void execute(BillingContext context) {
        log.info("    [Step] -> 11. SplitBillStep Executed");
    }

}
