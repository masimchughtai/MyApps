package com.ordertotal.service;

import com.ordertotal.model.*;

import javax.lang.model.element.Element;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class PriceCalculator {
    //MathContext mc = new MathContext(2);
    public BigDecimal calculatePriceForItemByCount(Items scannedItem, int newItemCount){
        BigDecimal unitPrice = scannedItem.price;
        BigDecimal totalPrice = (unitPrice.subtract(applicableMarkdownPriceFor(scannedItem)))
                .multiply(new BigDecimal((newItemCount)))
                .setScale(2,RoundingMode.CEILING);

        return totalPrice;
    }

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


    public BigDecimal calculateItemPriceWithApplicableMarkdownAndDealsForItemByCount(Items scannedItem, int newItemCount){
        BigDecimal unitPrice = scannedItem.price;
        BigDecimal newPrice=new BigDecimal("0");
        SpecialBuyMGetNOff specialDeal = new SpecialBuyMGetNOff();

        SpecialDeals buyMGetNItemsOff = SpecialDeals.getInstance();
        Date currentTimestamp = new Date();
        boolean specialDealisApplicable = false;

        if(buyMGetNItemsOff.buyM_getN_xPercentOff.size()>0){
// List<Object>
            // List<SpecialBuyMGetNOff>
            for(SpecialBuyMGetNOff deal : buyMGetNItemsOff.buyM_getN_xPercentOff){

                if(deal.getItem().equals(scannedItem) && (currentTimestamp.equals(deal.effectiveStartDate) || currentTimestamp.after(deal.effectiveStartDate)) && currentTimestamp.before(deal.effectiveEndDate) ){
                    specialDealisApplicable = true;
                    specialDeal = deal;
                    break;
                }
            }
        }

        int itemsSubjectToSpecialDeal=0;

        if(specialDealisApplicable){
            if(specialDeal.maxItemLimit==0 || newItemCount<(specialDeal.buyItems + specialDeal.getItemsOff)){
                itemsSubjectToSpecialDeal = 0;
            }else if(specialDeal.maxItemLimit<=newItemCount){
                itemsSubjectToSpecialDeal = specialDeal.maxItemLimit;
            }else{
                itemsSubjectToSpecialDeal = newItemCount-(newItemCount%(specialDeal.buyItems+specialDeal.getItemsOff));
            }

        }

        int itemsNotSubjectToSpecialDeal = newItemCount-itemsSubjectToSpecialDeal;

        int specialDealItemsSubjectToFullPrice = (itemsSubjectToSpecialDeal*(specialDeal.buyItems/(specialDeal.buyItems+specialDeal.getItemsOff)));
        int specialDealItemsSubjectToDiscountedPrice = (itemsSubjectToSpecialDeal*(specialDeal.getItemsOff/(specialDeal.buyItems+specialDeal.getItemsOff)));

        BigDecimal pricesSubjectToFullPrice = scannedItem.price
                .multiply(new BigDecimal(specialDealItemsSubjectToFullPrice))
                .setScale(2,RoundingMode.CEILING);

        BigDecimal pricesSubjectToDiscountedPrice = (scannedItem.price.subtract(scannedItem.price.multiply((new BigDecimal(specialDeal.percentageOff).divide(new BigDecimal("100")))))).setScale(2,RoundingMode.CEILING)
                .multiply(new BigDecimal(specialDealItemsSubjectToDiscountedPrice))
                .setScale(2,RoundingMode.CEILING);
        BigDecimal pricesSubjectToMarkdownPrice = (new BigDecimal(itemsNotSubjectToSpecialDeal))
                .multiply((scannedItem.price).subtract(applicableMarkdownPriceFor(scannedItem)));

        newPrice = pricesSubjectToFullPrice
                .add(pricesSubjectToDiscountedPrice)
                .add(pricesSubjectToMarkdownPrice);
        return newPrice;
    }
}
