package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE012 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(6,BitStatusUtil.add(2,4)); assertTrue(BitStatusUtil.has(6,2)); assertFalse(BitStatusUtil.has(2,4)); assertEquals(4,BitStatusUtil.remove(6,2));
    }
    @Test public void behavioralCase2() throws Exception {
        try{BitStatusUtil.add(2,3);fail();}catch(IllegalArgumentException e){assertEquals("3 not even",e.getMessage());}
    }
}
