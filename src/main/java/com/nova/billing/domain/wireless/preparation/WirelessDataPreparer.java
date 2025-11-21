package com.nova.billing.domain.wireless.preparation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingSubject;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.model.SubscriptionProduct;
import com.nova.billing.core.spi.DataPreparer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WirelessDataPreparer implements DataPreparer {

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRELESS;
    }

    @Override
    public BillingSubject prepareData(CalculationParameter param) {
        log.info("  [WirelessDataPreparer] Fetching & flattening contract data for: " + param.serviceId());

        BillingSubject subject = new BillingSubject();
        //subject.setCustomerId(1001L);


        // Plan
        subject.addProduct(SubscriptionProduct.builder()
                .productId("WL_STANDARD_PLAN")
                .productName("LTE Standard Price Plan")
                .productType("PLAN")
                .startDate(LocalDate.of(2025, 10, 1))
                .endDate(LocalDate.of(9999, 12, 31))
                .monthlyRate(BigDecimal.valueOf(90000))
                .build());
                
                // Additional Products
                subject.addProduct(SubscriptionProduct.builder()
                .productId("WL_ADD_ON_001")
                .productName("V Coloring")
                .productType("ADD_ON")
                .startDate(LocalDate.of(2025, 11, 1))
                .endDate(LocalDate.of(9999, 12, 31))
                .monthlyRate(BigDecimal.valueOf(9000))
                .build());

        log.info("  [WirelessDataPreparer] Data prepared with " + subject.getProducts().size() + " products.");

        subject.getProducts().forEach(prod -> 
            log.info("    [###Product] ID: " + prod.getProductId() + ", Name: " + prod.getProductName() + ", Type: " + prod.getProductType())
        );

        return subject;
    }
}
