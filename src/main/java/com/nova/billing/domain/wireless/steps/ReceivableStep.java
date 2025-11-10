package com.nova.billing.domain.wireless.steps;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

@Component
public class ReceivableStep implements CalculationStep {
    
    private static final BigDecimal RECEIVABLE_FEE = new BigDecimal("-3000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();

        System.out.println("    [Step] -> 10. ReceivableStep Executed");

        bill.addCharge("Receivable Fee", RECEIVABLE_FEE);
    }
}
