package com.nova.billing.core;

/**
 * [v0.03] 파이프라인의 가장 작은 작업 단위 (실무자)
 * (예: BaseFeeStep, DiscountStep, VatStep)
 *
 * @FunctionalInterface:
 * - 이 인터페이스가 '단 하나의' 추상 메소드만 갖도록 컴파일러가 강제
 * - 람다(Lambda) 표현식으로 구현 가능하도록 보장
 */
@FunctionalInterface
public interface CalculationStep {

    void execute(BillingContext context);
}
