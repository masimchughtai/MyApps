package com.ordertotal.controllers;

import com.ordertotal.model.*;
import com.ordertotal.service.PriceCalculator;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/scan-item")
public class ScanItemByCount {

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
        //BigDecimal currentTotalPriceOfThisScannedItem = cart.itemsInCart.get(scannedItem).getTotalItemPrice();
        updateItemCountAndPrice(scannedItem, currentCountOfThisScannedItem, cart);
    }

    private void updateItemCountAndPrice(Items scannedItem, int currentCountOfThisScannedItem, Cart cart){
        int newItemCount = currentCountOfThisScannedItem+1;

        PriceCalculator calculator = new PriceCalculator();
        //BigDecimal newPrice = calculator.calculatePriceForItemByCount(scannedItem, newItemCount);
        BigDecimal newPrice = calculator.calculateItemPriceWithApplicableMarkdownAndDealsForItemByCount(scannedItem, newItemCount);
        ScannedItemCheckoutDetail newItemDetail = new ScannedItemCheckoutDetail(newItemCount, newPrice);

        cart.itemsInCart.put(scannedItem, newItemDetail);
    }

    private void addNewItemToCart(Items scannedItem, Cart cart) {
        int itemCount = 1;

        PriceCalculator calculator = new PriceCalculator();
        BigDecimal itemPrice = calculator.calculatePriceForItemByCount(scannedItem, itemCount);

        ScannedItemCheckoutDetail itemDetail = new ScannedItemCheckoutDetail(itemCount, itemPrice);

        cart.itemsInCart.put(scannedItem, itemDetail);
    }

}
