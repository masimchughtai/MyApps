package com.ordertotal.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseEntity {
    Cart cart = Cart.getInstance();

    public List<Map> getServiceResponse(){
        BigDecimal grandTotal = new BigDecimal("0");

        cart.allItemsList.clear();

        if(cart.itemsInCart.size()>0){
            cart.allItemsList.add(cart.itemsInCart);

            for(Items item:cart.itemsInCart.keySet()){
                grandTotal.add(cart.itemsInCart.get(item).getTotalItemPrice());
            }
        }

        if(cart.weighedItemsInCart.size()>0){
            cart.allItemsList.add(cart.weighedItemsInCart);

            for(Items item:cart.weighedItemsInCart.keySet()){
                grandTotal.add(cart.weighedItemsInCart.get(item).getTotalWeighedItemPrice());
            }
        }

        Map<String,String> grandTotalMap = new HashMap();
        grandTotalMap.put("Grand total", grandTotal.toString());
        cart.allItemsList.add(grandTotalMap);

        return cart.allItemsList;
    }

}
