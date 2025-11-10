package com.nova.billing.core.spi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DomainManagerFactory {

    private final List<DomainBillingManager> managers;

    private final Map<DomainType, DomainBillingManager> managerMap = new HashMap<>();

    @PostConstruct
    public void initializeManagerMap() {
        System.out.println("\n[ManagerFactory] Initializing Manager Map...");
        managers.forEach(m -> managerMap.put(m.getDomainType(), m));
        System.out.println("[ManagerFactory] Initialization Complete. Total strategies: " + managerMap.size());
    }

    public DomainBillingManager findManager(DomainType domainType) {
        return Optional.ofNullable(managerMap.get(domainType))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for Manager: " + domainType));
    }
}
