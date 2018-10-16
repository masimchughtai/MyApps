package com.ordertotal;

import com.ordertotal.model.Cart;
import com.ordertotal.model.ScannedItemCheckoutDetail;
import com.ordertotal.model.Items;
import com.ordertotal.service.PriceCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootApplication
public class Application {

    public static void main(String[] Args){
        SpringApplication.run(Application.class, Args);

    }

}
