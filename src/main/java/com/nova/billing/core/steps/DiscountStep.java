package com.nova.billing.core.steps;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.calculators.discount.DiscountCalculator;
import com.nova.billing.domain.Bill;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscountStep implements CalculationStep {

    private final List<DiscountCalculator> allDisCountCalculators;

    @Override
    public void execute(BillingContext context) {
        System.out.println("\n    [Step] -> 04. Executing 'DiscountStep' (Engine)...");

        List<DiscountCalculator> matchingCalculators = allDisCountCalculators.stream()
                .filter(calculator -> calculator.supports(context))
                .collect(Collectors.toList());

        if (matchingCalculators.isEmpty()) {
            System.out.println("      [Engine] -> No matching 'Discount' Calculators found.");
        } else {
            for (DiscountCalculator calculator : matchingCalculators) {
                System.out.println("      [Engine] -> Found Calculator: " + calculator.getClass().getSimpleName());
                calculator.apply(context);
            }
        }
    }
}
