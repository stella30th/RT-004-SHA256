package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GeneratedTest_CE017 {

private static final double DELTA = 1e-6;

@Test
public void testSimpleArithmeticAndPrecedence() {
    Calculator calculator = new Calculator();
    assertEquals(0.0, calculator.calculate("1+2-3"), DELTA);
    assertEquals(14.0, calculator.calculate("2+3*4"), DELTA);
    assertEquals(2.0, calculator.calculate("10/5"), DELTA);
}

@Test
public void testPowerOperator() {
    Calculator calculator = new Calculator();
    assertEquals(8.0, calculator.calculate("2^3"), DELTA);
    assertEquals(17.0, calculator.calculate("1+2^4"), DELTA);
}

@Test
public void testParentheses() {
    Calculator calculator = new Calculator();
    assertEquals(20.0, calculator.calculate("(2+3)*4"), DELTA);
    assertEquals(18.0, calculator.calculate("((1+2)*3)*2"), DELTA);
}

@Test
public void testDecimalsAndEmpty() {
    Calculator calculator = new Calculator();
    assertEquals(8.5, calculator.calculate("3.5+5"), DELTA);
    assertNull(calculator.calculate(""));
}

}
