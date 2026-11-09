// BEGIN GeneratedTest_CE060
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE060 {

    @Test
    public void testDecimalToBinary() {
        assertEquals("0", NumberConverter.decimalToBinary(0));
        assertEquals("1", NumberConverter.decimalToBinary(1));
        assertEquals("1010", NumberConverter.decimalToBinary(10));
        assertEquals("11111111", NumberConverter.decimalToBinary(255));
        assertEquals("10000000000", NumberConverter.decimalToBinary(1024));
    }

    @Test
    public void testBinaryToDecimal() {
        assertEquals(0, NumberConverter.binaryToDecimal("0"));
        assertEquals(1, NumberConverter.binaryToDecimal("1"));
        assertEquals(10, NumberConverter.binaryToDecimal("1010"));
        assertEquals(255, NumberConverter.binaryToDecimal("11111111"));
        assertEquals(1024, NumberConverter.binaryToDecimal("10000000000"));
    }

    @Test
    public void testDecimalToOctal() {
        assertEquals("0", NumberConverter.decimalToOctal(0));
        assertEquals("1", NumberConverter.decimalToOctal(1));
        assertEquals("10", NumberConverter.decimalToOctal(8));
        assertEquals("12", NumberConverter.decimalToOctal(10));
        assertEquals("377", NumberConverter.decimalToOctal(255));
        assertEquals("2000", NumberConverter.decimalToOctal(1024));
    }

    @Test
    public void testOctalToDecimal() {
        assertEquals(0, NumberConverter.octalToDecimal("0"));
        assertEquals(1, NumberConverter.octalToDecimal("1"));
        assertEquals(8, NumberConverter.octalToDecimal("10"));
        assertEquals(10, NumberConverter.octalToDecimal("12"));
        assertEquals(255, NumberConverter.octalToDecimal("377"));
        assertEquals(1024, NumberConverter.octalToDecimal("2000"));
    }

    @Test
    public void testDecimalToHex() {
        assertEquals("0", NumberConverter.decimalToHex(0));
        assertEquals("1", NumberConverter.decimalToHex(1));
        assertEquals("a", NumberConverter.decimalToHex(10));
        assertEquals("f", NumberConverter.decimalToHex(15));
        assertEquals("10", NumberConverter.decimalToHex(16));
        assertEquals("ff", NumberConverter.decimalToHex(255));
        assertEquals("400", NumberConverter.decimalToHex(1024));
    }

    @Test
    public void testHexToDecimal() {
        assertEquals(0, NumberConverter.hexToDecimal("0"));
        assertEquals(1, NumberConverter.hexToDecimal("1"));
        assertEquals(10, NumberConverter.hexToDecimal("a"));
        assertEquals(15, NumberConverter.hexToDecimal("f"));
        assertEquals(16, NumberConverter.hexToDecimal("10"));
        assertEquals(255, NumberConverter.hexToDecimal("ff"));
        assertEquals(1024, NumberConverter.hexToDecimal("400"));
    }

    @Test
    public void testHexToDecimalCaseInsensitive() {
        assertEquals(255, NumberConverter.hexToDecimal("FF"));
        assertEquals(171, NumberConverter.hexToDecimal("Ab"));
        assertEquals(373592855, NumberConverter.hexToDecimal("1643C17"));
    }

    @Test
    public void testLeadingZerosInInput() {
        assertEquals(5, NumberConverter.binaryToDecimal("00000101"));
        assertEquals(8, NumberConverter.octalToDecimal("00010"));
        assertEquals(255, NumberConverter.hexToDecimal("0000FF"));
    }

    @Test
    public void testPositiveSignInInput() {
        assertEquals(5, NumberConverter.binaryToDecimal("+101"));
        assertEquals(8, NumberConverter.octalToDecimal("+10"));
        assertEquals(255, NumberConverter.hexToDecimal("+ff"));
    }

    @Test
    public void testNegativeSignInInput() {
        assertEquals(-5, NumberConverter.binaryToDecimal("-101"));
        assertEquals(-8, NumberConverter.octalToDecimal("-10"));
        assertEquals(-255, NumberConverter.hexToDecimal("-ff"));
    }

    @Test
    public void testNegativeDecimalToBinary() {
        assertEquals("11111111111111111111111111111111",
                NumberConverter.decimalToBinary(-1));
        assertEquals("11111111111111111111111111111010",
                NumberConverter.decimalToBinary(-6));
        assertEquals("10000000000000000000000000000000",
                NumberConverter.decimalToBinary(Integer.MIN_VALUE));
    }

    @Test
    public void testNegativeDecimalToOctal() {
        assertEquals("37777777777",
                NumberConverter.decimalToOctal(-1));
        assertEquals("20000000000",
                NumberConverter.decimalToOctal(Integer.MIN_VALUE));
    }

    @Test
    public void testNegativeDecimalToHex() {
        assertEquals("ffffffff",
                NumberConverter.decimalToHex(-1));
        assertEquals("fffffffa",
                NumberConverter.decimalToHex(-6));
        assertEquals("80000000",
                NumberConverter.decimalToHex(Integer.MIN_VALUE));
    }

    @Test
    public void testMaximumIntegerConversions() {
        int max = Integer.MAX_VALUE;

        assertEquals("1111111111111111111111111111111",
                NumberConverter.decimalToBinary(max));
        assertEquals("17777777777",
                NumberConverter.decimalToOctal(max));
        assertEquals("7fffffff",
                NumberConverter.decimalToHex(max));

        assertEquals(max,
                NumberConverter.binaryToDecimal("1111111111111111111111111111111"));
        assertEquals(max,
                NumberConverter.octalToDecimal("17777777777"));
        assertEquals(max,
                NumberConverter.hexToDecimal("7fffffff"));
    }

    @Test
    public void testMinimumIntegerParsing() {
        int min = Integer.MIN_VALUE;

        assertEquals(min,
                NumberConverter.binaryToDecimal("-10000000000000000000000000000000"));
        assertEquals(min,
                NumberConverter.octalToDecimal("-20000000000"));
        assertEquals(min,
                NumberConverter.hexToDecimal("-80000000"));
    }

    @Test
    public void testBinaryRoundTripForNonNegativeValues() {
        int[] values = {0, 1, 2, 7, 15, 31, 128, 1024, 65535, Integer.MAX_VALUE};

        for (int value : values) {
            assertEquals(value,
                    NumberConverter.binaryToDecimal(
                            NumberConverter.decimalToBinary(value)));
        }
    }

    @Test
    public void testOctalRoundTripForNonNegativeValues() {
        int[] values = {0, 1, 7, 8, 63, 64, 255, 1024, 65535, Integer.MAX_VALUE};

        for (int value : values) {
            assertEquals(value,
                    NumberConverter.octalToDecimal(
                            NumberConverter.decimalToOctal(value)));
        }
    }

    @Test
    public void testHexRoundTripForNonNegativeValues() {
        int[] values = {0, 1, 9, 10, 15, 16, 255, 256, 4096, Integer.MAX_VALUE};

        for (int value : values) {
            assertEquals(value,
                    NumberConverter.hexToDecimal(
                            NumberConverter.decimalToHex(value)));
        }
    }

    @Test(expected = NumberFormatException.class)
    public void testBinaryToDecimalRejectsInvalidDigit() {
        NumberConverter.binaryToDecimal("102");
    }

    @Test(expected = NumberFormatException.class)
    public void testOctalToDecimalRejectsInvalidDigit() {
        NumberConverter.octalToDecimal("89");
    }

    @Test(expected = NumberFormatException.class)
    public void testHexToDecimalRejectsInvalidDigit() {
        NumberConverter.hexToDecimal("G1");
    }

    @Test(expected = NumberFormatException.class)
    public void testBinaryToDecimalRejectsEmptyString() {
        NumberConverter.binaryToDecimal("");
    }

    @Test(expected = NumberFormatException.class)
    public void testOctalToDecimalRejectsEmptyString() {
        NumberConverter.octalToDecimal("");
    }

    @Test(expected = NumberFormatException.class)
    public void testHexToDecimalRejectsEmptyString() {
        NumberConverter.hexToDecimal("");
    }

    @Test(expected = NumberFormatException.class)
    public void testBinaryToDecimalRejectsOverflow() {
        NumberConverter.binaryToDecimal("10000000000000000000000000000000");
    }

    @Test(expected = NumberFormatException.class)
    public void testOctalToDecimalRejectsOverflow() {
        NumberConverter.octalToDecimal("20000000000");
    }

    @Test(expected = NumberFormatException.class)
    public void testHexToDecimalRejectsOverflow() {
        NumberConverter.hexToDecimal("80000000");
    }

    @Test(expected = NumberFormatException.class)
    public void testHexToDecimalRejectsWhitespace() {
        NumberConverter.hexToDecimal(" ff ");
    }

    @Test(expected = NumberFormatException.class)
    public void testBinaryToDecimalRejectsPrefix() {
        NumberConverter.binaryToDecimal("0b1010");
    }

    @Test(expected = NumberFormatException.class)
    public void testHexToDecimalRejectsPrefix() {
        NumberConverter.hexToDecimal("0xFF");
    }

    @Test
    public void testDifferentBasesRepresentSameValue() {
        assertEquals("11111111", NumberConverter.decimalToBinary(255));
        assertEquals("377", NumberConverter.decimalToOctal(255));
        assertEquals("ff", NumberConverter.decimalToHex(255));

        assertEquals(255, NumberConverter.binaryToDecimal("11111111"));
        assertEquals(255, NumberConverter.octalToDecimal("377"));
        assertEquals(255, NumberConverter.hexToDecimal("ff"));
    }
}
// END GeneratedTest_CE060