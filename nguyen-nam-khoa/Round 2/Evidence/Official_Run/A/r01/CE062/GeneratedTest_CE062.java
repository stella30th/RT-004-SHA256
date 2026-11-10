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
public void testIsHexChar() {
    assertTrue(NumericEntityUnescaper.isHexChar('0'));
    assertTrue(NumericEntityUnescaper.isHexChar('9'));
    assertTrue(NumericEntityUnescaper.isHexChar('a'));
    assertTrue(NumericEntityUnescaper.isHexChar('f'));
    assertTrue(NumericEntityUnescaper.isHexChar('A'));
    assertTrue(NumericEntityUnescaper.isHexChar('F'));

    assertFalse(NumericEntityUnescaper.isHexChar('g'));
    assertFalse(NumericEntityUnescaper.isHexChar('G'));
    assertFalse(NumericEntityUnescaper.isHexChar(';'));
    assertFalse(NumericEntityUnescaper.isHexChar('#'));
    assertFalse(NumericEntityUnescaper.isHexChar(' '));
}

@Test
public void testReplaceDecimalEntities() {
    assertEquals("A", unescaper.replace("&#65;"));
    assertEquals("ABC", unescaper.replace("&#65;&#66;&#67;"));
    assertEquals("a", unescaper.replace("&#97;"));
}

@Test
public void testReplaceHexEntities() {
    assertEquals("A", unescaper.replace("&#x41;"));
    assertEquals("B", unescaper.replace("&#X42;"));
    assertEquals("AB", unescaper.replace("&#x41;&#X42;"));
}

@Test
public void testReplaceShortStrings() {
    assertEquals("", unescaper.replace(""));
    assertEquals("", unescaper.replace("a"));
    assertEquals("", unescaper.replace("ab"));
    assertEquals("a", unescaper.replace("abc"));
}

@Test
public void testReplaceWithPrefixAndEntity() {
    assertEquals("Test ", unescaper.replace("Test&#32;Me"));
}

@Test
public void testReplaceNumberFormatException() {
    assertEquals("", unescaper.replace("&#99999999999999999999999999999999999;"));
    assertEquals("Prefix", unescaper.replace("Prefix&#6a;Suffix"));
}

@Test
public void testReplaceIncompleteEntity() {
    assertEquals("", unescaper.replace("&#x"));
    assertEquals("&#65x", unescaper.replace("&#65xyz"));
}

}