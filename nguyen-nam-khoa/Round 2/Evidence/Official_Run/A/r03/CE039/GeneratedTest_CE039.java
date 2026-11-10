package org.example;

import org.junit.Before; import org.junit.Test;

import java.util.ArrayDeque; import java.util.Deque;

import static org.junit.Assert.*;

public class GeneratedTest_CE039 {

private ExpressionCalculator calculator;

@Before
public void setUp() {
    calculator = new ExpressionCalculator();
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
    assertFalse(calculator.isOperator('0'));
    assertFalse(calculator.isOperator('x'));
}

@Test
public void testComparePrecedence() {
    // '*' has higher precedence than '+'
    // compare(cur_op, peek_op): returns peek_op priority >= cur_op priority
    assertTrue(calculator.compare('+', '*'));
    assertFalse(calculator.compare('*', '+'));

    // Equal precedence
    assertTrue(calculator.compare('+', '+'));
    assertTrue(calculator.compare('*', '/'));

    // Modulo '%' is treated as '/'
    assertTrue(calculator.compare('+', '%'));
    assertTrue(calculator.compare('%', '/'));
}

@Test
public void testCalculateBasicArithmetic() {
    double result1 = calculator.calculate("2 + 3 * 4");
    assertEquals(14.0, result1, 1e-9);

    double result2 = calculator.calculate("(2 + 3) * 4");
    assertEquals(20.0, result2, 1e-9);

    double result3 = calculator.calculate("20 / 4 + 7 % 4");
    assertEquals(8.0, result3, 1e-9);
}

@Test
public void testCalculateFloatingPoint() {
    double result = calculator.calculate("2.5 * 4 + 1.25");
    assertEquals(11.25, result, 1e-9);
}

@Test
public void testCalculateInternalHelper() {
    assertEquals(8.0, calculator._calculate(5.0, 3.0, '+'), 1e-9);
    assertEquals(2.0, calculator._calculate(5.0, 3.0, '-'), 1e-9);
    assertEquals(15.0, calculator._calculate(5.0, 3.0, '*'), 1e-9);
    assertEquals(2.5, calculator._calculate(5.0, 2.0, '/'), 1e-9);
    assertEquals(1.0, calculator._calculate(5.0, 2.0, '%'), 1e-9);
}

@Test(expected = IllegalArgumentException.class)
public void testCalculateInternalHelperUnsupportedOperator() {
    calculator._calculate(5.0, 2.0, '^');
}

@Test
public void testTransform() {
    assertEquals("1+2*3", calculator.transform(" 1 +  2 * 3 "));
    assertEquals("1~2", calculator.transform("1 - 2"));
    assertEquals("0-(1+2)", calculator.transform("-(1 + 2)"));
}

@Test
public void testPrepareGeneratesPostfixStack() {
    calculator.prepare("3+4*2");
    Deque<String> expected = new ArrayDeque<>();
    expected.add("3");
    expected.add("4");
    expected.add("2");
    expected.add("*");
    expected.add("+");

    assertEquals(expected.size(), calculator.postfixStack.size());
    assertArrayEquals(expected.toArray(), calculator.postfixStack.toArray());
}

@Test
public void testPrepareWithParentheses() {
    calculator.prepare("(3+4)*2");
    Deque<String> expected = new ArrayDeque<>();
    expected.add("3");
    expected.add("4");
    expected.add("+");
    expected.add("2");
    expected.add("*");

    assertEquals(expected.size(), calculator.postfixStack.size());
    assertArrayEquals(expected.toArray(), calculator.postfixStack.toArray());
}

}