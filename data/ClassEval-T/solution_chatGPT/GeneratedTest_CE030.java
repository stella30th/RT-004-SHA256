package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE030 {
    @Test public void behavioralCase1() throws Exception {
        DataStatistics d=new DataStatistics(); assertEquals(2.5,d.mean(new int[]{1,2,3,4}),0); assertEquals(2.5,d.median(new int[]{4,1,3,2}),0);
    }
    @Test public void behavioralCase2() throws Exception {
        DataStatistics d=new DataStatistics(); assertEquals(Arrays.asList(2,3),d.mode(new int[]{3,2,3,2,1})); assertEquals(5,d.median(new int[]{7,5,1}),0);
    }
}
