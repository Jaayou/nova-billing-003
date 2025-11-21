package com.nova.billing.core.pipeline;

import java.util.List;

import org.slf4j.MDC;

import com.nova.billing.core.model.BillingContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultCalculationPipeline implements CalculationPipeline {

    private final String pipelineName;
    private final List<CalculationStep> steps;

    @Override
    public boolean supports(BillingContext context) {
        return true;
    }

    @Override
    public List<CalculationStep> getSteps() {
        return steps;
    }

    public void execute(BillingContext context) {
        MDC.put("serviceId", context.getParam().serviceId());
        long startTime = System.currentTimeMillis();

        log.info(">>> Pipeline [{}] Start. (Steps: {})", pipelineName, steps.size());

        try {
            for (CalculationStep step : steps) {
                step.execute(context);
            }
        } finally {
            log.info("<<< Pipeline [{}] End. ({}ms)", pipelineName, System.currentTimeMillis() - startTime);
            MDC.clear();
        }
    }
}
