package com.ordertotal.model;

import java.util.ArrayList;
import java.util.List;

public class SpecialDeals {

    private static SpecialDeals ourInstance = new SpecialDeals();

    public static List<SpecialBuyMGetNOff> buyM_getN_xPercentOff;
    public static List<Object> buyM_for_yDollars;

    private SpecialDeals() {
        buyM_getN_xPercentOff= new ArrayList<>();
        buyM_for_yDollars=new ArrayList<>();
    }

    public static SpecialDeals getInstance() {
        return ourInstance;
    }

    public List<SpecialBuyMGetNOff> addDeal_buyM_getN_xPercentOff(SpecialBuyMGetNOff buyMGetNOff){
        buyM_getN_xPercentOff.add(buyMGetNOff);
        return buyM_getN_xPercentOff;
    }

    public List<SpecialBuyMGetNOff> viewDeals_buyM_getN_xPercentOff(){
        return buyM_getN_xPercentOff;
    }


}
