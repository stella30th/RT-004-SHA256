package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE052 {

@Test
public void testPalindromicStringOddLength() {
    Manacher manacher = new Manacher("abacaba");
    assertEquals("abacaba", manacher.palindromicString());
}

@Test
public void testPalindromicStringEvenLength() {
    Manacher manacher = new Manacher("cbbd");
    assertEquals("bb", manacher.palindromicString());
}

@Test
public void testPalindromicStringSingleCharAndNoRepeats() {
    Manacher single = new Manacher("a");
    assertEquals("a", single.palindromicString());

    Manacher distinct = new Manacher("abcdef");
    assertEquals(1, distinct.palindromicString().length());
}

@Test
public void testPalindromicLengthProtectedMethod() {
    Manacher manacher = new Manacher("aba");
    // Preprocessed: "|a|b|a|"
    // Center at 'b' is index 3
    int len = manacher.palindromicLength("|a|b|a|", 3);
    assertEquals(3, len);
}

}
