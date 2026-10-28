package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE011 {
    @Test public void behavioralCase1() throws Exception {
        BinaryDataProcessor b=new BinaryDataProcessor("01 a 10!"); assertEquals("0110",b.getBinaryString()); assertEquals(4,b.calculateBinaryInfo().getBitLength()); assertEquals(.5,b.calculateBinaryInfo().getOnes(),0);
    }
    @Test public void behavioralCase2() throws Exception {
        BinaryDataProcessor b=new BinaryDataProcessor("0100000101000010"); assertEquals("AB",b.convertToAscii()); assertEquals("AB",b.convertToUtf8());
    }
}
