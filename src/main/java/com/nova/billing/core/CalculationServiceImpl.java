package com.nova.billing.core;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;
import com.nova.billing.domain.SubContract;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final List<DomainBillingStrategy> allDomainStrategies;

    private final Map<DomainType, DomainBillingStrategy> strategyMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initializeStrategyMap() {
        System.out.println("\n=======================================================");
        System.out.println("  NOVA Billing Engine Initializing... [Version v0.12]");
        System.out.println("  (Arch: Strategy-Pipeline-Step-Calculator)");
        System.out.println("-------------------------------------------------------");
        System.out.println("[ServiceImpl] Initializing Strategy Map...");

        for (DomainBillingStrategy strategy : allDomainStrategies) {
            if (strategyMap.containsKey(strategy.getDomainType())) {
                System.err.println("[WARNING] Duplicate Domain Strategy detected: " + strategy.getDomainType());
            }
            strategyMap.put(strategy.getDomainType(), strategy);

            System.out.println("  [ServiceImpl] Strategy Map initialized. Total strategies: " + strategyMap.size());
        }

        System.out.println("[ServiceImpl] Strategy Map initialized. Total strategies: " + strategyMap.size());
        System.out.println("=======================================================\n");
    }

    @Override
    public Bill calculate(CalculationParameter param) {

        System.out.println("\n[ServiceImpl] === Calculation Start: " + param.getServiceId() + " ===");

        Bill bill = Bill.builder()
                .serviceId(param.getServiceId())
                .message("Calculation processing...")
                .build();

        BillingSubject subject = buildBillingSubject(param);

        BillingContext context = new BillingContext(param, subject, bill);
        System.out.println("  [ServiceImpl] -> BillingContext Created.");

        // 2. [v0.01]
        try {
            DomainType domain = context.getParam().getDomainType();
            DomainBillingStrategy strategy = Optional.ofNullable(strategyMap.get(domain))
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain: " + domain));

            System.out.println("  [ServiceImpl] -> Found Manager: " + strategy.getClass().getSimpleName());

            strategy.execute(context);
            bill.setMessage("Calculation Complete");
        } catch (Exception e) {
            System.err.println("  [ServiceImpl] -> ERROR: " + e.getMessage());
            context.getBill().setMessage("Calculation Failed: " + e.getMessage());
        }

        System.out.println("  [ServiceImpl] === Calculation Complete ===");
        System.out.println("  [ServiceImpl] Final Bill Object: " + context.getBill().toString());
        System.out.println("  [ServiceImpl] Final TotalAmount: " + context.getBill().getTotalAmount());

        return context.getBill();
    }

    private BillingSubject buildBillingSubject(CalculationParameter param) {

        List<SubContract> subContracts;

        if (param.getServiceId().equals("SVC_WL_001")) {
            subContracts = List.of(
                    SubContract.builder().subContractId("WL_sub_1").productType("WL_STANDARD_PLAN").build(),
                    SubContract.builder().subContractId("WL_sub_2").productType("WL_LITE_PLAN").build());
        } else if (param.getServiceId().equals("SVC_WD_002")) {
            subContracts = List.of(
                    SubContract.builder().subContractId("WD_sub_2").productType("WD_STANDARD_PLAN").build());
        } else {
            subContracts = List.of(
                    SubContract.builder().subContractId(param.getServiceId() + "_sub")
                            .productType(param.getProductType())
                            .build());
        }

        return BillingSubject.builder()
                .subContracts(subContracts)
                .build();
    }
}