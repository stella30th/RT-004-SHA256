package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE077 {
    @Test public void behavioralCase1() throws Exception {
        StockPortfolioTracker p=new StockPortfolioTracker(1000); assertEquals(1000,p.getCashBalance(),0); assertEquals(1000,p.calculatePortfolioValue(),0);
    }
}
