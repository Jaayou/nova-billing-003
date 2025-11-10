package com.nova.billing.core.spi;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataPreparerFactory {

    private final List<DataPreparer> preparers;
    private final Map<DomainType, DataPreparer> preparerMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initialize() {
        System.out.println("[PreparerFactory] Initializing DataPreparer Map...");

        preparers.forEach(preparer -> {
            System.out.println("  [PreparerFactory] -> Registered Preparer: " + preparer.getDomainType() + " with Class: " + preparer.getClass().getSimpleName());
            preparerMap.put(preparer.getDomainType(), preparer);
        });
    }

    public DataPreparer findDataPreparer(DomainType domain) {
        System.out.println("  [PreparerFactory] -> Finding Preparer for: " + domain);

        return Optional.ofNullable(preparerMap.get(domain))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for DataPreparer: " + domain));
    }
}
