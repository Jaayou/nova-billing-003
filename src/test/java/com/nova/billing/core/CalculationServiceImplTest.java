package com.nova.billing.core;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;
import com.nova.billing.domain.ChargeItem;

@SpringBootTest
class CalculationServiceImplTest {

    @Autowired
    private CalculationService calculationService;

    private String prettyPrint(Bill bill) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill Summary {\n");
        sb.append(String.format("  Service ID: %s\n", bill.getServiceId()));
        sb.append(String.format("  Message: %s\n", bill.getMessage()));
        sb.append("  Charge Items:\n");

        if (bill.getChargeItems() == null || bill.getChargeItems().isEmpty()) {
            sb.append("    (No items)\n");
        } else {
            for (ChargeItem item : bill.getChargeItems()) {
                sb.append(String.format("    - %s: %,.0f\n",
                        item.getItemName(), item.getAmount()));
            }
        }
        sb.append("  ------------------\n");
        sb.append(String.format("  Total Amount: %,.0f\n", bill.getTotalAmount()));
        sb.append("}");
        return sb.toString();
    }

    @Test
    @DisplayName("[Test] Wireless (WL) customer")
    void testCalculate_Wireless() {
        // 1. Arrange
        CalculationParameter param = CalculationParameter.builder()
                .serviceId("SVC_WL_005") // "WL"
                .isHotbill(false)
                .build();

        // 2. Act
        Bill result = calculationService.calculate(param);

        // 3. Assert

        System.out.println("\n--- [TEST: Wireless] ---");
        System.out.println(prettyPrint(result));
        System.out.println("------------------------");

        assertNotNull(result, "Bill object should not be null.");

        assertEquals(6, result.getChargeItems().size(), "Should have 1 charge item.");

        // assertEquals("Wireless Base Fee",
        // result.getChargeItems().get(0).getItemName());
        assertEquals("Wireless Base Fee (Lite)", result.getChargeItems().get(0).getItemName());

        assertEquals(0, result.getTotalAmount().compareTo(new BigDecimal("67000")));
    }

    @Test
    @DisplayName("[Test] Wired (WD) customer")
    void testCalculate_Wired() {
        // 1. Arrange
        CalculationParameter param = CalculationParameter.builder()
                .serviceId("SVC_WD_002") // "WD"
                .isHotbill(false)
                .build();

        // 2. Act
        Bill result = calculationService.calculate(param);

        // 3. Assert
        System.out.println("\n--- [TEST: Wired] ---");
        System.out.println(prettyPrint(result));
        System.out.println("---------------------");

        assertNotNull(result);
        assertEquals(1, result.getChargeItems().size());

        assertEquals("Wired Base Fee", result.getChargeItems().get(0).getItemName());

        assertEquals(0, result.getTotalAmount().compareTo(new BigDecimal("45000")));
    }
}