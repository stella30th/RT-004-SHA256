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
    Integer[] expected = new Integer[]{0, 1};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertArrayEquals(expected, actual);
}

@Test
public void testSingletonList() {
    List<Integer> input = Arrays.asList(5);
    Integer[] expected = new Integer[]{5, 5};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertArrayEquals(expected, actual);
}

@Test
public void testMultiplePositiveIntegers() {
    List<Integer> input = Arrays.asList(1, 2, 3, 4);
    Integer[] expected = new Integer[]{10, 24};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertArrayEquals(expected, actual);
}

@Test
public void testListWithZero() {
    List<Integer> input = Arrays.asList(1, 2, 0, 4);
    Integer[] expected = new Integer[]{7, 0};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertArrayEquals(expected, actual);
}

@Test
public void testListWithNegativeIntegers() {
    List<Integer> input = Arrays.asList(-1, 2, -3);
    Integer[] expected = new Integer[]{-2, 6};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(input);
    assertArrayEquals(expected, actual);
}
}