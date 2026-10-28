package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE017 {
    @Test public void behavioralCase1() throws Exception {
        Calculator c=new Calculator(); assertEquals(7.0,c.calculate("1+2*3"),0); assertEquals(9.0,c.calculate("(1+2)*3"),0);
    }
    @Test public void behavioralCase2() throws Exception {
        Calculator c=new Calculator(); assertEquals(4.0,c.calculate("8/2"),0); assertEquals(8.0,c.calculate("2^3"),0); assertNull(c.calculate(""));
    }
}
