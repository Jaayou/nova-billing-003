package com.nova.billing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [v0.00] 요금 계산에 필요한 '입력' 파라미터 DTO
 */
@Data
@Builder // DTO 생성을 쉽게 하기 위해 빌더 패턴 사용
@NoArgsConstructor
@AllArgsConstructor
public class CalculationParameter {
    
    /**
     * 서비스 관리 번호 (예: "SVC_WL_001")
     * (DB 조회를 대신할 하드코딩 로직의 Key로 사용)
     */
    private String serviceId;

    /**
     * 핫빌 여부 (true: 핫빌, false: 정기배치)
     * (v0.00에서는 사용하지 않지만, 향후 확장을 위해 미리 정의)
     */
    private boolean isHotbill;

    public DomainType getDomainType() {
        if (this.serviceId != null && this.serviceId.contains("WL")) {
            return DomainType.WIRELESS;
        }
        if (this.serviceId != null && this.serviceId.contains("WD")) {
            return DomainType.WIRED;
        }
        throw new IllegalArgumentException("Unsupported Domain: " + this.serviceId);
    }
}