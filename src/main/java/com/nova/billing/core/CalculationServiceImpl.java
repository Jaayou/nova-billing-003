package com.nova.billing.core;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * [v0.00-eng] CalculationService의 임시 구현체 ('구현')
 * - 하드코딩된 'if-else' 기본료 로직 (영문 로그)
 */
@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final List<DomainBillingStrategy> allDomainStrategies;

    private final Map<DomainType, DomainBillingStrategy> strategyMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initializeStrategyMap() {
        System.out.println("\n[v0.03 ServiceImpl] Initializing Strategy Map...");

        for (DomainBillingStrategy strategy : allDomainStrategies) {
            if (strategyMap.containsKey(strategy.getDomainType())) {
                System.err.println("[WARNING] Duplicate Domain Strategy detected: " + strategy.getDomainType());
            }
            strategyMap.put(strategy.getDomainType(), strategy);

            System.out.println("[v0.03 ServiceImpl] Strategy Map initialized. Total strategies: " + strategyMap.size());
        }
    }

    @Override
    public Bill calculate(CalculationParameter param) {

        // [System.out] 1. 서비스 시작 확인 (영문)
        System.out.println("\n[v0.03 ServiceImpl] === Calculation Start: " + param.getServiceId() + " ===");

        // 1. '빈 Bill' 객체 생성
        Bill bill = Bill.builder()
                .serviceId(param.getServiceId())
                .message("Calculation processing...")
                .build();

        BillingContext context = new BillingContext(param, bill);
        System.out.println("  [v0.03 ServiceImpl] -> BillingContext Created.");

        // 2. [v0.01]
        try {
            DomainType domain = context.getParam().getDomainType();
            DomainBillingStrategy strategy = Optional.ofNullable(strategyMap.get(domain))
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain: " + domain));

            System.out.println("  [v0.03 ServiceImpl] -> Found Manager: " + strategy.getClass().getSimpleName());

            strategy.execute(context);
            bill.setMessage("Calculation Complete");
        } catch (Exception e) {
            System.err.println("  [v0.03 ServiceImpl] -> ERROR: " + e.getMessage());
            context.getBill().setMessage("Calculation Failed: " + e.getMessage());
        }

        // [System.out] 3. 최종 결과 확인
        System.out.println("  [v0.03 ServiceImpl] === Calculation Complete ===");
        System.out.println("  [v0.03 ServiceImpl] Final Bill Object: " + context.getBill().toString());
        System.out.println("  [v0.03 ServiceImpl] Final TotalAmount: " + context.getBill().getTotalAmount());

        return context.getBill();
    }
}