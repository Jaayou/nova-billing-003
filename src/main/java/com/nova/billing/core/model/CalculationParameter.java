package com.nova.billing.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculationParameter {
    
    private String callerId; //호출 프로그램, 호출자 ID 등 식별용
    private String companyCd; //회사코드
    private String serviceId; //대표 (회선)메인계약(As-is 서비스관리번호)
    private DomainType domainType; //무선, 유선, 구독 등
    private String operationType; //작업유형: REGULAR, BILL_SHOCK 등
    private boolean isHotbill; //핫빌여부
    private String calculationStartDate; //계산시작일
    private String calculationEndDate; //계산종료일
    private String invoiceDate; //청구일자

    //테스트용 
    // public DomainType getDomainType() {
    //     if (this.serviceId != null && this.serviceId.contains("WL")) {
    //         return DomainType.WIRELESS;
    //     }
    //     if (this.serviceId != null && this.serviceId.contains("WD")) {
    //         return DomainType.WIRED;
    //     }
    //     throw new IllegalArgumentException("Unsupported Domain: " + this.serviceId);
    // }

    //테스트용
    public String getProductType() {
        if (this.serviceId.contains("001") || this.serviceId.contains("003")) {
            return "WL_STANDARD_PLAN";
        }
        if (this.serviceId.contains("005")) {
            return "WL_LITE_PLAN";
        }
        if (this.serviceId.contains("WD")) {
            return "WD_STANDARD_PLAN";
        }
        return "UNKNOWN";
    }
}