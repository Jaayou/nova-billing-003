package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class LateFeeStep implements CalculationStep {

    private static final BigDecimal LATE_FEE = new BigDecimal("2200");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [Step] -> 06. LateFeeStep Executed");

        bill.addCharge("Late Fee", LATE_FEE);
    }

}
