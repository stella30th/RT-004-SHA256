package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE070 {
    @Test public void behavioralCase1() throws Exception {
        ShoppingCart c=new ShoppingCart(); c.addItem("Apple",2.5,3); assertEquals(7.5,c.totalPrice(),0); assertEquals(3,c.viewItems().get("Apple").getQuantity()); c.removeItem("Apple",2); assertEquals(1,c.viewItems().get("Apple").getQuantity());
    }
}
