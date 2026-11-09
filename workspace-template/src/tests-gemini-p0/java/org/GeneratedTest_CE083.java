package org.example;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE083 {

private static final double DELTA = 1e-4;

@Test
public void testFactorial() {
    TriCalculator calc = new TriCalculator();
    assertEquals(BigDecimal.ONE, calc.factorial(0));
    assertEquals(BigDecimal.ONE, calc.factorial(1));
    assertEquals(BigDecimal.valueOf(120), calc.factorial(5));
}

@Test
public void testSin() {
    TriCalculator calc = new TriCalculator();
    assertEquals(0.0, calc.sin(0), DELTA);
    assertEquals(0.5, calc.sin(30), DELTA);
    assertEquals(1.0, calc.sin(90), DELTA);
}

@Test
public void testCos() {
    TriCalculator calc = new TriCalculator();
    assertEquals(1.0, calc.cos(0), DELTA);
    assertEquals(0.5, calc.cos(60), DELTA);
    assertEquals(0.0, calc.cos(90), DELTA);
}

@Test
public void testTan() {
    TriCalculator calc = new TriCalculator();
    assertEquals(0.0, calc.tan(0), DELTA);
    assertEquals(1.0, calc.tan(45), DELTA);
}

}
