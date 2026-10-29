package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE039 {

private static final double DELTA = 1e-4;

@Test
public void testCalculateBasicArithmetic() {
    ExpressionCalculator calc = new ExpressionCalculator();
    assertEquals(14.0, calc.calculate("2+3*4"), DELTA);
    assertEquals(7.0, calc.calculate("10-6/2"), DELTA);
    assertEquals(1.0, calc.calculate("7%3"), DELTA);
}

@Test
public void testCalculateParentheses() {
    ExpressionCalculator calc = new ExpressionCalculator();
    assertEquals(20.0, calc.calculate("(2+3)*4"), DELTA);
    assertEquals(5.0, calc.calculate("((10+5)/3)"), DELTA);
}

@Test
public void testTransformNegativeStart() {
    ExpressionCalculator calc = new ExpressionCalculator();
    String transformed = calc.transform("-(2+3)");
    assertEquals("0-(2+3)", transformed);

    String regular = calc.transform("5 - 3");
    assertEquals("5~3", regular);
}

@Test
public void testIsOperatorAndCompare() {
    ExpressionCalculator calc = new ExpressionCalculator();
    assertTrue(calc.isOperator('+'));
    assertTrue(calc.isOperator('-'));
    assertTrue(calc.isOperator('*'));
    assertTrue(calc.isOperator('/'));
    assertTrue(calc.isOperator('%'));
    assertFalse(calc.isOperator('a'));

    // '*' has higher precedence than '+'
    assertTrue(calc.compare('*', '+'));
}

}
