package com.nova.billing.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nova.billing.core.CalculationService;
import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class HotbillController {

    private final CalculationService calculationService;

    @PostMapping("/hotbill")
    public Bill calculateHotbill(@RequestBody HotbillRequest request) {
        System.out.println("\n[v0.03 Controller] === Hotbill Request Received: " + request.getServiceId() + " ===");

        CalculationParameter param = CalculationParameter.builder()
                .serviceId(request.getServiceId())
                .isHotbill(true)
                .build();

        System.out.println("  [v0.03 Controller] -> Calling CalculationService (v0.02 Engine)...");

        Bill billResult = calculationService.calculate(param);

        System.out.println("  [v0.03 Controller] === Returning Bill Response ===");

        return billResult;
    }
}
