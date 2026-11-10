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
public void testCalculateSimpleAdditionAndMultiplication() {
    double result1 = calculator.calculate("2 + 3 * 4");
    assertEquals(14.0, result1, 1e-6);

    double result2 = calculator.calculate("2 * 3 + 4");
    assertEquals(10.0, result2, 1e-6);
}

@Test
public void testCalculateWithParentheses() {
    double result = calculator.calculate("(2 + 3) * 4");
    assertEquals(20.0, result, 1e-6);
}

@Test
public void testCalculateDivisionAndModulo() {
    double divResult = calculator.calculate("20 / 4");
    assertEquals(5.0, divResult, 1e-6);

    double modResult = calculator.calculate("10 % 3");
    assertEquals(1.0, modResult, 1e-6);
}

@Test
public void testCalculateUnaryMinusParentheses() {
    double result = calculator.calculate("-(2 + 3)");
    assertEquals(-5.0, result, 1e-6);
}

@Test
public void testCalculateFloatingPoint() {
    double result = calculator.calculate("2.5 * 4.0");
    assertEquals(10.0, result, 1e-6);
}

@Test
public void testTransform() {
    assertEquals("1+2", calculator.transform("1 + 2"));
    assertEquals("0-(3+4)", calculator.transform("-(3 + 4)"));
    assertEquals("5~2", calculator.transform("5 - 2"));
}

@Test
public void testIsOperator() {
    assertTrue(calculator.isOperator('+'));
    assertTrue(calculator.isOperator('-'));
    assertTrue(calculator.isOperator('*'));
    assertTrue(calculator.isOperator('/'));
    assertTrue(calculator.isOperator('%'));

    assertFalse(calculator.isOperator('('));
    assertFalse(calculator.isOperator(')'));
    assertFalse(calculator.isOperator('a'));
    assertFalse(calculator.isOperator('~'));
}

@Test
public void testCompare() {
    assertTrue(calculator.compare('+', '*'));
    assertFalse(calculator.compare('*', '+'));
    assertTrue(calculator.compare('+', '+'));
    assertTrue(calculator.compare('*', '/'));
    assertFalse(calculator.compare('%', '+'));
    assertTrue(calculator.compare('+', '%'));
}

@Test
public void testPrepare() {
    calculator.prepare("3+4*2");
    List<String> postfixTokens = new ArrayList<>(calculator.postfixStack);
    List<String> expected = Arrays.asList("3", "4", "2", "*", "+");
    assertEquals(expected, postfixTokens);
}

@Test
public void testCalculateHelperMethod() {
    assertEquals(15.0, calculator._calculate(10.0, 5.0, '+'), 1e-6);
    assertEquals(5.0, calculator._calculate(10.0, 5.0, '-'), 1e-6);
    assertEquals(50.0, calculator._calculate(10.0, 5.0, '*'), 1e-6);
    assertEquals(2.0, calculator._calculate(10.0, 5.0, '/'), 1e-6);
    assertEquals(1.0, calculator._calculate(10.0, 3.0, '%'), 1e-6);
}

@Test(expected = IllegalArgumentException.class)
public void testCalculateHelperMethodUnsupportedOperator() {
    calculator._calculate(10.0, 5.0, '^');
}

}