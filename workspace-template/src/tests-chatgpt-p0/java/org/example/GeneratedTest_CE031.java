package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE031 {
    @Test public void behavioralCase1() throws Exception {
        DataStatistics2 d=new DataStatistics2(Arrays.asList(1,2,3)); assertEquals(6,d.getSum(),0); assertEquals(1,d.getMin(),0); assertEquals(3,d.getMax(),0); assertEquals(2.0/3,d.getVariance(),1e-9);
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals(1.0,new DataStatistics2(Arrays.asList(5)).getCorrelation(),0); assertTrue(Double.isNaN(new DataStatistics2(Collections.emptyList()).getMin()));
    }
}
