package com.nova.billing.domain.wireless.pipeline;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.pipeline.CalculationPipeline;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.steps.UsageFeeStep;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessBillShockPipeline implements CalculationPipeline {

    private final UsageFeeStep usageFeeStep;

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().getDomainType() == DomainType.WIRELESS
                && context.getParam().getOperationType().equals("BILL_SHOCK");
    }

    @Override
    public List<CalculationStep> getSteps() {
        return List.of(
            usageFeeStep               // 01
        );
    }
}
