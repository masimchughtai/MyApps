package com.ordertotal.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MarkdownRequestEntity {
    public Items item;
    public BigDecimal markdownPrice;
    public Date effectiveFrom;

}

/*
Sample request
{
  "item": "SOUP",
  "markdownPrice": 0.20,
  "effectiveFrom": "2018-10-10T00:00:00.000"
}
 */