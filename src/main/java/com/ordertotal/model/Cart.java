package com.ordertotal.model;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private static Cart cart = new Cart();

    public static Map<Items,ScannedItemCheckoutDetail> itemsInCart = new HashMap();
    public static Map<Items,WeighedItemCheckoutDetail> weighedItemsInCart = new HashMap();
    public static List<Map> allItemsList = new ArrayList<>();
    public static Map<String,BigDecimal> gTotal = new HashMap();
/*
    private Cart()
    {
        itemsInCart = new HashMap();
        weighedItemsInCart = new HashMap();
        allItemsList = new ArrayList<>();
    }
*/

    public static Cart getInstance(){
        return cart;
    }

}
