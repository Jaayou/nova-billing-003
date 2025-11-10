package com.nova.billing.core.spi;

import com.nova.billing.core.model.BillingSubject;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;

public interface DataPreparer {
    DomainType getDomainType();
    BillingSubject prepareData(CalculationParameter param);    
}
