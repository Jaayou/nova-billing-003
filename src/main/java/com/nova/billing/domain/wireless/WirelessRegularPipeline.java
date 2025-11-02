package com.nova.billing.domain.wireless;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.steps.AdjustmentStep;
import com.nova.billing.core.steps.DiscountStep;
import com.nova.billing.core.steps.OneTimeFeeStep;
import com.nova.billing.core.steps.ReceivableStep;
import com.nova.billing.core.steps.UsageFeeStep;
import com.nova.billing.core.steps.VatStep;
import com.nova.billing.core.steps.WirelessBaseFeeStep;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessRegularPipeline implements CalculationPipeline {

    private final WirelessBaseFeeStep wirelessBaseFeeStep; // 1. 기본료 (v0.04)
    private final UsageFeeStep usageFeeStep;               // 2. 통화료
    private final OneTimeFeeStep oneTimeFeeStep;           // 3. 일회성요금
    private final DiscountStep discountStep;               // 4. 할인
    private final VatStep vatStep;                         // 5. 부가세
    private final AdjustmentStep adjustmentStep;           // 6. 조정
    private final ReceivableStep receivableStep;           // 7. 반제

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().getDomainType() == DomainType.WIRELESS
                && !context.getParam().isHotbill();
    }

    @Override
    public List<CalculationStep> getSteps() {
        return List.of(
            wirelessBaseFeeStep, // 01
            usageFeeStep,        // 02
            oneTimeFeeStep,      // 03
            discountStep,        // 04
            vatStep,             // 05
            adjustmentStep,      // 06
            receivableStep       // 07
        );
    }
}
