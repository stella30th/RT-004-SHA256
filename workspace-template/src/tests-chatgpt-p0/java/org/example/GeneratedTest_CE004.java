package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE004 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(6,ArrangementCalculator.count(3,null)); assertEquals(6,ArrangementCalculator.count(3,2)); assertEquals(15,ArrangementCalculator.countAll(3));
    }
    @Test public void behavioralCase2() throws Exception {
        ArrangementCalculator a=new ArrangementCalculator(new ArrayList<Object>(Arrays.asList("a","b","c"))); assertEquals(6,a.select(2).size()); assertEquals(15,a.selectAll().size());
    }
}
