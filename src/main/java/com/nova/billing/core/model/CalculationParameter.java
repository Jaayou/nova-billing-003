package com.nova.billing.core.model;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record CalculationParameter(
    String callerId,
    String serviceId,
    DomainType domainType,
    String operationType,
    boolean isHotbill,
    LocalDate calculationStartDate,
    LocalDate calculationEndDate,
    String invoiceDate
) {
    public CalculationParameter {
        if (serviceId == null) throw new IllegalArgumentException("ServiceId required");
    }    
}