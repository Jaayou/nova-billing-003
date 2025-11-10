package com.nova.billing.domain.wireless.steps;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.model.SubContract;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.domain.wireless.calculators.monthly.BaseFeeCalculator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WiredBaseFeeStep implements CalculationStep {

    private final List<BaseFeeCalculator> allCalculators;

    @Override
    public void execute(BillingContext context) {

        if (context.getParam().getDomainType() != DomainType.WIRED) {
            return;
        }

        System.out.println("    [Step] -> 01. Executing 'WiredBaseFeeStep' (Looping Engine)...");
        
    }

    private BillingContext createSubContextForHack(BillingContext mainContext, SubContract subContract) {
        CalculationParameter subParam = CalculationParameter.builder()
                .serviceId(subContract.getSubContractId())
                .isHotbill(mainContext.getParam().isHotbill())
                .build();

        return new BillingContext(subParam, null, null);
    }
}
