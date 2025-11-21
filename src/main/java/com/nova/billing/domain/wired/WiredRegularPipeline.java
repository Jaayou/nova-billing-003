package com.nova.billing.domain.wired;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.pipeline.CalculationPipeline;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.steps.WiredBaseFeeStep;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WiredRegularPipeline implements CalculationPipeline {

    private final WiredBaseFeeStep wiredBaseFeeStep;

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().domainType() == DomainType.WIRED
                && !context.getParam().isHotbill();
    }

    @Override
    public List<CalculationStep> getSteps() {
        return List.of(
            wiredBaseFeeStep
        );
    }
}
