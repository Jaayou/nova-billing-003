package com.nova.billing.core;

import com.nova.billing.domain.DomainType;

public interface BillingManager {

    DomainType getDomainType();

    void execute(BillingContext context);
    
}
