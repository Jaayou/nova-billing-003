package com.nova.billing.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [v0.00] 요금 계산 '출력' DTO (ChargeItem 리스트 소유)
 */
@Data
@Builder // DTO 생성을 쉽게 하기 위해 빌더 패턴 사용
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    private String serviceId; // 서비스 관리 번호
    private String message;   // 계산 결과 메시지

    /**
     * [NPE 방지] @Builder.Default는 빌더 사용 시 이 필드를 null이 아닌 빈 리스트로 초기화
     */
    @Builder.Default
    private List<ChargeItem> chargeItems = new ArrayList<>();

    // --- 헬퍼(Helper) 메소드 ---

    /**
     * 과금(Charge) 항목을 리스트에 추가합니다. (금액은 양수로)
     */
    public void addCharge(String chargeName, BigDecimal chargeAmount) {
        if (chargeAmount != null && chargeAmount.compareTo(BigDecimal.ZERO) != 0) {
            this.chargeItems.add(new ChargeItem(chargeName, chargeAmount));
        }
    }

    /**
     * 할인(Discount) 항목을 리스트에 추가합니다. (금액은 음수로 변환)
     */
    public void addDiscount(String discountName, BigDecimal discountAmount) {
        if (discountAmount != null && discountAmount.compareTo(BigDecimal.ZERO) != 0) {
            // [중요] 할인은 '음수' 금액을 가진 ChargeItem으로 저장
            this.chargeItems.add(new ChargeItem(discountName, discountAmount.negate()));
        }
    }

    // --- 계산(Getter) 메소드 ---

    /**
     * '총 청구 금액'을 실시간으로 계산하여 반환합니다.
     * (chargeItems 리스트의 모든 amount를 합산)
     */
    public BigDecimal getTotalAmount() {
        // stream()을 사용하여 리스트 내의 모든 ChargeItem의 amount를 합산
        return chargeItems.stream()
                .map(ChargeItem::getAmount)         // ChargeItem 객체에서 금액(BigDecimal)만 추출
                .reduce(BigDecimal.ZERO, BigDecimal::add); // 모든 금액을 합산 (초기값 0)
    }
}