package com.nova.billing.core.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillingSubject {

    private Long customerId;
    
    private List<SubscriptionProduct> products = new ArrayList<>();

    //계약(상품가입)정보

    //할인정보
    //일회성정보
    //통화자료, 종량사용내역
    //단말기
    //등
    
    public void addProduct(SubscriptionProduct product) {
        this.products.add(product);
    }
}
