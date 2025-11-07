package com.nova.billing.domain.wireless;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.steps.UsageFeeStep;
import com.nova.billing.domain.DomainType;

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
