package com.nova.billing.domain.wireless;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nova.billing.core.BillingContext;
import com.nova.billing.core.CalculationPipeline;
import com.nova.billing.core.CalculationStep;
import com.nova.billing.core.steps.AdjustmentStep;
import com.nova.billing.core.steps.AutoPayDiscountStep;
import com.nova.billing.core.steps.DiscountStep;
import com.nova.billing.core.steps.LateFeeStep;
import com.nova.billing.core.steps.OneTimeFeeStep;
import com.nova.billing.core.steps.PenaltyStep;
import com.nova.billing.core.steps.ReceivableStep;
import com.nova.billing.core.steps.RevenueDistributionStep;
import com.nova.billing.core.steps.SplitBillStep;
import com.nova.billing.core.steps.UsageFeeStep;
import com.nova.billing.core.steps.VatStep;
import com.nova.billing.core.steps.WirelessBaseFeeStep;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WirelessRegularPipeline implements CalculationPipeline {

    private final WirelessBaseFeeStep wirelessBaseFeeStep;
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

    @Override
    public boolean supports(BillingContext context) {
        return context.getParam().getDomainType() == DomainType.WIRELESS
                && context.getParam().getOperationType().equals("REGULAR")
                && !context.getParam().isHotbill();
    }

    @Override
    public List<CalculationStep> getSteps() {
        return List.of(
                wirelessBaseFeeStep, // 01
                usageFeeStep, // 02
                oneTimeFeeStep, // 03
                discountStep, // 04
                penaltyStep, // 05
                lateFeeStep, // 06
                autoPayDiscountStep, // 07
                vatStep, // 08
                adjustmentStep, // 09
                receivableStep, // 10
                splitBillStep, // 11
                revenueDistributionStep // 12
        );
    }
}
