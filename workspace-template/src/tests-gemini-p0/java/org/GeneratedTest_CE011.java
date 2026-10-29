package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE011 {

private static final double DELTA = 1e-4;

@Test
public void testCleanNonBinaryChars() {
    BinaryDataProcessor processor = new BinaryDataProcessor("01a b2 10!#$");
    assertEquals("0110", processor.getBinaryString());
}

@Test
public void testCalculateBinaryInfo() {
    BinaryDataProcessor processor = new BinaryDataProcessor("00011111");
    BinaryInfo info = processor.calculateBinaryInfo();

    assertEquals(8, info.getBitLength());
    assertEquals(3.0 / 8.0, info.getZeroes(), DELTA);
    assertEquals(5.0 / 8.0, info.getOnes(), DELTA);
    assertEquals("{Zeroes: 0.375, Ones: 0.625, Bit length: 8}", info.toString());
}

@Test
public void testConvertToAscii() {
    // "01000001" is 'A' (65), "01000010" is 'B' (66)
    BinaryDataProcessor processor = new BinaryDataProcessor("0100000101000010");
    assertEquals("AB", processor.convertToAscii());
}

@Test
public void testConvertToUtf8() {
    // "0110100001101001" -> 'h' (104), 'i' (105)
    BinaryDataProcessor processor = new BinaryDataProcessor("0110100001101001");
    assertEquals("hi", processor.convertToUtf8());
}

}
