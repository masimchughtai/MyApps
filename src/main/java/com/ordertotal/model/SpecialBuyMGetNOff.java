package com.ordertotal.model;

import lombok.Data;

import java.util.Date;

@Data
public class SpecialBuyMGetNOff {

    public Items item;
    public int buyItems;
    public int getItemsOff;
    public float percentageOff;
    public int maxItemLimit;
    public Date effectiveStartDate;
    public Date effectiveEndDate;

public SpecialBuyMGetNOff(){

}

/*
    public SpecialBuyMGetNOff(Items item,
                              int byeItems,
                              int getItemsOff,
                              float percentageOff,
                              int maxItemLimit,
                              Date effectiveStartDate,
                              Date effectiveEndDate)
    {
    this.item=item;
    this.buyItems =byeItems;
    this.getItemsOff=getItemsOff;
    this.percentageOff=percentageOff;
    this.maxItemLimit=maxItemLimit;
    this.effectiveStartDate=effectiveStartDate;
    this.effectiveEndDate=effectiveEndDate;
    }
*/


}
