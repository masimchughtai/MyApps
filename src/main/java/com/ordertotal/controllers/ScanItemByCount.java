package com.ordertotal.controllers;

import com.ordertotal.model.*;
import com.ordertotal.service.PriceCalculatorForItemsByCount;
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

        if (cart.allItemsList.size()==0){
            cart.gTotal.put("Grand Total: ", new BigDecimal("0.00"));
        }

        if (!cartAlreadyHasTheItem(scannedItem, cart)){
            ScannedItemCheckoutDetail initialItemDetail = new ScannedItemCheckoutDetail(0,new BigDecimal(0));
            cart.itemsInCart.put(scannedItem, initialItemDetail);
        }

        addScannedItemToExistingScannedItemsMap(scannedItem,cart);
        updateGrandTotal(cart);

        ResponseEntity response = new ResponseEntity();
        return response.getServiceResponse();
    }

    private boolean cartAlreadyHasTheItem(@PathVariable("item") Items scannedItem, Cart cart) {
        return cart.itemsInCart.containsKey(scannedItem);
    }

    private void addScannedItemToExistingScannedItemsMap(Items scannedItem, Cart cart) {
        int currentCountOfThisScannedItem = cart.itemsInCart.get(scannedItem).getNumberOfItems();
        updateItemCountAndPrice(scannedItem, currentCountOfThisScannedItem, cart);


    }

    private void updateItemCountAndPrice(Items scannedItem, int currentCountOfThisScannedItem, Cart cart){
        int newItemCount = currentCountOfThisScannedItem+1;

        PriceCalculatorForItemsByCount calculator = new PriceCalculatorForItemsByCount();
        BigDecimal newPrice = calculator.calculateTotalPriceForScannedItems(scannedItem, newItemCount);
        ScannedItemCheckoutDetail newItemDetail = new ScannedItemCheckoutDetail(newItemCount, newPrice);

        cart.itemsInCart.put(scannedItem, newItemDetail);



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
