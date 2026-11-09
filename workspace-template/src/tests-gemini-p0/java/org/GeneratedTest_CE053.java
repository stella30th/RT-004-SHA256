package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE053 {

private static final double DELTA = 1e-4;

@Test
public void testPerfectPredictions() {
    MetricsCalculator mc = new MetricsCalculator();
    int[] predicted = {1, 0, 1, 0};
    int[] actual = {1, 0, 1, 0};

    assertEquals(1.0, mc.accuracy(predicted, actual), DELTA);
    assertEquals(1.0, mc.precision(predicted, actual), DELTA);
    assertEquals(1.0, mc.recall(predicted, actual), DELTA);
    assertEquals(1.0, mc.f1Score(predicted, actual), DELTA);
}

@Test
public void testMixedPredictions() {
    MetricsCalculator mc = new MetricsCalculator();
    // TP=1, FP=1, FN=1, TN=1
    int[] predicted = {1, 1, 0, 0};
    int[] actual = {1, 0, 1, 0};

    assertEquals(0.5, mc.precision(predicted, actual), DELTA);
    assertEquals(0.5, mc.recall(predicted, actual), DELTA);
    assertEquals(0.5, mc.f1Score(predicted, actual), DELTA);
    assertEquals(0.5, mc.accuracy(predicted, actual), DELTA);
}

@Test
public void testAllZeroPredictions() {
    MetricsCalculator mc = new MetricsCalculator();
    int[] predicted = {0, 0, 0};
    int[] actual = {1, 1, 1};

    // TP=0, FP=0, FN=3, TN=0
    assertEquals(0.0, mc.precision(predicted, actual), DELTA);
    assertEquals(0.0, mc.recall(predicted, actual), DELTA);
    assertEquals(0.0, mc.accuracy(predicted, actual), DELTA);
}

@Test
public void testEmptyInputs() {
    MetricsCalculator mc = new MetricsCalculator();
    int[] empty = {};

    assertEquals(0.0, mc.precision(empty, empty), DELTA);
    assertEquals(0.0, mc.recall(empty, empty), DELTA);
    assertEquals(0.0, mc.accuracy(empty, empty), DELTA);
}

}
