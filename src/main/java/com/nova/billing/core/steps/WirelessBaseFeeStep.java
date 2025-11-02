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
public class WirelessBaseFeeStep implements CalculationStep {

    // private final List<BaseFeeCalculator> calculators;
    private final List<BaseFeeCalculator> allCalculators;

    @Override
    public void execute(BillingContext context) {

        // [v0.07] '무선' 도메인이 아니면, 이 스텝(룰 엔진) 자체를 실행할 필요가 없음
        if (context.getParam().getDomainType() != DomainType.WIRELESS) {
            return; // (WiredRegularPipeline이 이 스텝을 호출하는 것을 방지)
        }

        System.out.println("    [v0.06 Step] -> 01. Executing 'WirelessBaseFeeStep' (Engine)...");

        // 1개만 처리하는 계산기의 경우
        // BaseFeeCalculator calculator = calculators.stream()
        // .filter(c -> c.supports(context))
        // .findFirst()
        // .orElseThrow(() -> new IllegalArgumentException(
        // "Unsupported ProductType for BaseFee: " +
        // context.getParam().getProductType()));

        // 0~n개 처리 가능한 계산기
        List<BaseFeeCalculator> matchingCalculators = allCalculators.stream()
                .filter(c -> c.supports(context))
                .collect(Collectors.toList());

        if (matchingCalculators.isEmpty()) {
            System.out.println("      [v0.07 Engine] -> No matching 'Wireless' BaseFeeCalculator found for: " + context.getParam().getProductType());
        } else {
            for (BaseFeeCalculator calculator : matchingCalculators) {
                System.out.println("      [v0.07 Engine] -> Found Calculator: " + calculator.getClass().getSimpleName());
                calculator.apply(context);
            }
        }
    }
}