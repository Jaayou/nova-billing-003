package com.nova.billing.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubContract {

    private String subContractId;
    private DomainType domainType;
    private String productType;
    
}
