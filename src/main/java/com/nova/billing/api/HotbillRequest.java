package com.nova.billing.api;

import lombok.Data;

/**
 * [v0.03] Hotbill API 요청 (Request Body)을 위한 DTO
 * - API 외부 명세(Contract)는 내부 도메인(CalculationParameter)과
 * 분리하는 것이 좋습니다. (현재는 serviceId만 포함)
 */
@Data
public class HotbillRequest {

    private String serviceId;
}
