package com.nova.billing.core.spi;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.DomainType;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataPreparerFactory {

    private final List<DataPreparer> preparers;
    private final Map<DomainType, DataPreparer> preparerMap = new EnumMap<>(DomainType.class);

    @PostConstruct
    public void initialize() {
        log.info("[PreparerFactory] Initializing DataPreparer Map...");

        preparers.forEach(preparer -> {
            log.info("  [PreparerFactory] -> Registered Preparer: " + preparer.getDomainType() + " with Class: " + preparer.getClass().getSimpleName());
            preparerMap.put(preparer.getDomainType(), preparer);
        });
    }

    public DataPreparer findDataPreparer(DomainType domain) {
        log.info("  [PreparerFactory] -> Finding Preparer for: " + domain);

        return Optional.ofNullable(preparerMap.get(domain))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Domain for DataPreparer: " + domain));
    }
}
