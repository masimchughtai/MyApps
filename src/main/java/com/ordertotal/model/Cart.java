package com.ordertotal.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart cart = new Cart();

    public Map<Items,CheckoutDetail> itemsInCart;


    private Cart(){
        itemsInCart = new HashMap();
    }

    public static Cart getInstance(){
        return cart;
    }

}
