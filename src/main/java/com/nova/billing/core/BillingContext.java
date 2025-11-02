package com.nova.billing.core;

import com.nova.billing.domain.Bill;
import com.nova.billing.domain.CalculationParameter;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * [v0.02] 계산 컨텍스트 (작업 바구니)
 * - 계산에 필요한 모든 데이터(입력, 출력, 중간 데이터)를 담아서
 * 전략, 파이프라인, 스텝 간에 전달하는 객체입니다.
 */
@Data
@RequiredArgsConstructor
public class BillingContext {

    private final CalculationParameter param;

    private final Bill bill;
}
