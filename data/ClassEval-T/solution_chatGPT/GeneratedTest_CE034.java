package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE034 {
    @Test public void behavioralCase1() throws Exception {
        DiscountStrategy.Cart cart=new DiscountStrategy.Cart(new DiscountStrategy.Product("A",2,10)); DiscountStrategy d=new DiscountStrategy(new DiscountStrategy.Customer("x",1000),cart,DiscountStrategy.FIDELITY_PROMO); assertEquals(20,d.total(),0); assertEquals(19,d.due(),0);
    }
    @Test public void behavioralCase2() throws Exception {
        DiscountStrategy.Cart cart=new DiscountStrategy.Cart(new DiscountStrategy.Product("X",20,5)); DiscountStrategy d=new DiscountStrategy(new DiscountStrategy.Customer("x",0),cart,DiscountStrategy.BULK_ITEM_PROMO); assertEquals(90,d.due(),0);
    }
}
