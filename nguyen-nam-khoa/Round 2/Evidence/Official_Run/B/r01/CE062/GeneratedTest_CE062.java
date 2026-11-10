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
public void testReplacePlainTextTruncatesLastTwoChars() {
    // Due to the loop condition (pos < length - 2), characters at the final two positions
    // that are not part of an unescaped entity are omitted.
    assertEquals("hel", unescaper.replace("hello"));
    assertEquals("a", unescaper.replace("abc"));
    assertEquals("", unescaper.replace("ab"));
    assertEquals("", unescaper.replace(""));
}

@Test
public void testReplaceIncompleteHexPrefixAtEnd() {
    // String ends with "&#x", start becomes length, triggering early return of out.toString()
    String input = "test&#x";
    assertEquals("test", unescaper.replace(input));
}

@Test
public void testReplaceInvalidDecimalEntityThrowsNumberFormatExceptionAndReturnsCurrent() {
    // "&#1a;" is treated as decimal because it lacks 'x'/'X', but 'a' is accepted by isHexChar
    // Integer.parseInt("1a", 10) throws NumberFormatException, returning accumulated out
    String input = "foo&#1a;bar";
    assertEquals("foo", unescaper.replace(input));
}

@Test
public void testReplaceUnterminatedEntityFallsBackToNormalChars() {
    // "&#65xyz" has no ';' terminator; it emits '&' and continues scanning
    String input = "&#65xyz";
    assertEquals("&#65x", unescaper.replace(input));
}

@Test
public void testIsHexCharDigitsAndLetters() {
    assertTrue(NumericEntityUnescaper.isHexChar('0'));
    assertTrue(NumericEntityUnescaper.isHexChar('9'));
    assertTrue(NumericEntityUnescaper.isHexChar('a'));
    assertTrue(NumericEntityUnescaper.isHexChar('f'));
    assertTrue(NumericEntityUnescaper.isHexChar('A'));
    assertTrue(NumericEntityUnescaper.isHexChar('F'));

    assertFalse(NumericEntityUnescaper.isHexChar('g'));
    assertFalse(NumericEntityUnescaper.isHexChar('G'));
    assertFalse(NumericEntityUnescaper.isHexChar('x'));
    assertFalse(NumericEntityUnescaper.isHexChar('X'));
    assertFalse(NumericEntityUnescaper.isHexChar(';'));
    assertFalse(NumericEntityUnescaper.isHexChar(' '));
}

}
