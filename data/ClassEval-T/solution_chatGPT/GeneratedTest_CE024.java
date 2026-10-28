package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE024 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(6,CombinationCalculator.count(4,2)); assertEquals(1,CombinationCalculator.count(4,0)); assertEquals(7,CombinationCalculator.countAll(3),0);
    }
    @Test public void behavioralCase2() throws Exception {
        CombinationCalculator c=new CombinationCalculator(Arrays.asList("A","B","C")); assertEquals(3,c.select(2).size()); assertEquals(7,c.selectAll().size());
    }
}
