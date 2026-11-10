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
public void testReplaceDecimalEntity() {
    String result = unescaper.replace("&#65;&#66;");
    assertEquals("AB", result);

    String resultWithPrefix = unescaper.replace("Hello &#65;&#66;");
    assertEquals("Hello AB", resultWithPrefix);
}

@Test
public void testReplaceHexEntityLowerCaseAndUpperCase() {
    String result = unescaper.replace("&#x41;&#X42;");
    assertEquals("AB", result);

    String hexWord = unescaper.replace("&#x68;&#x65;&#x6c;&#x6c;&#x6f;");
    assertEquals("hello", hexWord);
}

@Test
public void testReplaceShortStringAndLoopBoundary() {
    assertEquals("", unescaper.replace(""));
    assertEquals("", unescaper.replace("a"));
    assertEquals("", unescaper.replace("ab"));
    assertEquals("a", unescaper.replace("abc"));
    assertEquals("hel", unescaper.replace("hello"));
}

@Test
public void testReplaceNumberFormatExceptionOnDecimalWithHexChars() {
    // "6a" matches isHexChar, but without x prefix it parses as base 10, throwing NumberFormatException
    String result = unescaper.replace("pre&#6a;post");
    assertEquals("pre", result);
}

@Test
public void testReplaceStartEqualsLength() {
    // When string ends with "&#x", start reaches length after skipping 'x'
    String result = unescaper.replace("foo&#x");
    assertEquals("foo", result);
}

@Test
public void testReplaceMissingSemicolon() {
    String result = unescaper.replace("&#65");
    assertEquals("&#", result);
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
    assertFalse(NumericEntityUnescaper.isHexChar('#'));
    assertFalse(NumericEntityUnescaper.isHexChar(' '));
}

}