package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE060 {

@Test
public void testBinaryConversions() {
    assertEquals("1010", NumberConverter.decimalToBinary(10));
    assertEquals("0", NumberConverter.decimalToBinary(0));
    assertEquals(10, NumberConverter.binaryToDecimal("1010"));
    assertEquals(0, NumberConverter.binaryToDecimal("0"));
}

@Test
public void testOctalConversions() {
    assertEquals("12", NumberConverter.decimalToOctal(10));
    assertEquals("77", NumberConverter.decimalToOctal(63));
    assertEquals(10, NumberConverter.octalToDecimal("12"));
    assertEquals(63, NumberConverter.octalToDecimal("77"));
}

@Test
public void testHexConversions() {
    assertEquals("ff", NumberConverter.decimalToHex(255));
    assertEquals("10", NumberConverter.decimalToHex(16));
    assertEquals(255, NumberConverter.hexToDecimal("ff"));
    assertEquals(16, NumberConverter.hexToDecimal("10"));
}

@Test
public void testRoundTripLargeNumber() {
    int original = 123456;
    assertEquals(original, NumberConverter.binaryToDecimal(NumberConverter.decimalToBinary(original)));
    assertEquals(original, NumberConverter.octalToDecimal(NumberConverter.decimalToOctal(original)));
    assertEquals(original, NumberConverter.hexToDecimal(NumberConverter.decimalToHex(original)));
}

}
