package com.nova.billing.core.pipeline;

import java.util.List;

import com.nova.billing.core.model.BillingContext;

public interface CalculationPipeline {
    boolean supports(BillingContext context);
    List<CalculationStep> getSteps();    
}
