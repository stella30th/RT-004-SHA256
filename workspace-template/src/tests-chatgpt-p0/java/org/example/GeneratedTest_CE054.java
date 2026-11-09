package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE054 {
    @Test public void behavioralCase1() throws Exception {
        MetricsCalculator2.Tuple t=new MetricsCalculator2.Tuple(Arrays.asList(1,2),2); assertEquals(Arrays.asList(1,2),t.getList()); assertEquals(2,t.getTotalNum());
    }
}
