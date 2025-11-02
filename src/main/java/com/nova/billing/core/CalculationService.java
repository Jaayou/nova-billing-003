package com.nova.billing.core;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;

/**
 * [v0.00] 요금 계산 핵심 엔진 인터페이스 ('약속')
 * - DIP 원칙에 따라, 모든 호출자(API, Batch)는 이 인터페이스에만 의존해야 합니다.
 * - 이 '약속'은 v0.01, v0.03으로 진화해도 '바뀌지 않습니다'.
 */
public interface CalculationService {

    /**
     * 주어진 파라미터를 기반으로 요금을 계산합니다.
     *
     * @param param 계산에 필요한 모든 정보 (하드코딩 Key 포함)
     * @return 계산 완료된 청구 결과 (Bill 객체)
     */
    Bill calculate(CalculationParameter param);
}