package com.ordertotal.controllers;

import com.ordertotal.model.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/scan-item")
public class ScanItemController {

    @RequestMapping(value="/eaches/{item}",method = RequestMethod.POST, produces = {"application/JSON"})
    @ResponseBody
        public List scanEachesItem(@PathVariable("item") Items scannedItem) {
        Cart cart = Cart.getInstance();

        if (cartAlreadyHasTheItem(scannedItem, cart)){
            addScannedItemToExistingScannedItemsMap(scannedItem,cart);
        }
        else{
            addNewItemToCart(scannedItem, cart);
        }

        ResponseEntity response = new ResponseEntity();
        return response.getServiceResponse();
    }

    private boolean cartAlreadyHasTheItem(@PathVariable("item") Items scannedItem, Cart cart) {
        return cart.itemsInCart.containsKey(scannedItem);
    }

    private void addScannedItemToExistingScannedItemsMap(Items scannedItem, Cart cart) {
        int currentCountOfThisScannedItem = cart.itemsInCart.get(scannedItem).getNumberOfItems();
        BigDecimal currentTotalPriceOfThisScannedItem = cart.itemsInCart.get(scannedItem).getTotalItemPrice();
        updateItemCountAndPrice(scannedItem,currentCountOfThisScannedItem, currentTotalPriceOfThisScannedItem, cart);
    }

    private void updateItemCountAndPrice(Items scannedItem, int currentCountOfThisScannedItem, BigDecimal currentTotalPriceOfThisScannedItem, Cart cart){
        ScannedItemCheckoutDetail newItemDetail = new ScannedItemCheckoutDetail(currentCountOfThisScannedItem+1,
                currentTotalPriceOfThisScannedItem.add(scannedItem.price));
        cart.itemsInCart.put(scannedItem, newItemDetail);
    }

    private void addNewItemToCart(Items scannedItem, Cart cart) {
        ScannedItemCheckoutDetail itemDetail = new ScannedItemCheckoutDetail(1, scannedItem.price);
        cart.itemsInCart.put(scannedItem, itemDetail);
    }

}
