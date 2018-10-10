package com.ordertotal.service;

import com.ordertotal.model.Items;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    public BigDecimal calculatePriceForItemByCount(Items scannedItem, int newItemCount){
    BigDecimal unitPrice = scannedItem.price;
    BigDecimal totalPrice = (unitPrice.multiply(new BigDecimal((newItemCount)))).setScale(2, RoundingMode.CEILING);
        return totalPrice;
    }

    public BigDecimal calculatePriceForItemByWeight(Items byWeightItem, float weight){

        BigDecimal pricePerPound = byWeightItem.price;
        BigDecimal totalPrice = (pricePerPound.multiply(new BigDecimal((weight)))).setScale(2, RoundingMode.CEILING);

        return totalPrice;
    }
}
