package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratedTest_ME001 {

@Test
public void testEmptyList() {
    List<Integer> input = Collections.emptyList();
    Integer[] result = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(Integer.valueOf(0), result[0]);
    assertEquals(Integer.valueOf(1), result[1]);
}

@Test
public void testSingleElement() {
    List<Integer> input = Arrays.asList(5);
    Integer[] result = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(Integer.valueOf(5), result[0]);
    assertEquals(Integer.valueOf(5), result[1]);
}

@Test
public void testMultipleElements() {
    List<Integer> input = Arrays.asList(1, 2, 3, 4);
    Integer[] result = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(Integer.valueOf(10), result[0]);
    assertEquals(Integer.valueOf(24), result[1]);
}

@Test
public void testNegativeNumbers() {
    List<Integer> input = Arrays.asList(-1, 2, -3);
    Integer[] result = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(Integer.valueOf(-2), result[0]);
    assertEquals(Integer.valueOf(6), result[1]);
}

@Test
public void testZeroInList() {
    List<Integer> input = Arrays.asList(0, 5, 10);
    Integer[] result = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertNotNull(result);
    assertEquals(2, result.length);
    assertEquals(Integer.valueOf(15), result[0]);
    assertEquals(Integer.valueOf(0), result[1]);
}
}