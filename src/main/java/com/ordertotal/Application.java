package com.ordertotal;

import com.ordertotal.model.Cart;
import com.ordertotal.model.CheckoutDetail;
import com.ordertotal.model.Items;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.ordertotal.model.Items.GROUND_BEAF;
import static com.ordertotal.model.Items.SOUP;

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

        CheckoutDetail detail = new CheckoutDetail(1,new BigDecimal("1.89"));
        c.itemsInCart.put(Items.SOUP,detail);

        CheckoutDetail detail2 = new CheckoutDetail(1,new BigDecimal("5.99"));
        d.itemsInCart.put(Items.GROUND_BEAF,detail2);

        CheckoutDetail detail3 = new CheckoutDetail(2, new BigDecimal(3.78));
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
