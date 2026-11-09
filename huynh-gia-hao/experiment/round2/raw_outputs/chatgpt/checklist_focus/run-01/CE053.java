// BEGIN GeneratedTest_CE053
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE053 {

    @Test public void updateCountsConfusionMatrixAndAccumulates() {
        MetricsCalculator m = new MetricsCalculator();
        m.update(new int[]{1, 1, 0, 0}, new int[]{1, 0, 1, 0});
        assertEquals(1, m.truePositives);
        assertEquals(1, m.falsePositives);
        assertEquals(1, m.falseNegatives);
        assertEquals(1, m.trueNegatives);
        m.update(new int[]{1, 0}, new int[]{1, 0});
        assertEquals(2, m.truePositives);
        assertEquals(2, m.trueNegatives);
    }
    @Test public void balancedMetricsAreHalf() {
        int[] predicted = {1, 1, 0, 0};
        int[] actual = {1, 0, 1, 0};
        assertEquals(0.5, new MetricsCalculator().precision(predicted, actual), 1e-9);
        assertEquals(0.5, new MetricsCalculator().recall(predicted, actual), 1e-9);
        assertEquals(0.5, new MetricsCalculator().f1Score(predicted, actual), 1e-9);
        assertEquals(0.5, new MetricsCalculator().accuracy(predicted, actual), 1e-9);
    }
    @Test public void perfectAndWrongPredictions() {
        int[] p = {1, 0, 1, 0};
        int[] correct = {1, 0, 1, 0};
        int[] wrong = {0, 1, 0, 1};
        assertEquals(1.0, new MetricsCalculator().f1Score(p, correct), 1e-9);
        assertEquals(1.0, new MetricsCalculator().accuracy(p, correct), 1e-9);
        assertEquals(0.0, new MetricsCalculator().f1Score(p, wrong), 1e-9);
        assertEquals(0.0, new MetricsCalculator().accuracy(p, wrong), 1e-9);
    }
    @Test public void emptyAndNoPositiveDenominatorsReturnZero() {
        assertEquals(0.0, new MetricsCalculator().accuracy(new int[0], new int[0]), 0.0);
        assertEquals(0.0, new MetricsCalculator().precision(new int[]{0, 0}, new int[]{0, 1}), 0.0);
        assertEquals(0.0, new MetricsCalculator().recall(new int[]{1, 0}, new int[]{0, 0}), 0.0);
        assertEquals(0.0, new MetricsCalculator().f1Score(new int[]{0}, new int[]{1}), 0.0);
    }
    @Test public void f1CallsUpdateThreeTimes() {
        MetricsCalculator m = new MetricsCalculator();
        m.f1Score(new int[]{1, 1, 0, 0}, new int[]{1, 0, 1, 0});
        assertEquals(3, m.truePositives);
        assertEquals(3, m.falsePositives);
        assertEquals(3, m.falseNegatives);
        assertEquals(3, m.trueNegatives);
    }
    @Test public void ignoresNonBinaryCombinations() {
        MetricsCalculator m = new MetricsCalculator();
        m.update(new int[]{2, -1, 1, 0, 1}, new int[]{2, 0, 2, -1, 1});
        assertEquals(1, m.truePositives);
        assertEquals(0, m.falsePositives);
        assertEquals(0, m.falseNegatives);
        assertEquals(0, m.trueNegatives);
    }

    @Test public void metricCallsAccumulate() { MetricsCalculator m = new MetricsCalculator(); int[] a={1,0},b={1,0}; m.precision(a,b); m.accuracy(a,b); assertEquals(2,m.truePositives); assertEquals(2,m.trueNegatives); }
    @Test public void previousUpdatesChangeLaterMetricResults() {
        MetricsCalculator m = new MetricsCalculator();
        assertEquals(1.0, m.precision(new int[]{1}, new int[]{1}), 1e-9);
        assertEquals(0.5, m.precision(new int[]{1}, new int[]{0}), 1e-9);
        assertEquals(2, m.truePositives + m.falsePositives);
        assertEquals(1, m.truePositives);
        assertEquals(1, m.falsePositives);
    }
}
// END GeneratedTest_CE053
