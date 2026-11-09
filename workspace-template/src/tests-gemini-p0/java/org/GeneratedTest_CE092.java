package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE092 {

@Test
public void testText2intBasicNumbers() {
    Words2Numbers w2n = new Words2Numbers();
    assertEquals("5", w2n.text2int("five"));
    assertEquals("42", w2n.text2int("forty two"));
    assertEquals("100", w2n.text2int("one hundred"));
}

@Test
public void testText2intCompoundNumbers() {
    Words2Numbers w2n = new Words2Numbers();
    assertEquals("1234", w2n.text2int("one thousand two hundred thirty four"));
}

@Test
public void testText2intOrdinals() {
    Words2Numbers w2n = new Words2Numbers();
    assertEquals("1", w2n.text2int("first"));
    assertEquals("2", w2n.text2int("second"));
    assertEquals("20", w2n.text2int("twentieth"));
}

@Test
public void testIsValidInput() {
    Words2Numbers w2n = new Words2Numbers();
    assertTrue(w2n.isValidInput("twenty one"));
    assertTrue(w2n.isValidInput("first"));
    assertFalse(w2n.isValidInput("twenty invalidword"));
}

}
