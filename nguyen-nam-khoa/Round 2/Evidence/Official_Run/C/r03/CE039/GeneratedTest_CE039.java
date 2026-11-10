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
public void testCalculateSimpleAndPrecedence() {
    double resultMixed = calculator.calculate("2 + 3 * 4");
    assertEquals(14.0, resultMixed, 1e-9);

    double resultParens = calculator.calculate("(2 + 3) * 4");
    assertEquals(20.0, resultParens, 1e-9);

    double resultDecimal = calculator.calculate("2.5 * 4");
    assertEquals(10.0, resultDecimal, 1e-9);
}

@Test
public void testCalculateWithParenthesizedUnaryMinus() {
    double resultNegative = calculator.calculate("-(2 + 3)");
    assertEquals(-5.0, resultNegative, 1e-9);

    double resultNegativeProduct = calculator.calculate("-(4 * 2.5)");
    assertEquals(-10.0, resultNegativeProduct, 1e-9);
}

@Test
public void testCalculateModuloAndDivision() {
    double resultMod = calculator.calculate("10 % 3 + 4");
    assertEquals(5.0, resultMod, 1e-9);

    double resultDiv = calculator.calculate("20 / 4 + 1");
    assertEquals(6.0, resultDiv, 1e-9);
}

@Test
public void testTransform() {
    assertEquals("1+2", calculator.transform(" 1 + 2 "));
    assertEquals("0-(1+2)", calculator.transform("-(1 + 2)"));
    assertEquals("10~5", calculator.transform("10 - 5"));
    assertEquals("~5", calculator.transform("-5"));
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
    assertFalse(calculator.isOperator('.'));
    assertFalse(calculator.isOperator('1'));
    assertFalse(calculator.isOperator('~'));
    assertFalse(calculator.isOperator('^'));
}

@Test
public void testCompare() {
    assertTrue(calculator.compare('+', '*'));
    assertFalse(calculator.compare('*', '+'));
    assertTrue(calculator.compare('+', '+'));
    assertTrue(calculator.compare('%', '/'));
    assertTrue(calculator.compare('/', '%'));
}

@Test(expected = IllegalArgumentException.class)
public void testCalculateUnsupportedOperator() {
    calculator._calculate(2.0, 3.0, '^');
}

@Test
public void testCalculateBasicOperations() {
    assertEquals(7.0, calculator._calculate(5.0, 2.0, '+'), 1e-9);
    assertEquals(3.0, calculator._calculate(5.0, 2.0, '-'), 1e-9);
    assertEquals(10.0, calculator._calculate(5.0, 2.0, '*'), 1e-9);
    assertEquals(2.5, calculator._calculate(5.0, 2.0, '/'), 1e-9);
    assertEquals(1.0, calculator._calculate(5.0, 2.0, '%'), 1e-9);
}

@Test
public void testPrepareAndPostfixStackState() {
    calculator.prepare("2+3*4");
    List<String> expectedTokens1 = Arrays.asList("2", "3", "4", "*", "+");
    assertEquals(expectedTokens1, new ArrayList<>(calculator.postfixStack));

    calculator.prepare("10*2");
    List<String> expectedTokens2 = Arrays.asList("10", "2", "*");
    assertEquals(expectedTokens2, new ArrayList<>(calculator.postfixStack));
}

}