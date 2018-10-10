package com.ordertotal.model;

import java.math.BigDecimal;

public class WeighedItemCheckoutDetail {

    private float weight;
    private BigDecimal totalWeighedItemPrice;

    public WeighedItemCheckoutDetail(float weight, BigDecimal totalWeighedItemPrice) {
        this.weight = weight;
        this.totalWeighedItemPrice = totalWeighedItemPrice;
    }

    public float getWeight() {
        return weight;
    }

    public BigDecimal getTotalWeighedItemPrice() {
        return totalWeighedItemPrice;
    }
}
