package humaneval.generated;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.assertArrayEquals;
public class GeneratedTest_ME001 {
@Test
public void testEmptyList() {
    Integer[] expected = new Integer[]{0, 1};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Collections.<Integer>emptyList());

    assertArrayEquals(expected, actual);
}

@Test
public void testSinglePositiveNumber() {
    Integer[] expected = new Integer[]{5, 5};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(5));

    assertArrayEquals(expected, actual);
}

@Test
public void testMultiplePositiveNumbers() {
    Integer[] expected = new Integer[]{10, 24};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, 2, 3, 4));

    assertArrayEquals(expected, actual);
}

@Test
public void testIncludesZero() {
    Integer[] expected = new Integer[]{6, 0};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, 2, 0, 3));

    assertArrayEquals(expected, actual);
}

@Test
public void testNegativeNumbers() {
    Integer[] expected = new Integer[]{-6, -6};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(-1, -2, -3));

    assertArrayEquals(expected, actual);
}

@Test
public void testEvenNumberOfNegativeNumbers() {
    Integer[] expected = new Integer[]{-10, 24};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(-1, -2, -3, -4));

    assertArrayEquals(expected, actual);
}

@Test
public void testMixedPositiveAndNegativeNumbers() {
    Integer[] expected = new Integer[]{2, -24};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, -2, 3, -4, 4));

    assertArrayEquals(expected, actual);
}

@Test
public void testSingleZero() {
    Integer[] expected = new Integer[]{0, 0};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(0));

    assertArrayEquals(expected, actual);
}

@Test
public void testSumIsZero() {
    Integer[] expected = new Integer[]{0, -1};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1, -1));

    assertArrayEquals(expected, actual);
}

@Test
public void testRepeatedValues() {
    Integer[] expected = new Integer[]{8, 16};
    Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(2, 2, 2, 2));

    assertArrayEquals(expected, actual);
}

}