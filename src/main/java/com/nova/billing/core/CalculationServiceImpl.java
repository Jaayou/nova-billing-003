package com.nova.billing.core;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nova.billing.core.preparation.DataPreparer;
import com.nova.billing.core.preparation.DataPreparerFactory;
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

    private final DataPreparerFactory dataPreparerFactory;
    private final List<DomainBillingStrategy> allDomainStrategies;
    private final Map<DomainType, DomainBillingStrategy> strategyMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initializeStrategyMap() {
        System.out.println("\n=======================================================");
        System.out.println("  NOVA Billing Engine Initializing... [Version v0.14]");
        System.out.println("  (Arch: Strategy-Pipeline-Step-Calculator)");
        System.out.println("-------------------------------------------------------");
        System.out.println("[ServiceImpl] Initializing Strategy Map...");

        for (DomainBillingStrategy strategy : allDomainStrategies) {
            if (strategyMap.containsKey(strategy.getDomainType())) {
                System.err.println("[WARNING] Duplicate Domain Strategy detected: " + strategy.getDomainType());
            }
            strategyMap.put(strategy.getDomainType(), strategy);

            System.out.println("  [ServiceImpl] Strategy Map initialized. Total strategies: " + strategyMap.size());
            System.out.println("    [domain, strategy] " + strategy.getDomainType() + ", " + strategy.getClass().getSimpleName());
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

        try {
            System.out.println("  [ServiceImpl] -> (1) Calling DataPreparerFactory...");

            DomainType domain = param.getDomainType();
            DataPreparer preparer = dataPreparerFactory.findDataPreparer(domain);
            BillingSubject subject = preparer.prepareData(param);
            BillingContext context = new BillingContext(param, subject, bill);
            System.out.println("  [ServiceImpl] -> (3) BillingContext (Flat) Created.");

            DomainBillingStrategy strategy = Optional.ofNullable(strategyMap.get(domain))
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for strategy: " + domain));

            System.out.println("  [ServiceImpl] -> Found Manager: " + strategy.getClass().getSimpleName());

            strategy.execute(context);
            bill.setMessage("Calculation Complete");
        } catch (Exception e) {
            System.err.println("  [ServiceImpl] -> ERROR: " + e.getMessage());
            bill.setMessage("Calculation Failed: " + e.getMessage());
        }

        System.out.println("  [ServiceImpl] === Calculation Complete ===");
        System.out.println("  [ServiceImpl] Final Bill Object: " + bill.toString());
        System.out.println("  [ServiceImpl] Final TotalAmount: " + bill.getTotalAmount());

        return bill;
    }
}