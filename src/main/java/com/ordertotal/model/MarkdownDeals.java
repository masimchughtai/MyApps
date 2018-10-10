package com.ordertotal.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownDeals {
     private static MarkdownDeals markdown = new MarkdownDeals();

     public List<MarkdownObject> markdownDeals;

     private MarkdownDeals(){
         markdownDeals = new ArrayList<>();
     }

     public static MarkdownDeals getInstance(){
         return markdown;
    }

}
