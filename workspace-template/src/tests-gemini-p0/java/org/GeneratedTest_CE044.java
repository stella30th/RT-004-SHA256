package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE044 {

private static final double DELTA = 1e-4;

@Test
public void testInterpolate1dExactPoints() {
    List<Double> x = Arrays.asList(0.0, 1.0, 2.0);
    List<Double> y = Arrays.asList(0.0, 10.0, 20.0);
    List<Double> xInterp = Arrays.asList(0.0, 1.0, 2.0);

    List<Double> result = Interpolation.interpolate1d(x, y, xInterp);
    assertEquals(3, result.size());
    assertEquals(0.0, result.get(0), DELTA);
    assertEquals(10.0, result.get(1), DELTA);
    assertEquals(20.0, result.get(2), DELTA);
}

@Test
public void testInterpolate1dIntermediatePoints() {
    List<Double> x = Arrays.asList(0.0, 10.0);
    List<Double> y = Arrays.asList(0.0, 100.0);
    List<Double> xInterp = Arrays.asList(2.5, 5.0, 7.5);

    List<Double> result = Interpolation.interpolate1d(x, y, xInterp);
    assertEquals(3, result.size());
    assertEquals(25.0, result.get(0), DELTA);
    assertEquals(50.0, result.get(1), DELTA);
    assertEquals(75.0, result.get(2), DELTA);
}

@Test
public void testInterpolate2dExactGridPoints() {
    List<Double> x = Arrays.asList(0.0, 1.0);
    List<Double> y = Arrays.asList(0.0, 1.0);
    List<List<Double>> z = Arrays.asList(
            Arrays.asList(0.0, 1.0),
            Arrays.asList(2.0, 3.0)
    );

    List<Double> xInterp = Arrays.asList(0.0, 1.0);
    List<Double> yInterp = Arrays.asList(0.0, 1.0);

    List<Double> result = Interpolation.interpolate2d(x, y, z, xInterp, yInterp);
    assertEquals(2, result.size());
    assertEquals(0.0, result.get(0), DELTA);
    assertEquals(3.0, result.get(1), DELTA);
}

@Test
public void testInterpolate2dCenter() {
    List<Double> x = Arrays.asList(0.0, 2.0);
    List<Double> y = Arrays.asList(0.0, 2.0);
    List<List<Double>> z = Arrays.asList(
            Arrays.asList(0.0, 0.0),
            Arrays.asList(4.0, 4.0)
    );

    List<Double> xInterp = Arrays.asList(1.0);
    List<Double> yInterp = Arrays.asList(1.0);

    List<Double> result = Interpolation.interpolate2d(x, y, z, xInterp, yInterp);
    assertEquals(1, result.size());
    assertEquals(2.0, result.get(0), DELTA);
}

}
