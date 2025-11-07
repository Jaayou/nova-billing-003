package com.nova.billing.core.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.domain.Bill;

@Component
public class AutoPayDiscountStep implements CalculationStep {

    private static final BigDecimal AUTOPAY_DISCOUNT = new BigDecimal("-600");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [Step] -> 07. AutoPayDiscountFeeStep Executed");

        bill.addCharge("Late Fee", AUTOPAY_DISCOUNT);
    }
}
