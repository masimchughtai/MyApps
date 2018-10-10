package com.ordertotal.model;

import java.util.*;

public class Cart {
    private static Cart cart = new Cart();

    public Map<Items,ScannedItemCheckoutDetail> itemsInCart;
    public Map<Items,WeighedItemCheckoutDetail> weighedItemsInCart;
    public List<Map> allItemsList;

    private Cart()
    {
        itemsInCart = new HashMap();
        weighedItemsInCart = new HashMap();
        allItemsList = new ArrayList<>();
    }

    public static Cart getInstance(){
        return cart;
    }
}
