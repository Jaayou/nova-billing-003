package com.nova.billing.api;

import com.nova.billing.domain.DomainType;

import lombok.Data;

@Data
public class HotbillRequest {

    private String callerId; //호출 프로그램, 호출자 ID 등 식별용
    private String serviceId; //대표 (회선)메인계약(As-is 서비스관리번호)
    private DomainType domainType; //무선, 유선, 구독 등
    private String operationType; //작업유형
    private boolean isHotbill; //핫빌여부
    private String calculationStartDate; //계산시작일
    private String calculationEndDate; //계산종료일
    private String invoiceDate; //청구일자
    
}
