package com.ordertotal.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MarkdownObject {
    Items item;
    BigDecimal markdownPrice;
    Date startDate;
    Date endDate;

}
