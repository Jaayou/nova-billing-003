package com.nova.billing.domain.wired.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationPipeline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WiredPipelineLocator {

    private final List<CalculationPipeline> allPipelines;

    public CalculationPipeline getPipeline(BillingContext context) {

        log.info("    [Locator] Finding 'Wired' pipeline...");

        return allPipelines.stream()
                .filter(pipeline -> pipeline.supports(context))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported 'Wired' pipeline for: " + context.getParam()));
    }
}
