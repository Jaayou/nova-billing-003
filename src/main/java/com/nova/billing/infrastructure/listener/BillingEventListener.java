package com.nova.billing.infrastructure.listener;

import org.springframework.stereotype.Component;

import com.nova.billing.core.event.BillCalculatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BillingEventListener {

    public void handleBillCalculated(BillCalculatedEvent event) {
        try {
            log.info("==== [Async Listener] Post-Processing Started ====");
            log.info("Target Service: {}", event.parameter().serviceId());
            log.info("Total Amount: {}", event.bill().getTotalAmount());

            simulateHeavyWork();

            log.info("==== [Async Listener] Invoice Generated Successfully ====");
        } catch (Exception e) {
            log.error("Post-processing failed", e);
        }
    }

    private void simulateHeavyWork() {
        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }
}
