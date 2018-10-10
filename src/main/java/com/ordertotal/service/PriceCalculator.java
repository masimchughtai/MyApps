package com.ordertotal.service;

import com.ordertotal.model.Items;
import com.ordertotal.model.MarkdownDeals;
import com.ordertotal.model.MarkdownObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class PriceCalculator {
    //MathContext mc = new MathContext(2);
    public BigDecimal calculatePriceForItemByCount(Items scannedItem, int newItemCount){
        BigDecimal unitPrice = scannedItem.price;

        BigDecimal totalPrice = (unitPrice.subtract(applicableMarkdownPriceForItemByCount(scannedItem)))
                .multiply(new BigDecimal((newItemCount)))
                .setScale(2,RoundingMode.CEILING);

        return totalPrice;
    }

    public BigDecimal calculatePriceForItemByWeight(Items byWeightItem, float weight){

        BigDecimal pricePerPound = byWeightItem.price;
        BigDecimal totalPrice = (pricePerPound.multiply(new BigDecimal((weight)))).setScale(2, RoundingMode.CEILING);

        return totalPrice;
    }

    private BigDecimal applicableMarkdownPriceForItemByCount(Items scannedItem){
        BigDecimal applicableMarkdownPrice = new BigDecimal("0");

        Date currentTimestamp = new Date();

        MarkdownDeals markdownDeal = MarkdownDeals.getInstance();

        if(markdownDeal.markdownDeals.size()>0) {
            for (MarkdownObject object : markdownDeal.markdownDeals) {
                if (object.getItem().equals(scannedItem) && currentTimestamp.after(object.getStartDate()) && currentTimestamp.before(object.getEndDate())) {
                    applicableMarkdownPrice = (object.getMarkdownPrice()).setScale(2, RoundingMode.CEILING);
                    break;
                }
            }
        }
        return applicableMarkdownPrice;
    }
}
