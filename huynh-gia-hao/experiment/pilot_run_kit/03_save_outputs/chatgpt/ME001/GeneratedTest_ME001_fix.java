package humaneval.generated;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;

public class GeneratedTest_ME001 {

    @Test
    public void testEmptyList() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Collections.<Integer>emptyList()
        );

        assertArrayEquals(new Integer[]{0, 1}, actual);
    }

    @Test
    public void testSinglePositiveNumber() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(5)
        );

        assertArrayEquals(new Integer[]{5, 5}, actual);
    }

    @Test
    public void testSingleNegativeNumber() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(-7)
        );

        assertArrayEquals(new Integer[]{-7, -7}, actual);
    }

    @Test
    public void testSingleZero() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(0)
        );

        assertArrayEquals(new Integer[]{0, 0}, actual);
    }

    @Test
    public void testMultiplePositiveNumbers() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(1, 2, 3, 4)
        );

        assertArrayEquals(new Integer[]{10, 24}, actual);
    }

    @Test
    public void testPositiveAndNegativeNumbers() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(2, -3, 4)
        );

        assertArrayEquals(new Integer[]{3, -24}, actual);
    }

    @Test
    public void testTwoNegativeNumbersProducePositiveProduct() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(-2, -3)
        );

        assertArrayEquals(new Integer[]{-5, 6}, actual);
    }

    @Test
    public void testOddNumberOfNegativeValues() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(-1, -2, -3)
        );

        assertArrayEquals(new Integer[]{-6, -6}, actual);
    }

    @Test
    public void testListContainingZero() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(5, 0, -2, 3)
        );

        assertArrayEquals(new Integer[]{6, 0}, actual);
    }

    @Test
    public void testDuplicateValues() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(3, 3, 3)
        );

        assertArrayEquals(new Integer[]{9, 27}, actual);
    }

    @Test
    public void testValuesWhoseSumIsZero() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(-4, 1, 3)
        );

        assertArrayEquals(new Integer[]{0, -12}, actual);
    }

    @Test
    public void testOnesPreserveProduct() {
        Integer[] actual = humaneval.correct.SUM_PRODUCT.sum_product(
                Arrays.asList(1, 1, 1, 1)
        );

        assertArrayEquals(new Integer[]{4, 1}, actual);
    }
}