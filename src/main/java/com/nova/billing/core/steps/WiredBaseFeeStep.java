package com.nova.billing.core.steps;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.calculators.BaseFeeCalculator;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WiredBaseFeeStep implements CalculationStep {

    // [v0.07 OCP] Spring이 @Component로 등록된 '모든' BaseFeeCalculator를 주입
    private final List<BaseFeeCalculator> allCalculators;
    
    @Override
    public void execute(BillingContext context) {
        
        // [v0.07] '유선' 도메인이 아니면 실행 안 함
        if (context.getParam().getDomainType() != DomainType.WIRED) {
            return;
        }

        // [System.out] (v0.07)
        System.out.println("    [v0.07 Step] -> 01. Executing 'WiredBaseFeeStep' (Engine)...");

        // [v0.07 룰 엔진]
        List<BaseFeeCalculator> matchingCalculators = allCalculators.stream()
                .filter(c -> c.supports(context)) // 이 Context(상품유형)를 처리할 '유선' 계산기를 '모두' 찾음
                .collect(Collectors.toList());

        if (matchingCalculators.isEmpty()) {
            System.out.println("      [v0.07 Engine] -> No matching 'Wired' BaseFeeCalculator found for: " + context.getParam().getProductType());
        } else {
            for (BaseFeeCalculator calculator : matchingCalculators) {
                // [System.out] (v0.07)
                System.out.println("      [v0.07 Engine] -> Found Calculator: " + calculator.getClass().getSimpleName());
                calculator.apply(context);
            }
        }
    }
}
