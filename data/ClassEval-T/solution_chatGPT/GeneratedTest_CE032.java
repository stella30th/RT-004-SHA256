package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE032 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(1.0,DataStatistics4.correlationCoefficient(Arrays.asList(1.0,2.0),Arrays.asList(2.0,4.0)),1e-9); assertEquals(0,DataStatistics4.correlationCoefficient(Arrays.asList(1.0,1.0),Arrays.asList(2.0,3.0)),0);
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals(0,DataStatistics4.skewness(Arrays.asList(1.0,2.0,3.0)),1e-9); assertTrue(Double.isNaN(DataStatistics4.kurtosis(Arrays.asList(2.0,2.0,2.0))));
    }
}
