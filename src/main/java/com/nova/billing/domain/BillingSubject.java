package com.nova.billing.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillingSubject {

    private List<SubContract> subContracts;
    
}
