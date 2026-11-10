package org.example;

import org.junit.Before; import org.junit.Test;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertFalse; import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE062 {

private NumericEntityUnescaper unescaper;

@Before
public void setUp() {
    unescaper = new NumericEntityUnescaper();
}

@Test
public void testReplaceDecimalEntities() {
    // &#65; is 'A', &#66; is 'B', &#67; is 'C'
    String input = "&#65;&#66;&#67;";
    String result = unescaper.replace(input);
    assertEquals("ABC", result);
}

@Test
public void testReplaceHexEntitiesLowerAndUpperPrefix() {
    // &#x41; is 'A' (hex 0x41 = 65), &#X42; is 'B' (hex 0x42 = 66)
    String input = "&#x41;&#X42;";
    String result = unescaper.replace(input);
    assertEquals("AB", result);
}

@Test
public void testReplacePrefixWithEntityEnding() {
    // Text followed by decimal entity ending at the end of the string
    String input = "Test &#65;";
    String result = unescaper.replace(input);
    assertEquals("Test A", result);
}

@Test
public void testReplaceShortStringsUnderThreeChars() {
    // The while-loop condition is pos < length - 2, so strings shorter than 3 yield empty string
    assertEquals("", unescaper.replace(""));
    assertEquals("", unescaper.replace("a"));
    assertEquals("", unescaper.replace("ab"));
}

@Test
public void testReplaceTruncatedHexMarkerAtEnd() {
    // When string ends with &#x, start becomes equal to length and triggers early return
    String input = "test&#x";
    String result = unescaper.replace(input);
    assertEquals("test", result);
}

@Test
public void testReplaceInvalidDecimalNumberFormatException() {
    // 'a' is a valid hex char, so it is consumed before ';', but Integer.parseInt("6a", 10) throws
    // NumberFormatException, causing an early return of accumulated output
    String input = "Hello &#6a; World";
    String result = unescaper.replace(input);
    assertEquals("Hello ", result);
}

@Test
public void testIsHexChar() {
    assertTrue(NumericEntityUnescaper.isHexChar('0'));
    assertTrue(NumericEntityUnescaper.isHexChar('9'));
    assertTrue(NumericEntityUnescaper.isHexChar('a'));
    assertTrue(NumericEntityUnescaper.isHexChar('f'));
    assertTrue(NumericEntityUnescaper.isHexChar('A'));
    assertTrue(NumericEntityUnescaper.isHexChar('F'));

    assertFalse(NumericEntityUnescaper.isHexChar('g'));
    assertFalse(NumericEntityUnescaper.isHexChar('G'));
    assertFalse(NumericEntityUnescaper.isHexChar('x'));
    assertFalse(NumericEntityUnescaper.isHexChar(';'));
    assertFalse(NumericEntityUnescaper.isHexChar(' '));
}

}