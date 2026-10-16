package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class GeneratedTest_HE004 {

@Test
public void testMeanAbsoluteDeviationBasic() {
    List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0);
    double result = humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers);
    assertEquals(1.0, result, 1e-6);
}

@Test
public void testMeanAbsoluteDeviationIdentical() {
    List<Double> numbers = Arrays.asList(5.0, 5.0, 5.0, 5.0);
    double result = humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers);
    assertEquals(0.0, result, 1e-6);
}

@Test
public void testMeanAbsoluteDeviationNegative() {
    List<Double> numbers = Arrays.asList(-1.0, 0.0, 1.0);
    double result = humaneval.correct.MEAN_ABSOLUTE_DEVIATION.mean_absolute_deviation(numbers);
    assertEquals(2.0 / 3.0, result, 1e-6);
}
}