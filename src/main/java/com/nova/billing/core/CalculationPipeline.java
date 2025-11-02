package com.nova.billing.core;

import java.util.List;

public interface CalculationPipeline {

    boolean supports(BillingContext context);

    List<CalculationStep> getSteps();
    
}
