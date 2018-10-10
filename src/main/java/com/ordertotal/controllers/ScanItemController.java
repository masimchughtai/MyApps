package com.ordertotal.controllers;

import com.ordertotal.model.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping("/scan-item")
public class ScanItemController {

    @RequestMapping(value="/eaches/{item}",method = RequestMethod.POST, produces = {"application/JSON"})
    @ResponseBody
        public List scanEachesItem(@PathVariable("item") Items scannedItem) {
        Cart cart = Cart.getInstance();

        if (cart.itemsInCart.containsKey(scannedItem)){
            ScannedItemCheckoutDetail existingItemDetail = getExistingItemCountAndTotalItemPriceFromCart(scannedItem, cart);
            addScannedItemToExistingItemDetail(scannedItem,existingItemDetail,cart);
        }
        else{
            ScannedItemCheckoutDetail itemDetail = new ScannedItemCheckoutDetail(1, scannedItem.price);
            cart.itemsInCart.put(scannedItem, itemDetail);
        }

        ResponseEntity response = new ResponseEntity();
        return response.getServiceResponse();
    }



    private ScannedItemCheckoutDetail getExistingItemCountAndTotalItemPriceFromCart(@PathVariable("item") Items scannedItem, Cart cart) {
        int currentCountOfThisScannedItem = cart.itemsInCart.get(scannedItem).getNumberOfItems();
        BigDecimal currentTotalPriceOfThisScannedItem = cart.itemsInCart.get(scannedItem).getTotalItemPrice();

        return new ScannedItemCheckoutDetail(currentCountOfThisScannedItem, currentTotalPriceOfThisScannedItem);
    }

    private void addScannedItemToExistingItemDetail(Items scannedItem, ScannedItemCheckoutDetail existingItemDetail, Cart cart){
        BigDecimal scannedItemPrice = scannedItem.price;
        ScannedItemCheckoutDetail newItemDetail = new ScannedItemCheckoutDetail(existingItemDetail.getNumberOfItems()+1,
                                            existingItemDetail.getTotalItemPrice().add(scannedItemPrice));
        cart.itemsInCart.put(scannedItem, newItemDetail);
    }

}
