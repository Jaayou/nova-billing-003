package com.nova.billing.core.model;

import java.math.BigDecimal;

public record ChargeItem(
    String itemName,
    BigDecimal amount
) {
}