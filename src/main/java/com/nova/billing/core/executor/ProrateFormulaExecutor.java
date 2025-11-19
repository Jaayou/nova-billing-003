package com.nova.billing.core.executor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.SubscriptionProduct;

@Service
public class ProrateFormulaExecutor {

    public void executeProrateFormula(BillingContext context, SubscriptionProduct product, String formulaId) {
        System.out.println("  [### executeProrateFormula] Started...");
        BigDecimal monthlyRate = product.getMonthlyRate();

        LocalDate startDate = product.getStartDate();
        LocalDate endDate = context.getParam().getCalculationEndDate().isBefore(product.getEndDate()) ? context.getParam().getCalculationEndDate() : product.getEndDate();

        System.out.println(" [## startdate, endDate]" + startDate + "/" + endDate);

        long daysInMonth = startDate.lengthOfMonth();
        long daysUsed = ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));

        if (daysInMonth == 0)
            return;

        System.out.println("  [## monthlyRate]" + monthlyRate.toString());
        System.out.println("  [## daysUsed]" + daysUsed);
        System.out.println("  [## daysInMonth]" + daysInMonth);
        BigDecimal prorateAmount = monthlyRate
                .multiply(BigDecimal.valueOf(daysUsed))
                .divide(BigDecimal.valueOf(daysInMonth), 2, RoundingMode.HALF_UP);

        System.out.println("  [## prorateAmount]" + prorateAmount.toString());
        context.getBill().addCharge(product.getProductName() + " (" + formulaId + ") ", prorateAmount);
    }
}
