package org.example;

import org.junit.Before; import org.junit.Test;

import java.util.ArrayList; import java.util.Arrays; import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE039 {

private ExpressionCalculator calculator;

@Before
public void setUp() {
    calculator = new ExpressionCalculator();
}

@Test
public void testCalculateBasicArithmetic() {
    assertEquals(14.0, calculator.calculate("2 + 3 * 4"), 1e-9);
    assertEquals(5.0, calculator.calculate("10 / 2"), 1e-9);
    assertEquals(1.0, calculator.calculate("10 % 3"), 1e-9);
    assertEquals(17.0, calculator.calculate("5 + 6 * 2"), 1e-9);
}

@Test
public void testCalculateWithParentheses() {
    assertEquals(20.0, calculator.calculate("(2 + 3) * 4"), 1e-9);
    assertEquals(25.0, calculator.calculate("((2 + 3) * (4 + 1))"), 1e-9);
    assertEquals(2.0, calculator.calculate("(8 / (2 * 2))"), 1e-9);
}

@Test
public void testCalculateUnaryMinusWithParentheses() {
    assertEquals(-5.0, calculator.calculate("-(2 + 3)"), 1e-9);
    assertEquals(-14.0, calculator.calculate(" - ( 2 + 3 * 4 ) "), 1e-9);
}

@Test
public void testCalculateDecimals() {
    assertEquals(4.0, calculator.calculate("1.5 + 2.5"), 1e-9);
    assertEquals(3.5, calculator.calculate("7.0 / 2.0"), 1e-9);
    assertEquals(10.0, calculator.calculate("2.5 * 4"), 1e-9);
}

@Test
public void testTransform() {
    assertEquals("2+3*4", calculator.transform(" 2 + 3 * 4 "));
    assertEquals("0-(2+3)", calculator.transform("-(2+3)"));
    assertEquals("0-(2+3)", calculator.transform(" - ( 2 + 3 ) "));
    assertEquals("~5+3", calculator.transform("-5 + 3"));
}

@Test
public void testIsOperator() {
    assertTrue(calculator.isOperator('+'));
    assertTrue(calculator.isOperator('-'));
    assertTrue(calculator.isOperator('*'));
    assertTrue(calculator.isOperator('/'));
    assertTrue(calculator.isOperator('%'));

    assertFalse(calculator.isOperator('~'));
    assertFalse(calculator.isOperator('('));
    assertFalse(calculator.isOperator(')'));
    assertFalse(calculator.isOperator('1'));
    assertFalse(calculator.isOperator(' '));
}

@Test
public void testComparePrecedence() {
    assertTrue(calculator.compare('+', '*'));
    assertFalse(calculator.compare('*', '+'));
    assertTrue(calculator.compare('+', '+'));
    assertTrue(calculator.compare('+', '-'));
    assertTrue(calculator.compare('/', '*'));
    assertTrue(calculator.compare('%', '/'));
    assertFalse(calculator.compare('%', '+'));
}

@Test
public void testCalculateHelperMethod() {
    assertEquals(15.0, calculator._calculate(10.0, 5.0, '+'), 1e-9);
    assertEquals(5.0, calculator._calculate(10.0, 5.0, '-'), 1e-9);
    assertEquals(50.0, calculator._calculate(10.0, 5.0, '*'), 1e-9);
    assertEquals(2.0, calculator._calculate(10.0, 5.0, '/'), 1e-9);
    assertEquals(2.0, calculator._calculate(10.0, 4.0, '%'), 1e-9);
}

@Test(expected = IllegalArgumentException.class)
public void testCalculateHelperUnsupportedOperator() {
    calculator._calculate(10.0, 5.0, '^');
}

@Test
public void testPreparePostfixStack() {
    calculator.prepare("3+4*2");
    List<String> expectedTokens = Arrays.asList("3", "4", "2", "*", "+");
    assertEquals(expectedTokens, new ArrayList<>(calculator.postfixStack));
}

}