package com.nova.billing.api;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nova.billing.core.model.Bill;
import com.nova.billing.core.model.CalculationParameter;
import com.nova.billing.core.service.CalculationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HotbillController {

    private final CalculationService calculationService;

    @PostMapping("/hotbill")
    public Bill calculateHotbill(@RequestBody HotbillRequest request) {
        System.out.println("\n[Controller] === Hotbill Request Received: " + request.getServiceId() + " ===");

        CalculationParameter param = CalculationParameter.builder()
                .callerId(request.getCallerId())
                .serviceId(request.getServiceId())
                .domainType(request.getDomainType())
                .operationType(request.getOperationType())
                .isHotbill(request.isHotbill())
                .calculationStartDate(LocalDate.parse(request.getCalculationStartDate()))
                .calculationEndDate(LocalDate.parse(request.getCalculationEndDate()))
                .invoiceDate(request.getInvoiceDate())
                .build();
    
        System.out.println("  [Controller] -> Calling CalculationService...");

        Bill billResult = calculationService.calculate(param);

        System.out.println("  [Controller] === Returning Bill Response ===");

        return billResult;
    }
}
