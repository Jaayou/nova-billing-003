package com.nova.billing.core.spi;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;

public interface DomainBillingManager {

    DomainType getDomainType();
    void execute(BillingContext context);
    
}
