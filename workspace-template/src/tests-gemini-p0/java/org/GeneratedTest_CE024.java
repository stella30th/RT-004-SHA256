package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE024 {

@Test
public void testCount() {
    assertEquals(1, CombinationCalculator.count(5, 0));
    assertEquals(1, CombinationCalculator.count(5, 5));
    assertEquals(10, CombinationCalculator.count(5, 2));
    assertEquals(6, CombinationCalculator.count(4, 2));
}

@Test
public void testCountAll() {
    assertEquals(7.0, CombinationCalculator.countAll(3), 1e-6);
    assertEquals(15.0, CombinationCalculator.countAll(4), 1e-6);
    assertTrue(Double.isNaN(CombinationCalculator.countAll(-1)));
    assertTrue(Double.isNaN(CombinationCalculator.countAll(64)));
    assertEquals(Double.POSITIVE_INFINITY, CombinationCalculator.countAll(63), 1e-6);
}

@Test
public void testSelect() {
    CombinationCalculator calc = new CombinationCalculator(Arrays.asList("A", "B", "C"));
    List<List<String>> result = calc.select(2);

    assertEquals(3, result.size());
    assertEquals(Arrays.asList("A", "B"), result.get(0));
    assertEquals(Arrays.asList("A", "C"), result.get(1));
    assertEquals(Arrays.asList("B", "C"), result.get(2));
}

@Test
public void testSelectAll() {
    CombinationCalculator calc = new CombinationCalculator(Arrays.asList("X", "Y"));
    List<List<String>> result = calc.selectAll();

    // 1-element combos: [X], [Y]; 2-element combos: [X, Y]
    assertEquals(3, result.size());
    assertEquals(Arrays.asList("X"), result.get(0));
    assertEquals(Arrays.asList("Y"), result.get(1));
    assertEquals(Arrays.asList("X", "Y"), result.get(2));
}

}
