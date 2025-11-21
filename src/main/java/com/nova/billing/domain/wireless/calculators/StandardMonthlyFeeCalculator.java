package com.nova.billing.domain.wireless.calculators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.ChargeItem;
import com.nova.billing.core.model.SubscriptionProduct;
import com.nova.billing.core.support.MoneyUtil;
import com.nova.billing.core.support.PeriodCalculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StandardMonthlyFeeCalculator {

    private final PeriodCalculator periodCalculator;

    public boolean supports(SubscriptionProduct product) {
        return "PLAN".equals(product.getProductType()) || "ADD_ON".equals(product.getProductType());
    }

    public void calculate(BillingContext context, SubscriptionProduct product) {
        long daysUsed = periodCalculator.calculateBillableDays(
                context.getParam().calculationStartDate(),
                context.getParam().calculationEndDate(),
                product.getStartDate(),
                product.getEndDate());

        if (daysUsed <= 0)
            return;

        long daysInMonth = periodCalculator.getDaysInMonth(context.getParam().calculationStartDate());

        BigDecimal amount = MoneyUtil.prorate(product.getMonthlyRate(), daysUsed, daysInMonth);

        String desc = String.format("%s (%d/%d)", product.getProductName(), daysUsed, daysInMonth);
        context.getBill().addCharge(new ChargeItem(desc, amount));

        log.debug("Calc Item: {} = {}", desc, amount);
    }
}
