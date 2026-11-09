package humaneval.generated;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class GeneratedTest_HE004 {
private static final double DELTA = 1e-9;

@Test
public void testSingleElement() {
    List<Double> numbers = Collections.singletonList(5.0);

    assertEquals(
            0.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testSimpleIncreasingSequence() {
    List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0);

    assertEquals(
            2.0 / 3.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testAllEqualValues() {
    List<Double> numbers = Arrays.asList(4.0, 4.0, 4.0, 4.0);

    assertEquals(
            0.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testPositiveAndNegativeValues() {
    List<Double> numbers = Arrays.asList(-2.0, 0.0, 2.0);

    assertEquals(
            4.0 / 3.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testNegativeValuesOnly() {
    List<Double> numbers = Arrays.asList(-1.0, -2.0, -3.0, -4.0);

    assertEquals(
            1.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testDecimalValues() {
    List<Double> numbers = Arrays.asList(1.5, 2.5, 3.5, 4.5);

    assertEquals(
            1.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testTwoValues() {
    List<Double> numbers = Arrays.asList(0.0, 10.0);

    assertEquals(
            5.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testValuesWithZero() {
    List<Double> numbers = Arrays.asList(0.0, 0.0, 0.0, 4.0);

    assertEquals(
            1.5,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testSymmetricValues() {
    List<Double> numbers = Arrays.asList(-10.0, -5.0, 0.0, 5.0, 10.0);

    assertEquals(
            6.0,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

@Test
public void testMixedFractionalValues() {
    List<Double> numbers = Arrays.asList(1.0, 1.0, 2.0, 2.0);

    assertEquals(
            0.5,
            humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers),
            DELTA
    );
}

}