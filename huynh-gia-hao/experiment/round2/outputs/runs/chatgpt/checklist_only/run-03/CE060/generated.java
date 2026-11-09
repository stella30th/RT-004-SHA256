// BEGIN GeneratedTest_CE060
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE060 {

    @Test public void decimalToBaseFormats() {
        assertEquals("0", NumberConverter.decimalToBinary(0));
        assertEquals("1010", NumberConverter.decimalToBinary(10));
        assertEquals("12", NumberConverter.decimalToOctal(10));
        assertEquals("377", NumberConverter.decimalToOctal(255));
        assertEquals("a", NumberConverter.decimalToHex(10));
        assertEquals("ff", NumberConverter.decimalToHex(255));
    }
    @Test public void parsesEachBase() {
        assertEquals(10, NumberConverter.binaryToDecimal("1010"));
        assertEquals(10, NumberConverter.octalToDecimal("12"));
        assertEquals(10, NumberConverter.hexToDecimal("A"));
        assertEquals(255, NumberConverter.hexToDecimal("ff"));
        assertEquals(255, NumberConverter.hexToDecimal("FF"));
    }
    @Test public void acceptsSignsAndLeadingZerosWhenParsing() {
        assertEquals(-5, NumberConverter.binaryToDecimal("-00101"));
        assertEquals(8, NumberConverter.octalToDecimal("+0010"));
        assertEquals(255, NumberConverter.hexToDecimal("0000ff"));
    }
    @Test public void negativeFormattingUsesUnsignedBits() {
        assertEquals("11111111111111111111111111111111", NumberConverter.decimalToBinary(-1));
        assertEquals("37777777777", NumberConverter.decimalToOctal(-1));
        assertEquals("ffffffff", NumberConverter.decimalToHex(-1));
        assertEquals("80000000", NumberConverter.decimalToHex(Integer.MIN_VALUE));
    }
    @Test public void roundTripNonnegativeValues() {
        for (int n : new int[]{0, 1, 15, 255, 1024, Integer.MAX_VALUE}) {
            assertEquals(n, NumberConverter.binaryToDecimal(NumberConverter.decimalToBinary(n)));
            assertEquals(n, NumberConverter.octalToDecimal(NumberConverter.decimalToOctal(n)));
            assertEquals(n, NumberConverter.hexToDecimal(NumberConverter.decimalToHex(n)));
        }
    }
    @Test(expected = NumberFormatException.class) public void invalidBinaryDigitThrows() {
        NumberConverter.binaryToDecimal("102");
    }
    @Test(expected = NumberFormatException.class) public void hexOverflowThrows() {
        NumberConverter.hexToDecimal("80000000");
    }

    @Test public void minimumIntegerCanBeParsedWithSign() { assertEquals(Integer.MIN_VALUE,NumberConverter.hexToDecimal("-80000000")); }
}
// END GeneratedTest_CE060
