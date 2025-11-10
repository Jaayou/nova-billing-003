package com.nova.billing.domain.wireless.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.SubscriptionProduct;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.calculators.monthly.WirelessMonthlyFeeCalculator;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessMonthlyFeeStep implements CalculationStep {

    private final List<WirelessMonthlyFeeCalculator> allCalculators;
    private final Map<String, WirelessMonthlyFeeCalculator> calculatorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("[MonthlyFeeStep] Initializing Product Registry...");
        for (WirelessMonthlyFeeCalculator calc : allCalculators) {
            calculatorMap.put(calc.getSupportedProductId(), calc);
            System.out.println("  [Registry] -> Registered Product ID: " + calc.getSupportedProductId() + " with Calculator: " + calc.getClass().getSimpleName());
        }
    }

    @Override
    public void execute(BillingContext context) {
        List<SubscriptionProduct> products = context.getProducts();
        System.out.println("    [Step] -> 01. MonthlyFeeStep Processing " + products.size() + " products for this customer...");
        
        for (SubscriptionProduct product : products) {
            String productId = product.getProductId();
            System.out.println("      [###productId]: " + productId);

            WirelessMonthlyFeeCalculator calculator = calculatorMap.get(productId);
            
            if (calculator != null) {
                System.out.print("      [Loop] Product '" + productId + "' (" + product.getProductName() + ") -> ");
                calculator.calculate(context, product);
            } else {
                System.out.println("        [Engine] -> No matching MonthlyFeeCalculator found for Product ID: " + product.getProductId());
            }
        }

        System.out.println("    [Step] -> 01. MonthlyFeeStep Executed");
    }
}
