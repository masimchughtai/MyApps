package com.ordertotal.model;

import java.math.BigDecimal;

public enum Items {

    SOUP(new BigDecimal("1.89"), "byCount"),
    BANANA(new BigDecimal("0.69"), "byWeight"),
    GROUND_BEEF(new BigDecimal("3.99"), "byWeight");

    Items(BigDecimal price, String itemType){
        this.price = price;
        this.itemType = itemType;
    }

    public String itemType;
    public BigDecimal price;
}
