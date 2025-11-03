package com.nova.billing.domain.wired;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.DomainBillingStrategy;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WiredStrategy implements DomainBillingStrategy {

    private final WiredPipelineLocator pipelineLocator;

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRED;
    }

    @Override
    public void execute(BillingContext context) {
        System.out.println("  [Strategy] === 'Wired' Domain Strategy Executing ===");

        CalculationPipeline pipeline = pipelineLocator.getPipeline(context);
        System.out.println("    [Strategy] -> Found Pipeline: " + pipeline.getClass().getSimpleName());

        List<CalculationStep> steps = pipeline.getSteps();

        System.out.println("    [Strategy] -> Executing " + steps.size() + " Steps in order...");
        for (CalculationStep step : steps) {
            step.execute(context);
        }
        System.out.println("    [Strategy] === Pipeline Execution Complete ===");
    }
}
