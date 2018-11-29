package com.ordertotal.controllers;

import com.ordertotal.model.SpecialBuyMGetNOff;
import com.ordertotal.model.SpecialDeals;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/special-deals")
@ResponseBody
public class ViewExistingSpecialDeals {

    @RequestMapping(value = "/view-special-deals",method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public List<SpecialBuyMGetNOff> viewSpecialDeals(){
        SpecialDeals specialDeals = SpecialDeals.getInstance();
        List<SpecialBuyMGetNOff> listOfSpecialDeals = specialDeals.viewDeals_buyM_getN_xPercentOff();
        return listOfSpecialDeals;
    }
}
