package com.nova.billing.core.preparation;

import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;

public interface DataPreparer {

    DomainType getDomainType();

    BillingSubject prepareData(CalculationParameter param);
    
}
