package com.nova.billing.core.support;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class PeriodCalculator {

    public long calculateBillableDays(LocalDate billStart, LocalDate billEnd, LocalDate prodStart, LocalDate prodEnd) {
        LocalDate effectiveStart = billStart.isAfter(prodStart) ? billStart : prodStart;
        LocalDate effectiveEnd = billEnd.isBefore(prodEnd) ? billEnd : prodEnd;

        if (effectiveStart.isAfter(effectiveEnd)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(effectiveStart, effectiveEnd) + 1;   
    }

    public long getDaysInMonth(LocalDate date) {
        return date.lengthOfMonth();
    }
}
