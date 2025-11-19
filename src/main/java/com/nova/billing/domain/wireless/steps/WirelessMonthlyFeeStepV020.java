package com.nova.billing.domain.wireless.steps;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.core.rule.RuleEvaluator;

import lombok.RequiredArgsConstructor;

@Component
@Profile("v020")
@RequiredArgsConstructor
public class WirelessMonthlyFeeStepV020 implements CalculationStep {

    private final RuleEvaluator ruleEvaluator;

    @Override
    public void execute(BillingContext context) {
        ruleEvaluator.evaluateAndExucute(context);
        System.out.println("    [Step] -> 01. WirelessMonthlyFeeStepV020 Executed (Rule Based)");
    }
}
