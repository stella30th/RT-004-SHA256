package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE004 {

@Test
public void testFactorial() {
    assertEquals(1, ArrangementCalculator.factorial(0));
    assertEquals(1, ArrangementCalculator.factorial(1));
    assertEquals(2, ArrangementCalculator.factorial(2));
    assertEquals(6, ArrangementCalculator.factorial(3));
    assertEquals(24, ArrangementCalculator.factorial(4));
}

@Test
public void testCountAndCountAll() {
    assertEquals(24, ArrangementCalculator.count(4, null));
    assertEquals(24, ArrangementCalculator.count(4, 4));
    assertEquals(12, ArrangementCalculator.count(4, 2));
    assertEquals(4, ArrangementCalculator.count(4, 1));

    int expectedCountAll = ArrangementCalculator.count(3, 1)
            + ArrangementCalculator.count(3, 2)
            + ArrangementCalculator.count(3, 3);
    assertEquals(3 + 6 + 6, expectedCountAll);
    assertEquals(expectedCountAll, ArrangementCalculator.countAll(3));
}

@Test
public void testSelectWithSpecificM() {
    List<Object> datas = Arrays.asList("A", "B", "C");
    ArrangementCalculator calculator = new ArrangementCalculator(datas);

    List<List<Object>> permutations = calculator.select(2);
    assertEquals(6, permutations.size());

    List<Object> first = permutations.get(0);
    assertEquals(2, first.size());
}

@Test
public void testSelectAllAndDefaultM() {
    List<Object> datas = Arrays.asList(1, 2);
    ArrangementCalculator calculator = new ArrangementCalculator(datas);

    List<List<Object>> selectNull = calculator.select(null);
    assertEquals(2, selectNull.size());

    List<List<Object>> allPermutations = calculator.selectAll();
    assertEquals(4, allPermutations.size());
}

}
