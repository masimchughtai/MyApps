package com.ordertotal.model;

import java.math.BigDecimal;

public class ScannedItemCheckoutDetail {
    private int numberOfItems;
    private BigDecimal totalItemPrice;

    public ScannedItemCheckoutDetail(int numberOfItems, BigDecimal totalItemPrice){
        this.numberOfItems = numberOfItems;
        this.totalItemPrice = totalItemPrice;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public BigDecimal getTotalItemPrice() {
        return totalItemPrice;

    }
}
