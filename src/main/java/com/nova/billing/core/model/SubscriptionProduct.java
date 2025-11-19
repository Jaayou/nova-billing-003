package com.nova.billing.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class SubscriptionProduct {
    private String productId;
    private String productName;
    private String productType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyRate;    
}
