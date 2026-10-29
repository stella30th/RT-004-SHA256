package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE061 {

@Test
public void testFormatZeroAndNull() {
    NumberWordFormatter formatter = new NumberWordFormatter();
    assertEquals("", formatter.format(null));
    assertEquals("ZERO ONLY", formatter.format("0"));
    assertEquals("ZERO ONLY", formatter.format(0));
}

@Test
public void testFormatSimpleIntegers() {
    NumberWordFormatter formatter = new NumberWordFormatter();
    assertEquals("ONE ONLY", formatter.format("1"));
    assertEquals("FIFTEEN ONLY", formatter.format("15"));
    assertEquals("TWENTY FIVE ONLY", formatter.format("25"));
    assertEquals("ONE HUNDRED ONLY", formatter.format("100"));
    assertEquals("ONE HUNDRED AND TWENTY THREE ONLY", formatter.format("123"));
}

@Test
public void testFormatWithCents() {
    NumberWordFormatter formatter = new NumberWordFormatter();
    assertEquals("ONE HUNDRED AND TWENTY THREE AND CENTS FORTY FIVE ONLY", formatter.format("123.45"));
    assertEquals("TEN AND CENTS FIVE ONLY", formatter.format("10.05"));
}

@Test
public void testTransTwoAndTransThree() {
    NumberWordFormatter formatter = new NumberWordFormatter();
    assertEquals("FOURTEEN", formatter.transTwo("14"));
    assertEquals("SEVENTY", formatter.transTwo("70"));
    assertEquals("EIGHTY NINE", formatter.transTwo("89"));

    assertEquals("FIVE HUNDRED", formatter.transThree("500"));
    assertEquals("TWO HUNDRED AND THIRTY FOUR", formatter.transThree("234"));
}

}
