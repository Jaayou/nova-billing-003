package com.nova.billing.domain.wireless;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.BillingManager;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WirelessManager implements BillingManager {

    private final WirelessPipelineLocator pipelineLocator;

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRELESS;
    }

    @Override
    public void execute(BillingContext context) {
        System.out.println("  [Manager] === 'Wireless' Domain Manager Executing ===");

        CalculationPipeline pipeline = pipelineLocator.getPipeline(context);
        System.out.println("    [Manager] -> Found Pipeline: " + pipeline.getClass().getSimpleName());

        List<CalculationStep> steps = pipeline.getSteps();

        System.out.println("    [Manager] -> Executing " + steps.size() + " Steps in order...");
        for (CalculationStep step : steps) {
            step.execute(context);
        }
        System.out.println("    [Manager] === Pipeline Execution Complete ===");
    }
}
