package com.ordertotal.service;

import com.ordertotal.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class PriceCalculatorByWeight {


    public BigDecimal calculatePriceForItemByWeight(Items byWeightItem, float weight){

        BigDecimal pricePerPound = byWeightItem.price;
        BigDecimal totalPrice = (pricePerPound.subtract(applicableMarkdownPriceFor(byWeightItem))).
        multiply(new BigDecimal((weight))).setScale(2, RoundingMode.CEILING);

        return totalPrice;
    }


    private BigDecimal applicableMarkdownPriceFor(Items scannedItemByCountOrByWeight){
        BigDecimal applicableMarkdownPrice = new BigDecimal("0");

        Date currentTimestamp = new Date();

        MarkdownDeals markdownDeal = MarkdownDeals.getInstance();

        if(markdownDeal.markdownDeals.size()>0) {
            for (MarkdownObject object : markdownDeal.markdownDeals) {
                if (object.getItem().equals(scannedItemByCountOrByWeight) && currentTimestamp.after(object.getStartDate()) && currentTimestamp.before(object.getEndDate())) {
                    applicableMarkdownPrice = (object.getMarkdownPrice()).setScale(2, RoundingMode.CEILING);
                    break;
                }
            }
        }
        return applicableMarkdownPrice;
    }

}
