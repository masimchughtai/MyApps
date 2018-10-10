package com.ordertotal.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MarkdownResponseEntity {
    String msg = "Markdown created successfully.";
    Items item;
    BigDecimal markdownPrice;
    Date startDate;
    Date endDate;
}
