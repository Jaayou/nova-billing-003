package com.nova.billing.domain.wireless.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationPipeline;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessPipelineLocator {

    private final List<CalculationPipeline> allPipelines;

    public CalculationPipeline getPipeline(BillingContext context) {
        System.out.println("    [Locator] Finding 'Wireless' pipeline...");

        System.out.println("    [Locator] Available Pipelines:");
        allPipelines.forEach(pipeline -> 
            System.out.println("      - " + pipeline.getClass().getSimpleName())
        );

        return allPipelines.stream()
                .filter(pipeline -> pipeline.supports(context))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported 'Wireless' pipeline for: " + context.getParam()));
    }
}
