package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE030 {

private static final double DELTA = 1e-2;

@Test
public void testMean() {
    DataStatistics stats = new DataStatistics();
    int[] data1 = {1, 2, 3, 4, 5};
    assertEquals(3.0, stats.mean(data1), DELTA);

    int[] data2 = {1, 2, 4};
    // 7 / 3 = 2.3333... rounded to 2.33
    assertEquals(2.33, stats.mean(data2), DELTA);
}

@Test
public void testMedianOddAndEven() {
    DataStatistics stats = new DataStatistics();

    int[] oddData = {5, 1, 3}; // sorted: 1, 3, 5 -> 3
    assertEquals(3.0, stats.median(oddData), DELTA);

    int[] evenData = {1, 2, 3, 4}; // (2 + 3) / 2.0 = 2.5
    assertEquals(2.5, stats.median(evenData), DELTA);

    int[] unsortedEven = {10, 2, 4, 8}; // sorted: 2, 4, 8, 10 -> (4 + 8) / 2 = 6.0
    assertEquals(6.0, stats.median(unsortedEven), DELTA);
}

@Test
public void testModeSingleMode() {
    DataStatistics stats = new DataStatistics();
    int[] data = {1, 2, 2, 3, 4};
    List<Integer> mode = stats.mode(data);

    assertEquals(1, mode.size());
    assertEquals(Integer.valueOf(2), mode.get(0));
}

@Test
public void testModeMultipleModes() {
    DataStatistics stats = new DataStatistics();
    int[] data = {1, 1, 2, 2, 3};
    List<Integer> mode = stats.mode(data);

    assertEquals(Arrays.asList(1, 2), mode);
}

}
