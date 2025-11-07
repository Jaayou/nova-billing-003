package com.nova.billing.core;

import org.springframework.stereotype.Service;

import com.nova.billing.core.preparation.DataPreparer;
import com.nova.billing.core.preparation.DataPreparerFactory;
import com.nova.billing.domain.Bill;
import com.nova.billing.domain.BillingSubject;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.DomainType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final DataPreparerFactory dataPreparerFactory;
    private final DomainManagerFactory domainManagerFactory;

    @Override
    public Bill calculate(CalculationParameter param) {

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

            // Create BillingContext
            BillingContext context = new BillingContext(param, subject, bill);
            System.out.println("  [ServiceImpl] -> (3) BillingContext (Flat) Created.");

            BillingManager manager = domainManagerFactory.findManager(domain);
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