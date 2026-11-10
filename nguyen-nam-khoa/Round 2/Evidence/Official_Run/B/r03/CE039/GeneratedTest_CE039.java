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
public void testIsOperator() {
    assertTrue(calculator.isOperator('+'));
    assertTrue(calculator.isOperator('-'));
    assertTrue(calculator.isOperator('*'));
    assertTrue(calculator.isOperator('/'));
    assertTrue(calculator.isOperator('%'));

    assertFalse(calculator.isOperator('('));
    assertFalse(calculator.isOperator(')'));
    assertFalse(calculator.isOperator('~'));
    assertFalse(calculator.isOperator('0'));
    assertFalse(calculator.isOperator('x'));
}

@Test
public void testComparePriority() {
    // '+' has priority 1, '*' has priority 2
    assertTrue(calculator.compare('+', '*'));
    assertFalse(calculator.compare('*', '+'));

    // '+' and '-' have the same priority 1
    assertTrue(calculator.compare('+', '-'));
    assertTrue(calculator.compare('-', '+'));

    // '*' and '/' and '%' have priority 2
    assertTrue(calculator.compare('*', '/'));
    assertTrue(calculator.compare('/', '%'));
    assertTrue(calculator.compare('%', '*'));
}

@Test
public void testTransform() {
    // Spaces should be removed
    assertEquals("1+2", calculator.transform(" 1 + 2 "));

    // Leading -(...) becomes 0-(...)
    assertEquals("0-(3+4)", calculator.transform("-(3+4)"));

    // Regular '-' becomes '~'
    assertEquals("5~2", calculator.transform("5 - 2"));
}

@Test
public void testPrepare() {
    calculator.prepare("3+4*2");
    List<String> actual = new ArrayList<>(calculator.postfixStack);
    List<String> expected = Arrays.asList("3", "4", "2", "*", "+");
    assertEquals(expected, actual);

    calculator.prepare("(1+2)*(3+4)");
    List<String> actualParentheses = new ArrayList<>(calculator.postfixStack);
    List<String> expectedParentheses = Arrays.asList("1", "2", "+", "3", "4", "+", "*");
    assertEquals(expectedParentheses, actualParentheses);
}

@Test
public void testCalculateSimpleAndPrecedence() {
    assertEquals(14.0, calculator.calculate("2 + 3 * 4"), 1e-9);
    assertEquals(10.0, calculator.calculate("2 * 3 + 4"), 1e-9);
    assertEquals(100.0, calculator.calculate("100 * ( 2 + 12 ) / 14"), 1e-9);
    assertEquals(1.0, calculator.calculate("10 % 3"), 1e-9);
}

@Test
public void testCalculateNegativeParentheses() {
    assertEquals(-7.0, calculator.calculate("-(3 + 4)"), 1e-9);
    assertEquals(-12.0, calculator.calculate("-(2 * 6)"), 1e-9);
}

@Test
public void testCalculateDecimals() {
    assertEquals(5.5, calculator.calculate("2.5 + 3.0"), 1e-9);
    assertEquals(3.75, calculator.calculate("1.5 * 2.5"), 1e-9);
}

@Test
public void testInternalCalculate() {
    assertEquals(9.0, calculator._calculate(6.0, 3.0, '+'), 1e-9);
    assertEquals(3.0, calculator._calculate(6.0, 3.0, '-'), 1e-9);
    assertEquals(18.0, calculator._calculate(6.0, 3.0, '*'), 1e-9);
    assertEquals(2.0, calculator._calculate(6.0, 3.0, '/'), 1e-9);
    assertEquals(1.0, calculator._calculate(7.0, 3.0, '%'), 1e-9);
}

@Test(expected = IllegalArgumentException.class)
public void testInternalCalculateUnsupportedOperator() {
    calculator._calculate(2.0, 3.0, '^');
}

}