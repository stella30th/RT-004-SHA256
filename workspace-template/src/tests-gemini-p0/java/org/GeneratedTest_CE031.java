package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE031 {

private static final double DELTA = 1e-4;

@Test
public void testBasicStatistics() {
    DataStatistics2 stats = new DataStatistics2(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals(15.0, stats.getSum(), DELTA);
    assertEquals(1.0, stats.getMin(), DELTA);
    assertEquals(5.0, stats.getMax(), DELTA);
    assertEquals(1.0, stats.getCorrelation(), DELTA);
}

@Test
public void testVarianceAndStdDeviation() {
    // Data: 1, 2, 3, 4, 5. Mean = 3.0.
    // Squared diffs: 4, 1, 0, 1, 4. Sum = 10. Avg = 2.0.
    DataStatistics2 stats = new DataStatistics2(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals(2.0, stats.getVariance(), DELTA);
    assertEquals(Math.sqrt(2.0), stats.getStdDeviation(), DELTA);
}

@Test
public void testSingleElementData() {
    DataStatistics2 stats = new DataStatistics2(Collections.singletonList(42));

    assertEquals(42.0, stats.getSum(), DELTA);
    assertEquals(42.0, stats.getMin(), DELTA);
    assertEquals(42.0, stats.getMax(), DELTA);
    assertEquals(0.0, stats.getVariance(), DELTA);
    assertEquals(0.0, stats.getStdDeviation(), DELTA);
}

@Test
public void testEmptyDataHandlesGracefully() {
    DataStatistics2 stats = new DataStatistics2(Collections.emptyList());

    assertEquals(0.0, stats.getSum(), DELTA);
    assertTrue(Double.isNaN(stats.getMin()));
    assertTrue(Double.isNaN(stats.getMax()));
    assertTrue(Double.isNaN(stats.getVariance()));
}

}
