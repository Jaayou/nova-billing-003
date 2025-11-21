package com.nova.billing.domain.wireless.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UsageFeeStep implements CalculationStep {

    private static final BigDecimal USAGE_FEE = new BigDecimal("20000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();    

        log.info("    [Step] -> 02. UsageFeeStep Executed");

        bill.addCharge("Usage Fee", USAGE_FEE);
    }
}
