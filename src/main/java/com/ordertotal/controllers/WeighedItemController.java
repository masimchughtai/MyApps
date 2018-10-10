package com.ordertotal.controllers;

import com.ordertotal.model.Cart;
import com.ordertotal.model.Items;
import com.ordertotal.model.ResponseEntity;
import com.ordertotal.model.WeighedItemCheckoutDetail;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scan-item")
public class WeighedItemController {

    @RequestMapping(value="/by-weight/{item}/{weight}",
            method = RequestMethod.POST,
            produces = {"application/JSON"})
    @ResponseBody
    public List<Map> scanItemByWeight(@PathVariable("item") Items byWeightItem,
                                      @PathVariable("weight") float weight )
    {

        Cart cart = Cart.getInstance();
        if(cart.weighedItemsInCart.containsKey(byWeightItem)){
            WeighedItemCheckoutDetail currentWeightAndPriceOfWeightedItem = getCurrentWeightAndPriceOfWeightedItem(byWeightItem,cart);
            addByWeightItemToCurrentDetail(byWeightItem, currentWeightAndPriceOfWeightedItem, cart,weight);
        }else {
            BigDecimal priceByWeight = (byWeightItem.price.multiply(new BigDecimal(Float.toString(weight)))).setScale(2, RoundingMode.CEILING);
            WeighedItemCheckoutDetail weighedItemDetail = new WeighedItemCheckoutDetail(weight, priceByWeight);
            cart.weighedItemsInCart.put(byWeightItem, weighedItemDetail);
        }

        ResponseEntity response = new ResponseEntity();
        return response.getServiceResponse();
    }


    private WeighedItemCheckoutDetail getCurrentWeightAndPriceOfWeightedItem(Items byWeightItem, Cart cart){
        WeighedItemCheckoutDetail currentWeighedItemDetail = cart.weighedItemsInCart.get(byWeightItem);
        return currentWeighedItemDetail;
    }

    private void addByWeightItemToCurrentDetail(Items byWeightItem,
                                                WeighedItemCheckoutDetail currentWeightAndPriceOfWeightedItem,
                                                Cart cart,float weight)
    {
        float newWeight = currentWeightAndPriceOfWeightedItem.getWeight()+weight;
        BigDecimal newPrice = (byWeightItem.price.multiply(new BigDecimal(Float.toString(newWeight)))).setScale(2, RoundingMode.CEILING);
        WeighedItemCheckoutDetail newWeightedItemDetail = new WeighedItemCheckoutDetail(newWeight,newPrice);
        cart.weighedItemsInCart.put(byWeightItem,newWeightedItemDetail);
    }

}
