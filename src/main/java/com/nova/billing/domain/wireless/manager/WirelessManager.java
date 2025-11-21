package com.nova.billing.domain.wireless.manager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.pipeline.CalculationPipeline;
import com.nova.billing.core.pipeline.DefaultCalculationPipeline;
import com.nova.billing.core.spi.DomainBillingManager;

@Service
public class WirelessManager implements DomainBillingManager {

    private final CalculationPipeline regularPipeline;

    public WirelessManager(@Qualifier("wirelessRegularPipeline") CalculationPipeline pipeline) {
        this.regularPipeline = pipeline;
    }

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRELESS;
    }

    @Override
    public void execute(BillingContext context) {
        if (regularPipeline instanceof DefaultCalculationPipeline pipeline) {
            pipeline.execute(context);
        } else {
            regularPipeline.getSteps().forEach(step -> step.execute(context));
        }
    }
}
