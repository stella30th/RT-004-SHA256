package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE090 {
    @Test public void behavioralCase1() throws Exception {
        Warehouse w=new Warehouse(); w.addProduct(1,"A",5); assertEquals(5,w.getProductQuantity(1)); w.updateProductQuantity(1,3); assertEquals(3,w.getProductQuantity(1));
    }
}
