package com.nova.billing.infrastructure.provider;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.nova.billing.core.config.BillingProperties;
import com.nova.billing.core.spi.ReferenceDataProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockDbReferenceDataProvider implements ReferenceDataProvider {

    private final BillingProperties properties;

    @Override
    public BigDecimal getVatRate(LocalDate refDate) {
        log.debug("Fetching VAT rate from DB(Mock) for date: {}", refDate);
        return properties.getRates().getVat();
    }

    @Override
    public BigDecimal getLateFeeRate(LocalDate refDate) {
        log.debug("Fetching LateFee rate from DB(Mock) for date: {}", refDate);
        return properties.getRates().getLateFee();
    }

}
