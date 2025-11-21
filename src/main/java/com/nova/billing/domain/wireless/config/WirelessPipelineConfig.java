package com.nova.billing.domain.wireless.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nova.billing.core.pipeline.CalculationPipeline;
import com.nova.billing.core.pipeline.PipelineBuilder;
import com.nova.billing.core.pipeline.standard.VatStep;
import com.nova.billing.domain.wireless.steps.UsageFeeStep;
import com.nova.billing.domain.wireless.steps.WirelessMonthlyFeeStep;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WirelessPipelineConfig {

    private final WirelessMonthlyFeeStep montylyFeeStep;
    private final UsageFeeStep usageFeeStep;
    private final VatStep vatStep;

    @Bean("wirelessRegularPipeline")
    public CalculationPipeline wirelessRegularPipeline() {
        return new PipelineBuilder("Wireless-Regular")
                .addStep(montylyFeeStep)
                .addStep(usageFeeStep)
                .addStep(vatStep)
                .build();
    }

}
