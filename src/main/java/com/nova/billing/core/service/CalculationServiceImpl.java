package com.nova.billing.core.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.nova.billing.core.event.BillCalculatedEvent;
import com.nova.billing.core.exception.BillingException;
import com.nova.billing.core.exception.ErrorCode;
import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.BillingSubject;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.spi.DataPreparer;
import com.nova.billing.core.spi.DataPreparerFactory;
import com.nova.billing.core.spi.DomainBillingManager;
import com.nova.billing.core.spi.DomainManagerFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final DataPreparerFactory dataPreparerFactory;
    private final DomainManagerFactory domainManagerFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Bill calculate(CalculationParameter param) {

        log.info("Calculation Start for: {}", param.serviceId());

        Bill bill = Bill.builder()
                .serviceId(param.serviceId())
                .message("Calculation processing...")
                .build();

        try {
            log.info("  [ServiceImpl] -> (1) Calling DataPreparerFactory...");

            // Prepare Data
            DataPreparer preparer = dataPreparerFactory.findDataPreparer(param.domainType());
            BillingSubject subject = preparer.prepareData(param);

            if (subject.getProducts().isEmpty()) {
                throw new BillingException(ErrorCode.NO_BILLING_TARGET, "ServiceId: " + param.serviceId());
            }

            subject.getProducts().forEach(prod -> log.debug("    [###ServiceImpl] -> Prepared Product: ID: "
                    + prod.getProductId() + ", Name: " + prod.getProductName() + ", Type: " + prod.getProductType()));

            // Create BillingContext
            BillingContext context = new BillingContext(param, subject, bill);
            log.debug("  [ServiceImpl] -> (3) BillingContext (Flat) Created.");

            DomainBillingManager manager = domainManagerFactory.findManager(param.domainType());
            log.debug("  [ServiceImpl] -> Delegate to Manager: " + manager.getClass().getSimpleName());

            manager.execute(context);

            bill.setMessage("Calculation Complete");

            // Event
            eventPublisher.publishEvent(BillCalculatedEvent.of(bill, param));
            log.info("Event Published: BillCalculatedEvent");
        } catch (BillingException be) {
            log.warn("Business Logic Error: [{}] {}", be.getErrorCode().getCode(), be.getMessage());
            bill.setMessage("Calculation Failed: " + be.getMessage());
            throw be;
        } catch (Exception e) {
            log.error("Unexpected System Error", e);
            throw new BillingException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        log.info("  [ServiceImpl] === Calculation Complete ===");
        log.info("  [ServiceImpl] Final Bill Object: " + bill.toString());
        log.info("  [ServiceImpl] Final TotalAmount: " + bill.getTotalAmount());

        return bill;
    }
}