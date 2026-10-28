package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE028 {
    @Test public void behavioralCase1() throws Exception {
        CurrencyConverter c=new CurrencyConverter(); assertEquals(85.0,c.convert(100,"USD","EUR"),1e-9); assertEquals(-1,c.convert(10,"USD","XYZ"),0);
    }
    @Test public void behavioralCase2() throws Exception {
        CurrencyConverter c=new CurrencyConverter(); assertTrue(c.addCurrencyRate("TEST",2)); assertFalse(c.addCurrencyRate("TEST",3)); assertTrue(c.updateCurrencyRate("TEST",4)); assertEquals(4,c.convert(1,"USD","TEST"),0);
    }
}
