package com.ordertotal.model;

import java.util.ArrayList;
import java.util.List;

public class SpecialDeals {

    private static SpecialDeals ourInstance = new SpecialDeals();

    public List<SpecialBuyMGetNOff> buyM_getN_xPercentOff;
    public List<Object> buyM_for_yDollars;

    private SpecialDeals() {
        buyM_getN_xPercentOff= new ArrayList<>();
        buyM_for_yDollars=new ArrayList<>();
    }

    public static SpecialDeals getInstance() {
        return ourInstance;
    }

}
