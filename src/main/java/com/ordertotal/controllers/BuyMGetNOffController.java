package com.ordertotal.controllers;

import com.ordertotal.model.SpecialBuyMGetNOff;
import com.ordertotal.model.SpecialDeals;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/special-deals")
public class BuyMGetNOffController {
  @RequestMapping(value="/buy-items-get-items-off/create",method = RequestMethod.POST)
    public SpecialBuyMGetNOff buyItemsGetItemsOff(@RequestBody SpecialBuyMGetNOff buyMGetNOff){
      //buyMGetNOff.getItem();
      SpecialDeals specialDeals = SpecialDeals.getInstance();
      specialDeals.buyM_getN_xPercentOff.add(buyMGetNOff);
      return buyMGetNOff;
  }

}
