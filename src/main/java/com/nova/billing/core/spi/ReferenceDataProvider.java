package com.nova.billing.core.spi;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReferenceDataProvider {

    BigDecimal getVatRate(LocalDate refDate);
    BigDecimal getLateFeeRate(LocalDate refDate);
        
}
