package org.example;

import org.junit.Before;
import org.junit.Test;

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
        assertFalse(calculator.isOperator('a'));
    }

    @Test
    public void testCompareOperatorPriority() {
        assertTrue(calculator.compare('+', '*'));
        assertFalse(calculator.compare('*', '+'));
        assertTrue(calculator.compare('+', '+'));
        assertTrue(calculator.compare('%', '/'));
    }

    @Test
    public void testTransform() {
        assertEquals("1+2", calculator.transform("1 + 2"));
        assertEquals("1~2", calculator.transform("1 - 2"));
        assertEquals("0-(2+3)", calculator.transform("-(2 + 3)"));
    }

    @Test
    public void testCalculateSimpleExpressions() {
        assertEquals(14.0, calculator.calculate("2 + 3 * 4"), 1e-6);
        assertEquals(10.0, calculator.calculate("2 * 3 + 4"), 1e-6);
        assertEquals(8.0, calculator.calculate("10 / 2 + 3"), 1e-6);
        assertEquals(1.0, calculator.calculate("10 % 3"), 1e-6);
        assertEquals(-5.0, calculator.calculate("-(2 + 3)"), 1e-6);
    }

    @Test
    public void testCalculateIgnoredHyphenBehavior() {
        assertEquals(3.0, calculator.calculate("2 - 3"), 1e-6);
    }
}
