package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE010 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals("1000",BigNumCalculator.add("999","1")); assertEquals("44",BigNumCalculator.add("12","32"));
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals("-8",BigNumCalculator.subtract("2","10")); assertEquals("98",BigNumCalculator.subtract("100","2"));
    }
    @Test public void behavioralCase3() throws Exception {
        assertEquals("56088",BigNumCalculator.multiply("123","456")); assertEquals("0",BigNumCalculator.multiply("0","123"));
    }
}
