package com.ordertotal.controllers;

import com.ordertotal.model.SpecialDeals;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/special-deals")
@ResponseBody
public class ViewExistingSpecialDeals {

    @RequestMapping(value = "/view-special-deals",method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public SpecialDeals viewSpecialDeals(){
        return SpecialDeals.getInstance();
    }
}
