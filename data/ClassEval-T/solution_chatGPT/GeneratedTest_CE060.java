package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE060 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals("1010",NumberConverter.decimalToBinary(10)); assertEquals(10,NumberConverter.binaryToDecimal("1010"));
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals("12",NumberConverter.decimalToOctal(10)); assertEquals(10,NumberConverter.octalToDecimal("12")); assertEquals("A",NumberConverter.decimalToHex(10));
    }
}
