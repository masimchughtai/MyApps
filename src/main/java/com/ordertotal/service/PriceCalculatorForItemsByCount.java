package com.ordertotal.service;

import com.ordertotal.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class PriceCalculatorForItemsByCount {

    private int dealItemsSubjectToFullPrice = 0;
    private int dealItemsSubjectToDiscountedPrice= 0;
    private int maximumDealItems = 0;
    private double applicableDiscount = 0.0;


    private int getCurrentItemCount(Items scannedItem){
        Cart cart = Cart.getInstance();
        return cart.itemsInCart.get(scannedItem).getNumberOfItems();
    }


    private BigDecimal getCurrentTotalItemPrice(Items scannedItem){
        Cart cart = Cart.getInstance();
        return cart.itemsInCart.get(scannedItem).getTotalItemPrice();
    }


    private BigDecimal getUnitPrice(Items scannedItem){
        return scannedItem.price;
    }


    private boolean specialDealApplicable(Items scannedItem) {
        SpecialDeals buyMGetNItemsOff = SpecialDeals.getInstance();
        Date currentTimestamp = new Date();
        boolean splDealisApplicable = false;

        if(buyMGetNItemsOff.buyM_getN_xPercentOff.size()>0){
            for(SpecialBuyMGetNOff deal : buyMGetNItemsOff.buyM_getN_xPercentOff){

                if(deal.getItem().equals(scannedItem)
                        && (currentTimestamp.equals(deal.effectiveStartDate) || currentTimestamp.after(deal.effectiveStartDate))
                        && currentTimestamp.before(deal.effectiveEndDate) ){
                    dealItemsSubjectToFullPrice = deal.buyItems;
                    dealItemsSubjectToDiscountedPrice = deal.getItemsOff;
                    applicableDiscount = ((deal.getPercentageOff())/100);
                    maximumDealItems = deal.maxItemLimit;
                    splDealisApplicable = true;
                    break;
                }
            }
        }
        return splDealisApplicable;
    }


    public BigDecimal calculateTotalPriceForScannedItems(Items scannedItem,int newItemCount){
        BigDecimal newPrice = new BigDecimal("0");

        BigDecimal currentItemTotal = getCurrentTotalItemPrice(scannedItem);

        if(specialDealApplicable(scannedItem)){

            int remainderItems = newItemCount%(dealItemsSubjectToFullPrice+dealItemsSubjectToDiscountedPrice);

            if(remainderItems > 0 && remainderItems <= dealItemsSubjectToFullPrice && newItemCount < maximumDealItems){

                newPrice = currentItemTotal.add(getUnitPrice(scannedItem));

            }else if((((remainderItems > dealItemsSubjectToFullPrice
                    && remainderItems< (dealItemsSubjectToFullPrice+dealItemsSubjectToDiscountedPrice))
                    || remainderItems==0)
                    && newItemCount <= maximumDealItems)
                    || maximumDealItems == 0){

                BigDecimal discountedPrice = (getUnitPrice(scannedItem)
                        .subtract(getUnitPrice(scannedItem).multiply(new BigDecimal(applicableDiscount))))
                        .setScale(2,RoundingMode.CEILING);

                newPrice = currentItemTotal.add(discountedPrice);

            }else if(newItemCount>maximumDealItems && maximumDealItems != 0){

                newPrice = currentItemTotal.add(getUnitPrice(scannedItem));
            }
        }else{
            newPrice = currentItemTotal.add(getUnitPrice(scannedItem));
        }
        return newPrice;
    }
}
