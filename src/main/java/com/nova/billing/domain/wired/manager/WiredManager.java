package com.nova.billing.domain.wired.manager;

import org.springframework.stereotype.Service;

import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.spi.DomainBillingManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WiredManager implements DomainBillingManager {

    @Override
    public DomainType getDomainType() {
        return DomainType.WIRED;
    }

    @Override
    public void execute(BillingContext context) {
        log.info("  [Manager] Wired Domain Execution Started (Not implemented yet)");
    }

}
