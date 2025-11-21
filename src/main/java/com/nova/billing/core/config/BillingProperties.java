package com.nova.billing.core.config;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "nova.billing")
public class BillingProperties {

    private Rates rates = new Rates();
    private Fees fees = new Fees();

    @Getter
    @Setter
    public static class Rates {
        private BigDecimal vat = new BigDecimal("0.1");
        private BigDecimal lateFee = new BigDecimal("0.02");
    }

    @Getter
    @Setter
    public static class Fees {
        private BigDecimal defaultBaseFee = new BigDecimal("11000");
    }
}
