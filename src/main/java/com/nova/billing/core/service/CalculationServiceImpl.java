package com.nova.billing.core.service;

import org.springframework.stereotype.Service;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.BillingContext;
import com.nova.billing.core.model.BillingSubject;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.model.DomainType;
import com.nova.billing.core.spi.DataPreparer;
import com.nova.billing.core.spi.DataPreparerFactory;
import com.nova.billing.core.spi.DomainBillingManager;
import com.nova.billing.core.spi.DomainManagerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final DataPreparerFactory dataPreparerFactory;
    private final DomainManagerFactory domainManagerFactory;

    @Override
    public Bill calculate(CalculationParameter param) {

        System.out.println("\n[ServiceImpl] === v0.18 ===");
        System.out.println("\n[ServiceImpl] === Calculation Start: " + param.getServiceId() + " ===");

        Bill bill = Bill.builder()
                .serviceId(param.getServiceId())
                .message("Calculation processing...")
                .build();

        try {
            System.out.println("  [ServiceImpl] -> (1) Calling DataPreparerFactory...");

            // Prepare Data
            DomainType domain = param.getDomainType();
            DataPreparer preparer = dataPreparerFactory.findDataPreparer(domain);
            BillingSubject subject = preparer.prepareData(param);

            subject.getProducts().forEach(prod -> 
                System.out.println("    [###ServiceImpl] -> Prepared Product: ID: " + prod.getProductId() + ", Name: " + prod.getProductName() + ", Type: " + prod.getProductType())
            );

            // Create BillingContext
            BillingContext context = new BillingContext(param, subject, bill);
            System.out.println("  [ServiceImpl] -> (3) BillingContext (Flat) Created.");

            DomainBillingManager manager = domainManagerFactory.findManager(domain);
            System.out.println("  [ServiceImpl] -> Delegate to Manager: " + manager.getClass().getSimpleName());

            manager.execute(context);

            bill.setMessage("Calculation Complete");
        } catch (Exception e) {
            System.err.println("  [ServiceImpl] -> ERROR: " + e.getMessage());
            bill.setMessage("Calculation Failed: " + e.getMessage());
        }

        System.out.println("  [ServiceImpl] === Calculation Complete ===");
        System.out.println("  [ServiceImpl] Final Bill Object: " + bill.toString());
        System.out.println("  [ServiceImpl] Final TotalAmount: " + bill.getTotalAmount());

        return bill;
    }
}