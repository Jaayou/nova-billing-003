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

/**
 * [v0.00-eng] CalculationService의 동작을 검증하는 테스트 (영문)
 */
@SpringBootTest
class CalculationServiceImplTest {

    @Autowired
    private CalculationService calculationService;

    @Test
    @DisplayName("[v0.00-eng] Wireless (WL) customer")
    void testCalculate_Wireless() {
        // 1. Arrange
        CalculationParameter param = CalculationParameter.builder()
                .serviceId("SVC_WL_005") // "WL"
                .isHotbill(false)
                .build();

        // 2. Act
        Bill result = calculationService.calculate(param);

        // 3. Assert
        
        // [System.out] 테스트 결과(Bill 객체)를 콘솔에 출력 (영문)
        System.out.println("\n--- [TEST: Wireless] ---");
        System.out.println(result.toString());
        System.out.println("------------------------");

        // [검증 1]
        assertNotNull(result, "Bill object should not be null.");
        
        // [검증 2]
        assertEquals(6, result.getChargeItems().size(), "Should have 1 charge item.");
        
        // [검증 3] (영문)
        assertEquals("Wireless Base Fee", result.getChargeItems().get(0).getItemName());
        
        // [검증 4]
        assertEquals(0, result.getTotalAmount().compareTo(new BigDecimal("92000")));
    }

    @Test
    @DisplayName("[v0.00-eng] Wired (WD) customer")
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
        System.out.println(result.toString());
        System.out.println("---------------------");
        
        assertNotNull(result);
        assertEquals(1, result.getChargeItems().size());
        
        // [검증 3] (영문)
        assertEquals("Wired Base Fee", result.getChargeItems().get(0).getItemName());
        
        // [검증 4]
        assertEquals(0, result.getTotalAmount().compareTo(new BigDecimal("45000")));
    }
}