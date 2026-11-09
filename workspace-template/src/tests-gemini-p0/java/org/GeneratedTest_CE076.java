package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE076 {

private static final double DELTA = 1e-4;

@Test
public void testMeanAndMedian() {
    Statistics3 stats = new Statistics3();
    int[] dataOdd = {1, 3, 2}; // median = 2.0, mean = 2.0
    assertEquals(2.0, stats.median(dataOdd), DELTA);
    assertEquals(Double.valueOf(2.0), stats.mean(dataOdd));

    int[] dataEven = {1, 2, 3, 4}; // median = 2.5, mean = 2.5
    assertEquals(2.5, stats.median(dataEven), DELTA);
    assertEquals(Double.valueOf(2.5), stats.mean(dataEven));

    assertNull(stats.mean(new int[]{}));
}

@Test
public void testMode() {
    Statistics3 stats = new Statistics3();
    int[] data = {1, 2, 2, 3};
    int[] mode = stats.mode(data);
    assertArrayEquals(new int[]{2}, mode);
}

@Test
public void testCorrelation() {
    Statistics3 stats = new Statistics3();
    int[] x = {1, 2, 3, 4, 5};
    int[] y = {2, 4, 6, 8, 10};

    Double corr = stats.correlation(x, y);
    assertNotNull(corr);
    assertEquals(1.0, corr, DELTA);

    assertNull(stats.correlation(new int[]{1}, new int[]{1, 2}));
}

@Test
public void testStandardDeviationAndZScore() {
    Statistics3 stats = new Statistics3();
    int[] data = {10, 12, 23, 23, 16, 23, 21, 16};

    Double stdDev = stats.standardDeviation(data);
    assertNotNull(stdDev);
    assertTrue(stdDev > 0);

    double[] zScores = stats.zScore(data);
    assertNotNull(zScores);
    assertEquals(data.length, zScores.length);

    assertNull(stats.standardDeviation(new int[]{1})); // len < 2
}

}
