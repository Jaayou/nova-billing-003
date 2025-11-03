package com.nova.billing.domain.wired;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WiredPipelineLocator {

    private final List<CalculationPipeline> allPipelines;

    public CalculationPipeline getPipeline(BillingContext context) {

        System.out.println("    [Locator] Finding 'Wired' pipeline...");

        return allPipelines.stream()
                .filter(pipeline -> pipeline.supports(context))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported 'Wired' pipeline for: " + context.getParam()));
    }
}
