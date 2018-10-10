package com.ordertotal;

import com.ordertotal.model.Cart;
import com.ordertotal.model.ScannedItemCheckoutDetail;
import com.ordertotal.model.Items;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootApplication
public class Application {

    public static void main(String[] Args){
        SpringApplication.run(Application.class, Args);
        //printing();
    }

    static void printing() {

        Cart c = Cart.getInstance();
        Cart d = Cart.getInstance();
        Cart e = Cart.getInstance();

        ScannedItemCheckoutDetail detail = new ScannedItemCheckoutDetail(1,new BigDecimal("1.89"));
        c.itemsInCart.put(Items.SOUP,detail);

        ScannedItemCheckoutDetail detail2 = new ScannedItemCheckoutDetail(1,new BigDecimal("5.99"));
        d.itemsInCart.put(Items.GROUND_BEEF,detail2);

        ScannedItemCheckoutDetail detail3 = new ScannedItemCheckoutDetail(2, new BigDecimal(3.78));
        e.itemsInCart.put(Items.SOUP,detail3);

        System.out.println(c.itemsInCart.size());

        BigDecimal priceC = new BigDecimal("0.00");
        BigDecimal priceD = new BigDecimal("0.00");
        BigDecimal priceE = new BigDecimal("0.00");

        for(Items keyx : c.itemsInCart.keySet()){
            priceC = priceC.add(c.itemsInCart.get(keyx).getTotalItemPrice());
        }

        System.out.println(priceC.setScale(2, RoundingMode.CEILING));

        for(Items keyx : d.itemsInCart.keySet()){

            priceD = priceD.add(d.itemsInCart.get(keyx).getTotalItemPrice());
        }

        System.out.println(priceD.setScale(2, RoundingMode.CEILING));

        for(Items keyx : e.itemsInCart.keySet()){

            priceE = priceE.add(e.itemsInCart.get(keyx).getTotalItemPrice());
        }

        System.out.println(priceE.setScale(2, RoundingMode.CEILING));

    }
}
