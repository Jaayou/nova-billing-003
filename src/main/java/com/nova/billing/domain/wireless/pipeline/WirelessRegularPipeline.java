package com.nova.billing.domain.wireless.pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.pipeline.CalculationPipeline;
import com.nova.billing.core.pipeline.CalculationStep;
import com.nova.billing.core.pipeline.standard.SplitBillStep;
import com.nova.billing.core.pipeline.standard.VatStep;
import com.nova.billing.domain.wireless.steps.AdjustmentStep;
import com.nova.billing.domain.wireless.steps.AutoPayDiscountStep;
import com.nova.billing.domain.wireless.steps.DiscountStep;
import com.nova.billing.domain.wireless.steps.LateFeeStep;
import com.nova.billing.domain.wireless.steps.OneTimeFeeStep;
import com.nova.billing.domain.wireless.steps.PenaltyStep;
import com.nova.billing.domain.wireless.steps.ReceivableStep;
import com.nova.billing.domain.wireless.steps.RevenueDistributionStep;
import com.nova.billing.domain.wireless.steps.UsageFeeStep;
import com.nova.billing.domain.wireless.steps.WirelessMonthlyFeeStepV020;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessRegularPipeline implements CalculationPipeline {

    private final List<CalculationStep> steps = new ArrayList<>();
    
    //private final WirelessBaseFeeStep wirelessBaseFeeStep;
    private final WirelessMonthlyFeeStepV020 monthlyFeeStep;
    private final UsageFeeStep usageFeeStep;
    private final OneTimeFeeStep oneTimeFeeStep;
    private final DiscountStep discountStep;
    private final PenaltyStep penaltyStep;
    private final LateFeeStep lateFeeStep;
    private final AutoPayDiscountStep autoPayDiscountStep;
    private final VatStep vatStep;
    private final AdjustmentStep adjustmentStep;
    private final ReceivableStep receivableStep;
    private final SplitBillStep splitBillStep;
    private final RevenueDistributionStep revenueDistributionStep;

    @PostConstruct
    public void init() {
        steps.add(monthlyFeeStep);
        steps.add(usageFeeStep);
        steps.add(oneTimeFeeStep);
        steps.add(discountStep);
        steps.add(penaltyStep);
        steps.add(lateFeeStep);
        steps.add(autoPayDiscountStep);
        steps.add(vatStep);
        steps.add(adjustmentStep);
        steps.add(receivableStep);
        steps.add(splitBillStep);
        steps.add(revenueDistributionStep);

        System.out.println("[WirelessRegularPipeline] Initialized with " + steps.size() + " steps.");
    }

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().getDomainType() == DomainType.WIRELESS
                && context.getParam().getOperationType().equals("REGULAR")
                && !context.getParam().isHotbill();
    }

    @Override
    public List<CalculationStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }
}
