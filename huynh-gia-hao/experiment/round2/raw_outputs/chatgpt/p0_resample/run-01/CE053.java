
package org.example;

// BEGIN GeneratedTest_CE053

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE053 {

    private static final double DELTA = 1e-9;

    @Test
    public void testBalancedClassificationMetrics() {
        int[] predicted = {1, 1, 0, 0};
        int[] actual = {1, 0, 0, 1};

        assertEquals(0.5, new MetricsCalculator().precision(predicted, actual), DELTA);
        assertEquals(0.5, new MetricsCalculator().recall(predicted, actual), DELTA);
        assertEquals(0.5, new MetricsCalculator().f1Score(predicted, actual), DELTA);
        assertEquals(0.5, new MetricsCalculator().accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testPerfectPredictions() {
        int[] predicted = {1, 0, 1, 0, 1};
        int[] actual = {1, 0, 1, 0, 1};

        MetricsCalculator calculator = new MetricsCalculator();

        assertEquals(1.0, calculator.precision(predicted, actual), DELTA);
        assertEquals(1.0, calculator.recall(predicted, actual), DELTA);
        assertEquals(1.0, calculator.f1Score(predicted, actual), DELTA);
        assertEquals(1.0, calculator.accuracy(predicted, actual), DELTA);

        assertEquals(12, calculator.truePositives);
        assertEquals(8, calculator.trueNegatives);
        assertEquals(0, calculator.falsePositives);
        assertEquals(0, calculator.falseNegatives);
    }

    @Test
    public void testCompletelyIncorrectPredictions() {
        int[] predicted = {1, 1, 0, 0};
        int[] actual = {0, 0, 1, 1};

        MetricsCalculator calculator = new MetricsCalculator();

        assertEquals(0.0, calculator.precision(predicted, actual), DELTA);
        assertEquals(0.0, calculator.recall(predicted, actual), DELTA);
        assertEquals(0.0, calculator.f1Score(predicted, actual), DELTA);
        assertEquals(0.0, calculator.accuracy(predicted, actual), DELTA);
    }

    @Test
    public void testEmptyInputs() {
        int[] predicted = {};
        int[] actual = {};

        MetricsCalculator calculator = new MetricsCalculator();

        assertEquals(0.0, calculator.precision(predicted, actual), DELTA);
        assertEquals(0.0, calculator.recall(predicted, actual), DELTA);
        assertEquals(0.0, calculator.f1Score(predicted, actual), DELTA);
        assertEquals(0.0, calculator.accuracy(predicted, actual), DELTA);

        assertEquals(0, calculator.truePositives);
        assertEquals(0, calculator.falsePositives);
        assertEquals(0, calculator.falseNegatives);
        assertEquals(0, calculator.trueNegatives);
    }

    @Test
    public void testUpdateAccumulatesCounts() {
        MetricsCalculator calculator = new MetricsCalculator();

        calculator.update(
            new int[]{1, 1, 0, 0},
            new int[]{1, 0, 1, 0}
        );

        assertEquals(1, calculator.truePositives);
        assertEquals(1, calculator.falsePositives);
        assertEquals(1, calculator.falseNegatives);
        assertEquals(1, calculator.trueNegatives);

        calculator.update(
            new int[]{1, 0, 0},
            new int[]{1, 0, 1}
        );

        assertEquals(2, calculator.truePositives);
        assertEquals(1, calculator.falsePositives);
        assertEquals(2, calculator.falseNegatives);
        assertEquals(2, calculator.trueNegatives);

        assertEquals(
            2.0 / 3.0,
            calculator.precision(new int[]{}, new int[]{}),
            DELTA
        );
        assertEquals(
            0.5,
            calculator.recall(new int[]{}, new int[]{}),
            DELTA
        );
        assertEquals(
            4.0 / 7.0,
            calculator.accuracy(new int[]{}, new int[]{}),
            DELTA
        );
    }

    @Test
    public void testUnrecognizedLabelsAreIgnored() {
        MetricsCalculator calculator = new MetricsCalculator();

        int[] predicted = {2, -1, 1, 0, 2};
        int[] actual = {2, 1, 1, 0, 0};

        calculator.update(predicted, actual);

        assertEquals(1, calculator.truePositives);
        assertEquals(0, calculator.falsePositives);
        assertEquals(0, calculator.falseNegatives);
        assertEquals(1, calculator.trueNegatives);

        assertEquals(
            1.0,
            calculator.accuracy(new int[]{}, new int[]{}),
            DELTA
        );
    }
}

// END GeneratedTest_CE053
