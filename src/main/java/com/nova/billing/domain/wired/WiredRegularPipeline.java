package com.nova.billing.domain.wired;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.steps.WiredBaseFeeStep;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WiredRegularPipeline implements CalculationPipeline {

    private final WiredBaseFeeStep wiredBaseFeeStep;

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().getDomainType() == DomainType.WIRED
                && !context.getParam().isHotbill();
    }

    @Override
    public List<CalculationStep> getSteps() {
        return List.of(
            wiredBaseFeeStep
        );
    }
}
