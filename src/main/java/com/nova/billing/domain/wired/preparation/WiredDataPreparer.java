package com.nova.billing.domain.wired.preparation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingSubject;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.model.SubscriptionProduct;
import com.nova.billing.core.spi.DataPreparer;

@Component
public class WiredDataPreparer implements DataPreparer {

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRED;
    }

    @Override
    public BillingSubject prepareData(CalculationParameter param) {
        // Mock
        BillingSubject subject = new BillingSubject();

        subject.addProduct(SubscriptionProduct.builder()
        .productId("WD_INTERNET_GIGA")
        .productName("Giga Internet (1Gbps)")
        .productType("PLAN")
        .startDate(LocalDate.of(2025,1,1))
        .endDate(LocalDate.of(9999,12,31))
        .monthlyRate(new BigDecimal("38500"))
        .build());
        
        return subject;
    }
}
