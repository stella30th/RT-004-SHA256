// BEGIN
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE060 {

    @Test
    public void testDecimalToBinary() {
        assertEquals("0", NumberConverter.decimalToBinary(0));
        assertEquals("1", NumberConverter.decimalToBinary(1));
        assertEquals("10", NumberConverter.decimalToBinary(2));
        assertEquals("101", NumberConverter.decimalToBinary(5));
        assertEquals("101010", NumberConverter.decimalToBinary(42));
        assertEquals("11111111", NumberConverter.decimalToBinary(255));
        assertEquals("10000000000", NumberConverter.decimalToBinary(1024));
        assertEquals("1111111111111111111111111111111",
                NumberConverter.decimalToBinary(Integer.MAX_VALUE));
        assertEquals("10000000000000000000000000000000",
                NumberConverter.decimalToBinary(Integer.MIN_VALUE));
        assertEquals("11111111111111111111111111111111",
                NumberConverter.decimalToBinary(-1));
    }

    @Test
    public void testBinaryToDecimal() {
        assertEquals(0, NumberConverter.binaryToDecimal("0"));
        assertEquals(1, NumberConverter.binaryToDecimal("1"));
        assertEquals(2, NumberConverter.binaryToDecimal("10"));
        assertEquals(5, NumberConverter.binaryToDecimal("101"));
        assertEquals(42, NumberConverter.binaryToDecimal("101010"));
        assertEquals(255, NumberConverter.binaryToDecimal("11111111"));
        assertEquals(1024, NumberConverter.binaryToDecimal("10000000000"));
        assertEquals(5, NumberConverter.binaryToDecimal("00000101"));
        assertEquals(5, NumberConverter.binaryToDecimal("+101"));
        assertEquals(-5, NumberConverter.binaryToDecimal("-101"));
        assertEquals(Integer.MAX_VALUE,
                NumberConverter.binaryToDecimal("1111111111111111111111111111111"));
        assertEquals(Integer.MIN_VALUE,
                NumberConverter.binaryToDecimal("-10000000000000000000000000000000"));
    }

    @Test
    public void testDecimalToOctal() {
        assertEquals("0", NumberConverter.decimalToOctal(0));
        assertEquals("1", NumberConverter.decimalToOctal(1));
        assertEquals("7", NumberConverter.decimalToOctal(7));
        assertEquals("10", NumberConverter.decimalToOctal(8));
        assertEquals("12", NumberConverter.decimalToOctal(10));
        assertEquals("52", NumberConverter.decimalToOctal(42));
        assertEquals("377", NumberConverter.decimalToOctal(255));
        assertEquals("2000", NumberConverter.decimalToOctal(1024));
        assertEquals("17777777777",
                NumberConverter.decimalToOctal(Integer.MAX_VALUE));
        assertEquals("20000000000",
                NumberConverter.decimalToOctal(Integer.MIN_VALUE));
        assertEquals("37777777777",
                NumberConverter.decimalToOctal(-1));
    }

    @Test
    public void testOctalToDecimal() {
        assertEquals(0, NumberConverter.octalToDecimal("0"));
        assertEquals(1, NumberConverter.octalToDecimal("1"));
        assertEquals(7, NumberConverter.octalToDecimal("7"));
        assertEquals(8, NumberConverter.octalToDecimal("10"));
        assertEquals(10, NumberConverter.octalToDecimal("12"));
        assertEquals(42, NumberConverter.octalToDecimal("52"));
        assertEquals(255, NumberConverter.octalToDecimal("377"));
        assertEquals(1024, NumberConverter.octalToDecimal("2000"));
        assertEquals(8, NumberConverter.octalToDecimal("00010"));
        assertEquals(8, NumberConverter.octalToDecimal("+10"));
        assertEquals(-8, NumberConverter.octalToDecimal("-10"));
        assertEquals(Integer.MAX_VALUE,
                NumberConverter.octalToDecimal("17777777777"));
        assertEquals(Integer.MIN_VALUE,
                NumberConverter.octalToDecimal("-20000000000"));
    }

    @Test
    public void testDecimalToHex() {
        assertEquals("0", NumberConverter.decimalToHex(0));
        assertEquals("1", NumberConverter.decimalToHex(1));
        assertEquals("9", NumberConverter.decimalToHex(9));
        assertEquals("a", NumberConverter.decimalToHex(10));
        assertEquals("f", NumberConverter.decimalToHex(15));
        assertEquals("10", NumberConverter.decimalToHex(16));
        assertEquals("2a", NumberConverter.decimalToHex(42));
        assertEquals("ff", NumberConverter.decimalToHex(255));
        assertEquals("100", NumberConverter.decimalToHex(256));
        assertEquals("400", NumberConverter.decimalToHex(1024));
        assertEquals("abcdef", NumberConverter.decimalToHex(0xABCDEF));
        assertEquals("7fffffff",
                NumberConverter.decimalToHex(Integer.MAX_VALUE));
        assertEquals("80000000",
                NumberConverter.decimalToHex(Integer.MIN_VALUE));
        assertEquals("ffffffff",
                NumberConverter.decimalToHex(-1));
    }

    @Test
    public void testHexToDecimal() {
        assertEquals(0, NumberConverter.hexToDecimal("0"));
        assertEquals(1, NumberConverter.hexToDecimal("1"));
        assertEquals(10, NumberConverter.hexToDecimal("a"));
        assertEquals(10, NumberConverter.hexToDecimal("A"));
        assertEquals(15, NumberConverter.hexToDecimal("f"));
        assertEquals(15, NumberConverter.hexToDecimal("F"));
        assertEquals(16, NumberConverter.hexToDecimal("10"));
        assertEquals(42, NumberConverter.hexToDecimal("2a"));
        assertEquals(42, NumberConverter.hexToDecimal("2A"));
        assertEquals(255, NumberConverter.hexToDecimal("ff"));
        assertEquals(255, NumberConverter.hexToDecimal("FF"));
        assertEquals(1024, NumberConverter.hexToDecimal("400"));
        assertEquals(0xABCDEF, NumberConverter.hexToDecimal("aBcDeF"));
        assertEquals(255, NumberConverter.hexToDecimal("000ff"));
        assertEquals(255, NumberConverter.hexToDecimal("+ff"));
        assertEquals(-255, NumberConverter.hexToDecimal("-ff"));
        assertEquals(Integer.MAX_VALUE,
                NumberConverter.hexToDecimal("7fffffff"));
        assertEquals(Integer.MIN_VALUE,
                NumberConverter.hexToDecimal("-80000000"));
    }

    @Test
    public void testRoundTripForNonNegativeValues() {
        int[] values = {
                0, 1, 2, 7, 8, 9, 10, 15, 16, 31, 32,
                63, 64, 127, 128, 255, 256, 511, 512,
                1023, 1024, 4095, 4096, 65535, 65536,
                1000000, Integer.MAX_VALUE
        };

        for (int value : values) {
            assertEquals(value, NumberConverter.binaryToDecimal(
                    NumberConverter.decimalToBinary(value)));
            assertEquals(value, NumberConverter.octalToDecimal(
                    NumberConverter.decimalToOctal(value)));
            assertEquals(value, NumberConverter.hexToDecimal(
                    NumberConverter.decimalToHex(value)));
        }
    }

    @Test
    public void testNegativeDecimalOutputUsesTwosComplementRepresentation() {
        int[] values = {
                -1, -2, -7, -8, -15, -16, -42,
                -255, -1024, Integer.MIN_VALUE
        };

        for (int value : values) {
            assertEquals(Integer.toBinaryString(value),
                    NumberConverter.decimalToBinary(value));
            assertEquals(Integer.toOctalString(value),
                    NumberConverter.decimalToOctal(value));
            assertEquals(Integer.toHexString(value),
                    NumberConverter.decimalToHex(value));
        }
    }

    @Test
    public void testSignedInputForAllRadices() {
        assertEquals(-1, NumberConverter.binaryToDecimal("-1"));
        assertEquals(-1, NumberConverter.octalToDecimal("-1"));
        assertEquals(-1, NumberConverter.hexToDecimal("-1"));

        assertEquals(0, NumberConverter.binaryToDecimal("-0"));
        assertEquals(0, NumberConverter.octalToDecimal("-0"));
        assertEquals(0, NumberConverter.hexToDecimal("-0"));

        assertEquals(0, NumberConverter.binaryToDecimal("+0"));
        assertEquals(0, NumberConverter.octalToDecimal("+0"));
        assertEquals(0, NumberConverter.hexToDecimal("+0"));
    }

    @Test
    public void testInvalidBinaryInputs() {
        String[] invalid = {
                "", "2", "102", "10a", "0b101", "1 0",
                " 101", "101 ", "+", "-", "--1",
                "11111111111111111111111111111111",
                "10000000000000000000000000000000",
                "-10000000000000000000000000000001"
        };

        for (String input : invalid) {
            try {
                NumberConverter.binaryToDecimal(input);
                fail("Expected NumberFormatException for binary input: " + input);
            } catch (NumberFormatException expected) {
                // Expected for invalid input.
            }
        }
    }

    @Test
    public void testInvalidOctalInputs() {
        String[] invalid = {
                "", "8", "9", "128", "7a", "0o10", "1 0",
                " 10", "10 ", "+", "-", "--7",
                "20000000000", "37777777777",
                "-20000000001"
        };

        for (String input : invalid) {
            try {
                NumberConverter.octalToDecimal(input);
                fail("Expected NumberFormatException for octal input: " + input);
            } catch (NumberFormatException expected) {
                // Expected for invalid input.
            }
        }
    }

    @Test
    public void testInvalidHexInputs() {
        String[] invalid = {
                "", "g", "G", "0x10", "0XFF", "#FF",
                "1 0", " ff", "ff ", "+", "-", "--ff",
                "80000000", "ffffffff", "-80000001"
        };

        for (String input : invalid) {
            try {
                NumberConverter.hexToDecimal(input);
                fail("Expected NumberFormatException for hex input: " + input);
            } catch (NumberFormatException expected) {
                // Expected for invalid input.
            }
        }
    }

    @Test
    public void testNullInputsThrowNumberFormatException() {
        try {
            NumberConverter.binaryToDecimal(null);
            fail("Expected NumberFormatException for null binary input");
        } catch (NumberFormatException expected) {
            // Expected.
        }

        try {
            NumberConverter.octalToDecimal(null);
            fail("Expected NumberFormatException for null octal input");
        } catch (NumberFormatException expected) {
            // Expected.
        }

        try {
            NumberConverter.hexToDecimal(null);
            fail("Expected NumberFormatException for null hex input");
        } catch (NumberFormatException expected) {
            // Expected.
        }
    }

    @Test
    public void testLeadingZerosAreAccepted() {
        assertEquals(0, NumberConverter.binaryToDecimal("000000"));
        assertEquals(5, NumberConverter.binaryToDecimal("00000101"));

        assertEquals(0, NumberConverter.octalToDecimal("000000"));
        assertEquals(63, NumberConverter.octalToDecimal("00077"));

        assertEquals(0, NumberConverter.hexToDecimal("000000"));
        assertEquals(255, NumberConverter.hexToDecimal("0000FF"));
    }

    @Test
    public void testConversionConsistencyAcrossRadices() {
        int[] values = {
                0, 1, 10, 15, 16, 42, 127, 255,
                256, 1024, 4096, 65535, Integer.MAX_VALUE
        };

        for (int value : values) {
            String binary = NumberConverter.decimalToBinary(value);
            String octal = NumberConverter.decimalToOctal(value);
            String hex = NumberConverter.decimalToHex(value);

            assertEquals(NumberConverter.binaryToDecimal(binary),
                    NumberConverter.octalToDecimal(octal));
            assertEquals(NumberConverter.octalToDecimal(octal),
                    NumberConverter.hexToDecimal(hex));
        }
    }
}
// END