package com.nova.billing.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [v0.00] 청구 항목(기본료, 할인 등)을 나타내는 DTO
 * (Bill 객체가 이 객체의 List를 소유합니다)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeItem {
    
    /**
     * 요금 항목명 (예: "무선 기본료", "약정 할인")
     */
    private String itemName;

    /**
     * 항목 금액
     * - 과금(기본료 등)은 양수 (+)
     * - 할인(약정 할인 등)은 음수 (-)
     */
    private BigDecimal amount;
}