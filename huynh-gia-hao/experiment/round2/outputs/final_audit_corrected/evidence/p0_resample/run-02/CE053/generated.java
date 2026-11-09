// BEGIN GeneratedTest_CE053
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE053 {

    private static final double DELTA = 1e-9;

    @Test
    public void testInitialCountersAreZero() {
        MetricsCalculator mc = new MetricsCalculator();

        assertEquals(0, mc.truePositives);
        assertEquals(0, mc.falsePositives);
        assertEquals(0, mc.falseNegatives);
        assertEquals(0, mc.trueNegatives);
    }

    @Test
    public void testUpdateConfusionMatrix() {
        MetricsCalculator mc = new MetricsCalculator();

        mc.update(
                new int[]{1, 1, 0, 0},
                new int[]{1, 0, 1, 0}
        );

        assertEquals(1, mc.truePositives);
        assertEquals(1, mc.falsePositives);
        assertEquals(1, mc.falseNegatives);
        assertEquals(1, mc.trueNegatives);
    }

    @Test
    public void testUpdateAccumulatesCounts() {
        MetricsCalculator mc = new MetricsCalculator();

        mc.update(new int[]{1, 0}, new int[]{1, 0});
        mc.update(new int[]{1, 0}, new int[]{0, 1});

        assertEquals(1, mc.truePositives);
        assertEquals(1, mc.falsePositives);
        assertEquals(1, mc.falseNegatives);
        assertEquals(1, mc.trueNegatives);
    }

    @Test
    public void testPrecision() {
        MetricsCalculator mc = new MetricsCalculator();

        assertEquals(0.5,
                mc.precision(
                        new int[]{1, 1, 0, 0},
                        new int[]{1, 0, 1, 0}
                ), DELTA);
    }

    @Test
    public void testRecall() {
        MetricsCalculator mc = new MetricsCalculator();

        assertEquals(0.5,
                mc.recall(
                        new int[]{1, 1, 0, 0},
                        new int[]{1, 0, 1, 0}
                ), DELTA);
    }

    @Test
    public void testF1Score() {
        MetricsCalculator mc = new MetricsCalculator();

        assertEquals(0.5,
                mc.f1Score(
                        new int[]{1, 1, 0, 0},
                        new int[]{1, 0, 1, 0}
                ), DELTA);
    }

    @Test
    public void testAccuracy() {
        MetricsCalculator mc = new MetricsCalculator();

        assertEquals(0.5,
                mc.accuracy(
                        new int[]{1, 1, 0, 0},
                        new int[]{1, 0, 1, 0}
                ), DELTA);
    }

    @Test
    public void testPerfectPredictions() {
        int[] predicted = {1, 0, 1, 0};
        int[] actual = {1, 0, 1, 0};

        assertEquals(1.0,
                new MetricsCalculator().precision(predicted, actual), DELTA);
        assertEquals(1.0,
                new MetricsCalculator().recall(predicted, actual), DELTA);
        assertEquals(1.0,
                new MetricsCalculator().f1Score(predicted, actual), DELTA);
        assertEquals(1.0,
                new MetricsCalculator().accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testCompletelyIncorrectPredictions() {
        int[] predicted = {1, 0, 1, 0};
        int[] actual = {0, 1, 0, 1};

        assertEquals(0.0,
                new MetricsCalculator().precision(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().recall(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().f1Score(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testNoPredictedPositives() {
        int[] predicted = {0, 0, 0};
        int[] actual = {1, 0, 1};

        assertEquals(0.0,
                new MetricsCalculator().precision(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().recall(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().f1Score(predicted, actual), DELTA);
        assertEquals(1.0 / 3.0,
                new MetricsCalculator().accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testNoActualPositives() {
        int[] predicted = {1, 0, 0};
        int[] actual = {0, 0, 0};

        assertEquals(0.0,
                new MetricsCalculator().precision(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().recall(predicted, actual), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().f1Score(predicted, actual), DELTA);
        assertEquals(2.0 / 3.0,
                new MetricsCalculator().accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testEmptyArraysReturnZero() {
        int[] empty = {};

        assertEquals(0.0,
                new MetricsCalculator().precision(empty, empty), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().recall(empty, empty), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().f1Score(empty, empty), DELTA);
        assertEquals(0.0,
                new MetricsCalculator().accuracy(empty, empty), DELTA);
    }

    @Test
    public void testMetricCallsAccumulateState() {
        MetricsCalculator mc = new MetricsCalculator();
        int[] predicted = {1, 1, 0, 0};
        int[] actual = {1, 0, 1, 0};

        assertEquals(0.5, mc.precision(predicted, actual), DELTA);
        assertEquals(0.5, mc.recall(predicted, actual), DELTA);

        assertEquals(2, mc.truePositives);
        assertEquals(2, mc.falsePositives);
        assertEquals(2, mc.falseNegatives);
        assertEquals(2, mc.trueNegatives);
    }

    @Test
    public void testF1ScoreUpdatesStateThreeTimes() {
        MetricsCalculator mc = new MetricsCalculator();
        int[] predicted = {1, 1, 0, 0};
        int[] actual = {1, 0, 1, 0};

        assertEquals(0.5, mc.f1Score(predicted, actual), DELTA);

        assertEquals(3, mc.truePositives);
        assertEquals(3, mc.falsePositives);
        assertEquals(3, mc.falseNegatives);
        assertEquals(3, mc.trueNegatives);
    }

    @Test
    public void testUpdateIgnoresNonBinaryLabels() {
        MetricsCalculator mc = new MetricsCalculator();

        mc.update(
                new int[]{2, -1, 1, 0, 1},
                new int[]{2, 0, 2, -1, 1}
        );

        assertEquals(1, mc.truePositives);
        assertEquals(0, mc.falsePositives);
        assertEquals(0, mc.falseNegatives);
        assertEquals(0, mc.trueNegatives);
    }

    @Test
    public void testAccuracyAfterPreviousUpdate() {
        MetricsCalculator mc = new MetricsCalculator();

        mc.update(new int[]{1, 0}, new int[]{1, 0});

        assertEquals(0.75,
                mc.accuracy(
                        new int[]{1, 0},
                        new int[]{0, 0}
                ), DELTA);
    }

    @Test
    public void testIndependentCalculatorInstances() {
        MetricsCalculator first = new MetricsCalculator();
        MetricsCalculator second = new MetricsCalculator();

        first.update(new int[]{1, 1}, new int[]{1, 0});
        second.update(new int[]{0, 0}, new int[]{0, 1});

        assertEquals(1, first.truePositives);
        assertEquals(1, first.falsePositives);
        assertEquals(0, first.trueNegatives);

        assertEquals(0, second.truePositives);
        assertEquals(0, second.falsePositives);
        assertEquals(1, second.falseNegatives);
        assertEquals(1, second.trueNegatives);
    }
}
// END GeneratedTest_CE053