package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE007 {
    @Test public void behavioralCase1() throws Exception {
        AvgPartition a=new AvgPartition(Arrays.asList(1,2,3,4,5),2); assertArrayEquals(new int[]{2,1},a.setNum()); assertEquals(Arrays.asList(1,2,3),a.get(0)); assertEquals(Arrays.asList(4,5),a.get(1));
    }
    @Test public void behavioralCase2() throws Exception {
        AvgPartition a=new AvgPartition(Arrays.asList(1,2),3); assertEquals(Arrays.asList(),a.get(2));
    }
}
