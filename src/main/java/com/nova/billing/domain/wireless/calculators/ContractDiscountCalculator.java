package com.nova.billing.domain.wireless.calculators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;

@Component
public class ContractDiscountCalculator implements DiscountCalculator {
    private static final BigDecimal DISCOUNT_AMOUNT = new BigDecimal("-10000");

    @Override
    public boolean supports(BillingContext context) {
        CalculationParameter param = context.getParam();
        return param.getDomainType() == DomainType.WIRELESS;
    }

    @Override
    public void apply(BillingContext context) {
        Bill bill = context.getBill();
        System.out.println("      [Calculator] -> Executing 'ContractDiscountCalculator' (-10,000)");
        bill.addCharge("Wireless Contract Discount", DISCOUNT_AMOUNT);
    }
}
