package com.nova.billing.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    private String serviceId;
    private String message;

    @Builder.Default
    private List<ChargeItem> chargeItems = new ArrayList<>();

    public void addCharge(ChargeItem item) {
        if (item != null && item.amount().compareTo(BigDecimal.ZERO) != 0) {
            this.chargeItems.add(item);
        }
    }
    public void addCharge(String chargeName, BigDecimal chargeAmount) {
        if (chargeAmount != null && chargeAmount.compareTo(BigDecimal.ZERO) != 0) {
            this.chargeItems.add(new ChargeItem(chargeName, chargeAmount));
        }
    }

    public void addDiscount(String discountName, BigDecimal discountAmount) {
        if (discountAmount != null && discountAmount.compareTo(BigDecimal.ZERO) != 0) {
            this.chargeItems.add(new ChargeItem(discountName, discountAmount.negate()));
        }
    }

    public BigDecimal getTotalAmount() {
        return chargeItems.stream()
                .map(ChargeItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}