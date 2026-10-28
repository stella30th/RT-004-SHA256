package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE060 {

@Test public void convertsBasesAndKeepsLowercaseHex() {
 assertEquals("101010",NumberConverter.decimalToBinary(42));
 assertEquals(42,NumberConverter.binaryToDecimal("101010"));
 assertEquals("52",NumberConverter.decimalToOctal(42));
 assertEquals(42,NumberConverter.octalToDecimal("52"));
 assertEquals("ff",NumberConverter.decimalToHex(255));
 assertEquals(255,NumberConverter.hexToDecimal("FF"));
}
@Test public void zeroAndNegativeBinary() {
 assertEquals("0",NumberConverter.decimalToBinary(0));
 assertEquals("ffffffff",NumberConverter.decimalToHex(-1));
}
@Test(expected=NumberFormatException.class)
public void invalidBinaryThrows() {NumberConverter.binaryToDecimal("102");}

}
