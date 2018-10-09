package com.ordertotal.controllers;

import com.ordertotal.model.Cart;
import com.ordertotal.model.CheckoutDetail;
import com.ordertotal.model.Items;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/scan-item")
public class ScanItemController {

    @RequestMapping(value="/eaches/{item}",method = RequestMethod.POST, produces = {"application/JSON"})
    @ResponseBody
        public Map scanEachesItem(@PathVariable("item") Items scannedItem) {
        Cart cart = Cart.getInstance();

        if (cart.itemsInCart.containsKey(scannedItem)){
            CheckoutDetail existingItemDetail = getExistingItemCountAndTotalItemPriceFromCart(scannedItem, cart);
            addScannedItemToExistingItemDetail(scannedItem,existingItemDetail,cart);
            return cart.itemsInCart;
        }
        else{
            CheckoutDetail itemDetail = new CheckoutDetail(1, scannedItem.price);
            cart.itemsInCart.put(scannedItem, itemDetail);
            return cart.itemsInCart;
        }
    }

    @RequestMapping(value="/by-weight/{item}/{weight}",
            method = RequestMethod.POST,
            produces = {"application/JSON"})
    @ResponseBody
    public Map scanItemByWeight(@PathVariable("item") Items byWeightItem,
                                @PathVariable("weight") float weight ){

        return null;
    }


    private CheckoutDetail getExistingItemCountAndTotalItemPriceFromCart(@PathVariable("item") Items scannedItem, Cart cart) {
        int currentCountOfThisScannedItem = cart.itemsInCart.get(scannedItem).getNumberOfItems();
        BigDecimal currentTotalPriceOfThisScannedItem = cart.itemsInCart.get(scannedItem).getTotalItemPrice();

        return new CheckoutDetail(currentCountOfThisScannedItem, currentTotalPriceOfThisScannedItem);
    }

    private void addScannedItemToExistingItemDetail(Items scannedItem,CheckoutDetail existingItemDetail, Cart cart){
        BigDecimal scannedItemPrice = scannedItem.price;
        CheckoutDetail newItemDetail = new CheckoutDetail(existingItemDetail.getNumberOfItems()+1,
                                            existingItemDetail.getTotalItemPrice().add(scannedItemPrice));
        cart.itemsInCart.put(scannedItem, newItemDetail);
    }

    @RequestMapping(value="/get-item-detail", method = RequestMethod.GET, produces ={"application/JSON"})
    public Map itemDetailInJson(){
        Cart cart = Cart.getInstance();

        return cart.itemsInCart;
    }
}
