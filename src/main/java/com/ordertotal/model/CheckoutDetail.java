package com.ordertotal.model;

import java.math.BigDecimal;

public class CheckoutDetail {
    private int numberOfItems;
    private BigDecimal totalItemPrice;

     public CheckoutDetail(int numberOfItems, BigDecimal totalItemPrice){
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
