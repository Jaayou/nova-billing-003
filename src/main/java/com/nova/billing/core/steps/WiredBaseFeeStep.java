package com.nova.billing.core.steps;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.calculators.BaseFeeCalculator;
import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;
import com.nova.billing.domain.SubContract;

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

        System.out.println("\n    [Step] -> 01. Executing 'WiredBaseFeeStep' (Looping Engine)...");

        BillingSubject subject = context.getSubject();

        for (SubContract subContract : subject.getSubContracts()) {
            System.out.println("      [Engine] -> Processing SubContract: "
                    + subContract.getSubContractId() + " (" + subContract.getProductType() + ")");

            BillingContext subContext = createSubContextForHack(context, subContract);

            List<BaseFeeCalculator> matchingCalculators = allCalculators.stream()
                    .filter(c -> c.supports(subContext))
                    .collect(Collectors.toList());

            if (matchingCalculators.isEmpty()) {
                System.out.println("        [Engine] -> No matching 'Wired' BaseFeeCalculator found.");
            } else {
                for (BaseFeeCalculator calculator : matchingCalculators) {
                    System.out.println(
                            "        [Engine] -> Found Calculator: " + calculator.getClass().getSimpleName());
                    calculator.apply(context);
                }
            }
        }
    }

    private BillingContext createSubContextForHack(BillingContext mainContext, SubContract subContract) {
        CalculationParameter subParam = CalculationParameter.builder()
                .serviceId(subContract.getSubContractId())
                .isHotbill(mainContext.getParam().isHotbill())
                .build();

        return new BillingContext(subParam, null, null);
    }
}
