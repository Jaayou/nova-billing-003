package com.nova.billing.core;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BillingContext {

    private final CalculationParameter param;

    private final BillingSubject subject;

    private final Bill bill;
}
