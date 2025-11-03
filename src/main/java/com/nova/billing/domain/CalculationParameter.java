package com.nova.billing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculationParameter {
    
    private String serviceId;

    private boolean isHotbill;

    public DomainType getDomainType() {
        if (this.serviceId != null && this.serviceId.contains("WL")) {
            return DomainType.WIRELESS;
        }
        if (this.serviceId != null && this.serviceId.contains("WD")) {
            return DomainType.WIRED;
        }
        throw new IllegalArgumentException("Unsupported Domain: " + this.serviceId);
    }

    public String getProductType() {
        if (this.serviceId.contains("001") || this.serviceId.contains("003")) {
            return "WL_STANDARD_PLAN";
        }
        if (this.serviceId.contains("005")) {
            return "WL_LITE_PLAN";
        }
        if (this.serviceId.contains("WD")) {
            return "WD_STANDARD_PLAN";
        }
        return "UNKNOWN";
    }
}