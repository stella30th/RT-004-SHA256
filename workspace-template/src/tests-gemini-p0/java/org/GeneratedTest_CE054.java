package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE054 {

private static final double DELTA = 1e-4;

@Test
public void testMrrSingleTuple() {
    // First relevant item at index 0 -> MRR = 1.0 / (0 + 1) = 1.0
    MetricsCalculator2.Tuple t1 = new MetricsCalculator2.Tuple(Arrays.asList(1, 0, 0), 1);
    assertEquals(1.0, MetricsCalculator2.mrr(t1), DELTA);

    // First relevant item at index 1 -> MRR = 1.0 / (1 + 1) = 0.5
    MetricsCalculator2.Tuple t2 = new MetricsCalculator2.Tuple(Arrays.asList(0, 1, 0), 1);
    assertEquals(0.5, MetricsCalculator2.mrr(t2), DELTA);

    // No relevant item
    MetricsCalculator2.Tuple t3 = new MetricsCalculator2.Tuple(Arrays.asList(0, 0, 0), 1);
    assertEquals(0.0, MetricsCalculator2.mrr(t3), DELTA);
}

@Test
public void testMapSingleTuple() {
    // Items at rank 1 and 3: AP = (1/1 + 2/3) / 2 = 1.6666... / 2 = 0.8333...
    MetricsCalculator2.Tuple t = new MetricsCalculator2.Tuple(Arrays.asList(1, 0, 1), 2);
    assertEquals((1.0 + 2.0 / 3.0) / 2.0, MetricsCalculator2.map(t), DELTA);

    MetricsCalculator2.Tuple zeroTuple = new MetricsCalculator2.Tuple(Collections.emptyList(), 0);
    assertEquals(0.0, MetricsCalculator2.map(zeroTuple), DELTA);
}

@Test
public void testMrrAndMapListTuples() {
    MetricsCalculator2.Tuple t1 = new MetricsCalculator2.Tuple(Arrays.asList(1, 0), 1); // MRR=1.0, MAP=1.0
    MetricsCalculator2.Tuple t2 = new MetricsCalculator2.Tuple(Arrays.asList(0, 1), 1); // MRR=0.5, MAP=0.5
    List<MetricsCalculator2.Tuple> list = Arrays.asList(t1, t2);

    assertEquals(0.75, MetricsCalculator2.mrr(list), DELTA);
    assertEquals(0.75, MetricsCalculator2.map(list), DELTA);
}

@Test(expected = IllegalArgumentException.class)
public void testInvalidInputThrowsException() {
    MetricsCalculator2.mrr("invalid_input_type");
}

}
