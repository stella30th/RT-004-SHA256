package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE010 {

@Test
public void testAdd() {
    String num1 = "98765432109876543210";
    String num2 = "12345678901234567890";
    String expected = "111111111011111111100";
    assertEquals(expected, BigNumCalculator.add(num1, num2));
}

@Test
public void testSubtractPositiveResult() {
    String num1 = "9876543210";
    String num2 = "1234567890";
    String expected = "8641975320";
    assertEquals(expected, BigNumCalculator.subtract(num1, num2));
}

@Test
public void testSubtractNegativeResult() {
    String num1 = "100";
    String num2 = "250";
    String expected = "-150";
    assertEquals(expected, BigNumCalculator.subtract(num1, num2));
}

@Test
public void testMultiply() {
    String num1 = "12345";
    String num2 = "6789";
    String expected = "83810205";
    assertEquals(expected, BigNumCalculator.multiply(num1, num2));

    assertEquals("0", BigNumCalculator.multiply("99999", "0"));
}

}
