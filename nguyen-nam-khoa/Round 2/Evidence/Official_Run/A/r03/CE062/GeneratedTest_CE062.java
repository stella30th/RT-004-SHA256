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
    String input = "&#65;&#66;&#67;";
    String expected = "ABC";
    assertEquals(expected, unescaper.replace(input));
}

@Test
public void testReplaceHexEntitiesBothCases() {
    String input = "&#x41;&#X42;";
    String expected = "AB";
    assertEquals(expected, unescaper.replace(input));
}

@Test
public void testReplaceShortStringsUnderThreeChars() {
    assertEquals("", unescaper.replace(""));
    assertEquals("", unescaper.replace("a"));
    assertEquals("", unescaper.replace("ab"));
}

@Test
public void testReplacePlainStringStopsBeforeLastTwoCharacters() {
    // According to the loop condition (pos < length - 2), plain characters beyond length - 2 are not processed
    assertEquals("Hel", unescaper.replace("Hello"));
}

@Test
public void testReplaceInvalidEntityNumberFormatException() {
    // Hex characters without x prefix attempt base-10 parse and trigger NumberFormatException
    String input = "&#abcdef;";
    assertEquals("", unescaper.replace(input));

    String inputWithPrefix = "xy&#abcdef;";
    assertEquals("xy", unescaper.replace(inputWithPrefix));
}

@Test
public void testReplaceTruncatedHexIndicator() {
    // String "&#x" ends right after 'x', triggering start == length
    assertEquals("", unescaper.replace("&#x"));
}

@Test
public void testReplaceUnterminatedEntityFallsBackToNormalCharacters() {
    // "&#65x" does not have ';' so '&' is appended as normal character
    assertEquals("&#6", unescaper.replace("&#65x"));
}

@Test
public void testIsHexCharDigits() {
    assertTrue(NumericEntityUnescaper.isHexChar('0'));
    assertTrue(NumericEntityUnescaper.isHexChar('5'));
    assertTrue(NumericEntityUnescaper.isHexChar('9'));
}

@Test
public void testIsHexCharLetters() {
    assertTrue(NumericEntityUnescaper.isHexChar('a'));
    assertTrue(NumericEntityUnescaper.isHexChar('f'));
    assertTrue(NumericEntityUnescaper.isHexChar('A'));
    assertTrue(NumericEntityUnescaper.isHexChar('F'));

    assertFalse(NumericEntityUnescaper.isHexChar('g'));
    assertFalse(NumericEntityUnescaper.isHexChar('G'));
    assertFalse(NumericEntityUnescaper.isHexChar('z'));
    assertFalse(NumericEntityUnescaper.isHexChar(';'));
}

}