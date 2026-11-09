package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE062 {
    @Test public void behavioralCase1() throws Exception {
        NumericEntityUnescaper n=new NumericEntityUnescaper(); assertEquals("A",n.replace("&#65;")); assertTrue(NumericEntityUnescaper.isHexChar('f')); assertFalse(NumericEntityUnescaper.isHexChar('g'));
    }
}
