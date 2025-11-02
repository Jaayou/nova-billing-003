package com.nova.billing.core.steps;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.calculators.BaseFeeCalculator;
import com.nova.billing.domain.Bill;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessBaseFeeStep implements CalculationStep {

    private final List<BaseFeeCalculator> calculators;

    @Override
    public void execute(BillingContext context) {

        System.out.println("    [v0.06 Step] -> 01. Executing 'WirelessBaseFeeStep' (Engine)...");

        BaseFeeCalculator calculator = calculators.stream()
                .filter(c -> c.supports(context))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported ProductType for BaseFee: " + context.getParam().getProductType()));
        
        System.out.println("      [v0.06 Engine] -> Found Calculator: " + calculator.getClass().getSimpleName());

        calculator.apply(context);
        
    }
}