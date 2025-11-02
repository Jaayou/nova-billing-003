package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class DiscountStep implements CalculationStep {

    private static final BigDecimal DISCOUNT_FEE = new BigDecimal("-10000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [v0.05 Step] -> 04. DiscountStep Executed");

        bill.addCharge("Discount Fee", DISCOUNT_FEE);
    }
}
