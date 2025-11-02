package com.nova.billing.core;

import com.nova.billing.domain.DomainType;

public interface DomainBillingStrategy {

    DomainType getDomainType();

    void execute(BillingContext context);
    
}
