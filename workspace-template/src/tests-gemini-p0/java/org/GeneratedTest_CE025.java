package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GeneratedTest_CE025 {

private static final double DELTA = 1e-6;

@Test
public void testAdd() {
    ComplexCalculator calc = new ComplexCalculator();
    ComplexCalculator.ComplexNumber c1 = new ComplexCalculator.ComplexNumber(1.0, 2.0);
    ComplexCalculator.ComplexNumber c2 = new ComplexCalculator.ComplexNumber(3.0, 4.0);

    ComplexCalculator.ComplexNumber result = calc.add(c1, c2);
    assertEquals(4.0, result.getReal(), DELTA);
    assertEquals(6.0, result.getImaginary(), DELTA);
}

@Test
public void testSubtract() {
    ComplexCalculator calc = new ComplexCalculator();
    ComplexCalculator.ComplexNumber c1 = new ComplexCalculator.ComplexNumber(5.0, 7.0);
    ComplexCalculator.ComplexNumber c2 = new ComplexCalculator.ComplexNumber(2.0, 3.0);

    ComplexCalculator.ComplexNumber result = calc.subtract(c1, c2);
    assertEquals(3.0, result.getReal(), DELTA);
    assertEquals(4.0, result.getImaginary(), DELTA);
}

@Test
public void testMultiply() {
    ComplexCalculator calc = new ComplexCalculator();
    // (1 + 2j) * (3 + 4j) = (3 - 8) + (4 + 6)j = -5 + 10j
    ComplexCalculator.ComplexNumber c1 = new ComplexCalculator.ComplexNumber(1.0, 2.0);
    ComplexCalculator.ComplexNumber c2 = new ComplexCalculator.ComplexNumber(3.0, 4.0);

    ComplexCalculator.ComplexNumber result = calc.multiply(c1, c2);
    assertEquals(-5.0, result.getReal(), DELTA);
    assertEquals(10.0, result.getImaginary(), DELTA);
}

@Test
public void testDivide() {
    ComplexCalculator calc = new ComplexCalculator();
    // (1 + 2j) / (1 + 1j) = (1 + 2j)(1 - 1j) / 2 = (1 - 1j + 2j + 2) / 2 = (3 + 1j) / 2 = 1.5 + 0.5j
    ComplexCalculator.ComplexNumber c1 = new ComplexCalculator.ComplexNumber(1.0, 2.0);
    ComplexCalculator.ComplexNumber c2 = new ComplexCalculator.ComplexNumber(1.0, 1.0);

    ComplexCalculator.ComplexNumber result = calc.divide(c1, c2);
    assertEquals(1.5, result.getReal(), DELTA);
    assertEquals(0.5, result.getImaginary(), DELTA);
}

@Test
public void testComplexNumberEqualsAndToString() {
    ComplexCalculator.ComplexNumber num1 = new ComplexCalculator.ComplexNumber(2.0, 3.0);
    ComplexCalculator.ComplexNumber num2 = new ComplexCalculator.ComplexNumber(2.0, 3.0);
    ComplexCalculator.ComplexNumber num3 = new ComplexCalculator.ComplexNumber(2.0, -3.0);

    assertEquals(num1, num2);
    assertNotEquals(num1, num3);
    assertEquals("2.0+3.0j", num1.toString());
    assertEquals("2.0-3.0j", num3.toString());
}

}
