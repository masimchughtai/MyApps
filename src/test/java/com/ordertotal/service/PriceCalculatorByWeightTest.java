package com.ordertotal.service;

import com.ordertotal.model.Items;
import com.ordertotal.model.SpecialBuyMGetNOff;
import com.ordertotal.model.SpecialDeals;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PriceCalculatorByWeightTest {
/*
    @Test
    public void getItemsSubjectToSpecialDealTest() throws ParseException {

        SpecialBuyMGetNOff special_deal = getSpecialDealObject();
        int newItemCount = 10;

        PriceCalculator itemsValidForDeal = new PriceCalculator();
        int itemsSubjectToSpecialDeal = itemsValidForDeal.getItemsSubjectToSpecialDeal(newItemCount,special_deal);
        assertEquals(6,itemsSubjectToSpecialDeal);
    }

    @Test
    public void getTotalPriceOfItemsSubjectToDealTest() throws ParseException {
        PriceCalculator totalPriceForSpecialDealItems = new PriceCalculator();
        SpecialBuyMGetNOff special_deal2 = getSpecialDealObject();
        BigDecimal totalPrice = totalPriceForSpecialDealItems.getTotalPriceOfItemsSubjectToDeal(Items.SOUP, 4,2,special_deal2);
        assertEquals(new BigDecimal("9.84"),totalPrice); // should be 4.91
    }

    @Ignore
    @Test
    public void calculateItemPriceWithApplicableMarkdownAndDealsForItemByCountTest(){
        PriceCalculator totalPrice = new PriceCalculator();
        BigDecimal itemTotalPrice = totalPrice.calculateItemPriceWithApplicableMarkdownAndDealsForItemByCount(Items.SOUP,3);
        assertEquals(new BigDecimal("5.67"),itemTotalPrice);
    }

    @Ignore
    @Test
    public void isSpecialDealApplicableToScannedItemTest()throws Exception {
        SpecialBuyMGetNOff specialBuy = getSpecialDealObject();

        SpecialDeals specialDeal = SpecialDeals.getInstance();
        specialDeal.buyM_for_yDollars.add(specialBuy);

        PriceCalculator validateDeal = new PriceCalculator();
        assertTrue(validateDeal.isSpecialDealApplicableToScannedItem(Items.SOUP));

    }
*/
    public SpecialBuyMGetNOff getSpecialDealObject() throws ParseException {
        SpecialBuyMGetNOff specialDeal = new SpecialBuyMGetNOff();
        specialDeal.setItem(Items.SOUP);
        specialDeal.setBuyItems(2);
        specialDeal.setGetItemsOff(1);
        specialDeal.setPercentageOff(40.00f);
        specialDeal.setMaxItemLimit(6);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String startStringDate = "2018-10-19 00:00:00";
        Date startDate = sdf.parse(startStringDate);
        String endStringDate = "2018-10-25 00:00:00";
        Date endDate = sdf.parse(endStringDate);

        specialDeal.setEffectiveStartDate(startDate);
        specialDeal.setEffectiveEndDate(endDate);
        return specialDeal;
    }

}
