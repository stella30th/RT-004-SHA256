package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertFalse(NumericEntityUnescaper.isHexChar(';'));
    }

    @Test
    public void testReplaceDecimalEntity() {
        assertEquals("A", unescaper.replace("&#65;"));
        assertEquals("AB", unescaper.replace("&#65;&#66;"));
    }

    @Test
    public void testReplaceHexEntity() {
        assertEquals("A", unescaper.replace("&#x41;"));
        assertEquals("A", unescaper.replace("&#X41;"));
    }

    @Test
    public void testReplacePlainTextTruncatesSuffix() {
        assertEquals("", unescaper.replace(""));
        assertEquals("", unescaper.replace("a"));
        assertEquals("", unescaper.replace("ab"));
        assertEquals("a", unescaper.replace("abc"));
        assertEquals("Hel", unescaper.replace("Hello"));
    }

    @Test
    public void testReplaceIncompleteEntity() {
        assertEquals("", unescaper.replace("&#"));
        assertEquals("", unescaper.replace("&#x"));
        assertEquals("", unescaper.replace("&#;"));
    }
}
