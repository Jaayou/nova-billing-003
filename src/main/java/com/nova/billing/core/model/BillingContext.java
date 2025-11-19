package com.nova.billing.core.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class BillingContext {

    //private Customer customer;
    private final CalculationParameter param;
    private final Bill bill;
    private final BillingSubject subject;

    public BillingContext(CalculationParameter param, BillingSubject subject, Bill bill) {
        this.param = param;
        this.subject = subject;
        this.bill = bill;
    }

    public List<SubscriptionProduct> getProducts() {
        return subject.getProducts();
    }

}
