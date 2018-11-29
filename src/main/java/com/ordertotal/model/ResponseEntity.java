package com.ordertotal.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseEntity {
    Cart cart = Cart.getInstance();

    public List<Map> getServiceResponse(){

        cart.allItemsList.clear();

        if(cart.itemsInCart.size()>0){
            cart.allItemsList.add(cart.itemsInCart);
        }

        if(cart.weighedItemsInCart.size()>0){
            cart.allItemsList.add(cart.weighedItemsInCart);
        }

        cart.allItemsList.add(cart.gTotal);

        return cart.allItemsList;
    }

}
