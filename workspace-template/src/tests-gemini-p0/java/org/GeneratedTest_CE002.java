package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE002 {

private static final double DELTA = 1e-6;

@Test
public void testCalculateCircleArea() {
    AreaCalculator calculator = new AreaCalculator(3.0);
    double expected = Math.PI * 9.0;
    assertEquals(expected, calculator.calculateCircleArea(), DELTA);
}

@Test
public void testCalculateSphereArea() {
    AreaCalculator calculator = new AreaCalculator(2.0);
    double expected = 4 * Math.PI * 4.0;
    assertEquals(expected, calculator.calculateSphereArea(), DELTA);
}

@Test
public void testCalculateCylinderArea() {
    AreaCalculator calculator = new AreaCalculator(2.0);
    double height = 3.0;
    double expected = 2 * Math.PI * 2.0 * (2.0 + 3.0);
    assertEquals(expected, calculator.calculateCylinderArea(height), DELTA);
}

@Test
public void testCalculateSectorArea() {
    AreaCalculator calculator = new AreaCalculator(4.0);
    double angle = Math.PI / 2.0;
    double expected = 16.0 * (Math.PI / 2.0) / 2.0;
    assertEquals(expected, calculator.calculateSectorArea(angle), DELTA);
}

@Test
public void testCalculateAnnulusArea() {
    AreaCalculator calculator = new AreaCalculator(1.0);
    double innerRadius = 2.0;
    double outerRadius = 5.0;
    double expected = Math.PI * (25.0 - 4.0);
    assertEquals(expected, calculator.calculateAnnulusArea(innerRadius, outerRadius), DELTA);
}

}
