package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE062 {

@Test
public void testReplaceDecimalEntities() {
    NumericEntityUnescaper unescaper = new NumericEntityUnescaper();
    // &#65; is 'A', &#66; is 'B'
    assertEquals("AB", unescaper.replace("&#65;&#66;"));
    assertEquals("Hello World!", unescaper.replace("Hello&#32;World!"));
}

@Test
public void testReplaceHexEntities() {
    NumericEntityUnescaper unescaper = new NumericEntityUnescaper();
    // &#x41; is 'A', &#X42; is 'B'
    assertEquals("AB", unescaper.replace("&#x41;&#X42;"));
    assertEquals("Test & More", unescaper.replace("Test &#x26; More"));
}

@Test
public void testNoEntitiesOrIncompleteEntities() {
    NumericEntityUnescaper unescaper = new NumericEntityUnescaper();
    assertEquals("Just plain text", unescaper.replace("Just plain text"));
    assertEquals("Incomplete &#65", unescaper.replace("Incomplete &#65"));
    assertEquals("Not an entity &amp;", unescaper.replace("Not an entity &amp;"));
}

@Test
public void testIsHexChar() {
    assertTrue(NumericEntityUnescaper.isHexChar('0'));
    assertTrue(NumericEntityUnescaper.isHexChar('9'));
    assertTrue(NumericEntityUnescaper.isHexChar('a'));
    assertTrue(NumericEntityUnescaper.isHexChar('F'));
    assertFalse(NumericEntityUnescaper.isHexChar('g'));
    assertFalse(NumericEntityUnescaper.isHexChar('-'));
}

}
