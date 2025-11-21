package com.nova.billing.core.model;

import java.math.BigDecimal;

public record ChargeItem(
    String itemName,
    BigDecimal amount
) {
    public static ChargeItem of(String name, BigDecimal amount) {
        return new ChargeItem(name, amount);
    }

    public static ChargeItem of(String name, long amount) {
        return new ChargeItem(name, BigDecimal.valueOf(amount));
    }
}