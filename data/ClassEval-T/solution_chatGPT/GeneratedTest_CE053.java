package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE053 {
    @Test public void behavioralCase1() throws Exception {
        MetricsCalculator m=new MetricsCalculator(); assertEquals(1,m.accuracy(new int[]{1,0},new int[]{1,0}),1e-9); assertEquals(.5,m.accuracy(new int[]{1,1},new int[]{1,0}),1e-9);
    }
}
