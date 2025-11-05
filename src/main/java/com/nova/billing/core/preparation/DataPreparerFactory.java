package com.nova.billing.core.preparation;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nova.billing.domain.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataPreparerFactory {

    private final List<DataPreparer> allPreparers;
    private final Map<DomainType, DataPreparer> preparerMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initialize() {
        System.out.println("[PreparerFactory] Initializing DataPreparer Map...");

        allPreparers.forEach(preparer -> {
            System.out.println("  [PreparerFactory] -> Registered Preparer: " + preparer.getDomainType());
            preparerMap.put(preparer.getDomainType(), preparer);
        });
    }

    public DataPreparer findDataPreparer(DomainType domain) {
        System.out.println("  [PreparerFactory] -> Finding Preparer for: " + domain);

        return Optional.ofNullable(preparerMap.get(domain))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for DataPreparer: " + domain));
    }
}
