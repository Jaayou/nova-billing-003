package com.nova.billing.domain.wireless.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationPipeline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WirelessPipelineLocator {

    private final List<CalculationPipeline> allPipelines;

    public CalculationPipeline getPipeline(BillingContext context) {
        log.info("    [Locator] Finding 'Wireless' pipeline...");

        log.info("    [Locator] Available Pipelines:");
        allPipelines.forEach(pipeline -> 
            log.info("      - " + pipeline.getClass().getSimpleName())
        );

        return allPipelines.stream()
                .filter(pipeline -> pipeline.supports(context))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported 'Wireless' pipeline for: " + context.getParam()));
    }
}
