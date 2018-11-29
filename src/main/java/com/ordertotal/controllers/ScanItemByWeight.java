package com.ordertotal.controllers;

import com.ordertotal.model.Cart;
import com.ordertotal.model.Items;
import com.ordertotal.model.ResponseEntity;
import com.ordertotal.model.WeighedItemCheckoutDetail;
import com.ordertotal.service.PriceCalculatorByWeight;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scan-item")
public class ScanItemByWeight {

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
            PriceCalculatorByWeight calculator1 = new PriceCalculatorByWeight();
            BigDecimal newPrice = calculator1.calculatePriceForItemByWeight(byWeightItem, weight);

            //BigDecimal priceByWeight = (byWeightItem.price.multiply(new BigDecimal(Float.toString(weight)))).setScale(2, RoundingMode.CEILING);
            WeighedItemCheckoutDetail weighedItemDetail = new WeighedItemCheckoutDetail(weight, newPrice);
            cart.weighedItemsInCart.put(byWeightItem, weighedItemDetail);
        }

        updateGrandTotal(cart);
        ResponseEntity response = new ResponseEntity();
        return response.getServiceResponse();
    }


    private WeighedItemCheckoutDetail getCurrentWeightAndPriceOfWeightedItem(Items byWeightItem, Cart cart){
        return cart.weighedItemsInCart.get(byWeightItem);
    }

    private void addByWeightItemToCurrentDetail(Items byWeightItem,
                                                WeighedItemCheckoutDetail currentWeightAndPriceOfWeightedItem,
                                                Cart cart,float weight)
    {
        float newWeight = currentWeightAndPriceOfWeightedItem.getWeight()+weight;

        PriceCalculatorByWeight calculator = new PriceCalculatorByWeight();
        BigDecimal newPrice = calculator.calculatePriceForItemByWeight(byWeightItem, newWeight);
        //BigDecimal newPrice = (byWeightItem.price.multiply(new BigDecimal(Float.toString(newWeight)))).setScale(2, RoundingMode.CEILING);
        WeighedItemCheckoutDetail newWeightedItemDetail = new WeighedItemCheckoutDetail(newWeight,newPrice);
        cart.weighedItemsInCart.put(byWeightItem,newWeightedItemDetail);
    }

    private void updateGrandTotal(Cart cart){
        BigDecimal grandTotal = new BigDecimal("0.00");

        for(Items itemByCount : cart.itemsInCart.keySet()){
            grandTotal = grandTotal.add(cart.itemsInCart.get(itemByCount).getTotalItemPrice());
        }

        for(Items itemByWeight : cart.weighedItemsInCart.keySet()){
            grandTotal = grandTotal.add(cart.weighedItemsInCart.get(itemByWeight).getTotalWeighedItemPrice());
        }

        cart.gTotal.put("Grand Total: ", grandTotal);
    }


}
