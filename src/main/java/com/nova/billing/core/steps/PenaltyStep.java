package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class PenaltyStep implements CalculationStep {

    private static final BigDecimal PENALTY_FEE = new BigDecimal("13000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [Step] -> 05. PenaltyStep Executed");

        bill.addCharge("Penalty Fee", PENALTY_FEE);        
    }

}
