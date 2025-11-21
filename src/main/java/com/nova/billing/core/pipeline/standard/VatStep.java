package com.nova.billing.core.pipeline.standard;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.ChargeItem;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.core.spi.ReferenceDataProvider;
import com.nova.billing.core.support.MoneyUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class VatStep implements CalculationStep {

    private final ReferenceDataProvider referenceDataProvider;

    @Override
    public void execute(BillingContext context) {

        BigDecimal vatRate = referenceDataProvider.getVatRate(context.getParam().calculationEndDate());

        BigDecimal totalAmount = context.getBill().getTotalAmount();

        if (totalAmount.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal vatAmount = MoneyUtil.calculateTax(totalAmount, vatRate);

            context.getBill().addCharge(
                    ChargeItem.of("VAT (10%)", vatAmount));
                    
            log.info("    [Step] VatStep: {} * {} = {}", totalAmount, vatRate, vatAmount);
        }

    }
}
