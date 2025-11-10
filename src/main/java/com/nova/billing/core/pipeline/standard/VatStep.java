package com.nova.billing.core.pipeline.standard;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;

@Component
public class VatStep implements CalculationStep {

    private static final BigDecimal VAT_FEE = new BigDecimal("-5000");

    @Override
    public void execute(BillingContext context) {
        Bill bill = context.getBill();
        
        System.out.println("    [Step] -> 08. VatStep Executed");

        bill.addCharge("Vat Fee", VAT_FEE);
    }
}
