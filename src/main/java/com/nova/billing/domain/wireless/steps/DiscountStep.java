package com.nova.billing.domain.wireless.steps;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.calculators.DiscountCalculator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscountStep implements CalculationStep {

    private final List<DiscountCalculator> allDisCountCalculators;

    @Override
    public void execute(BillingContext context) {
        System.out.println("    [Step] -> 04. Executing 'DiscountStep' (Engine)...");

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
