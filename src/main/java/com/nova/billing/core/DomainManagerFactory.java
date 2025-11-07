package com.nova.billing.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nova.billing.domain.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DomainManagerFactory {

    private final List<BillingManager> allStrategies;

    private final Map<DomainType, BillingManager> managerMap = new HashMap<>();

    @PostConstruct
    public void initializeManagerMap() {
        System.out.println("\n[ManagerFactory] Initializing Manager Map...");
        for (BillingManager manager : allStrategies) {
            if (managerMap.containsKey(manager.getDomainType())) {
                System.err.println("[WARNING] Duplicate Domain Manager detected: " + manager.getDomainType());
            }
            managerMap.put(manager.getDomainType(), manager);
            System.out.println("  [ManagerFactory] -> Registered: " + manager.getDomainType());
        }
        System.out.println("[ManagerFactory] Initialization Complete. Total strategies: " + managerMap.size());
    }

    public BillingManager findManager(DomainType domainType) {
        return Optional.ofNullable(managerMap.get(domainType))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for Manager: " + domainType));
    }
}
