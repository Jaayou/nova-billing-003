package com.nova.billing.core.event;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.CalculationParameter;

public record BillCalculatedEvent(
    Bill bill,
    CalculationParameter parameter,
    long timestamp
) {
    public static BillCalculatedEvent of(Bill bill, CalculationParameter param) {
        return new BillCalculatedEvent(bill, param, System.currentTimeMillis());
    }
}
