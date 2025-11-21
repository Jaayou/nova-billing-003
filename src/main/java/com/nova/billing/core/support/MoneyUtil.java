package com.nova.billing.core.support;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {

    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.FLOOR;
    private static final int SCALE = 0;

    public static BigDecimal prorate(BigDecimal amount, long usedDays, long totalDays) {
        if (amount == null || totalDays == 0)
            return BigDecimal.ZERO;
        if (usedDays == totalDays)
            return amount;

        return amount.multiply(BigDecimal.valueOf(usedDays))
                .divide(BigDecimal.valueOf(totalDays), 20, DEFAULT_ROUNDING)
                .setScale(SCALE, DEFAULT_ROUNDING);
    }

    public static BigDecimal calculateTax(BigDecimal amount, BigDecimal rate) {
        if (amount == null || rate == null)
            return BigDecimal.ZERO;

        return amount.multiply(rate)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }
}
