package com.nova.billing.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillingSubject {

    private List<SubContract> subContracts;

    //계약(상품가입)정보
    //할인정보
    //일회성정보
    //통화자료, 종량사용내역
    //단말기
    //등
    
}
