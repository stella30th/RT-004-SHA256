package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratedTest_HE000 {

@Test
public void testHasCloseElementsTrue() {
    List<Double> numbers = Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0, 2.2);
    double threshold = 0.3;
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(numbers, threshold));
}

@Test
public void testHasCloseElementsFalse() {
    List<Double> numbers = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    double threshold = 0.5;
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(numbers, threshold));
}

@Test
public void testEmptyOrSingleElement() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(Collections.emptyList(), 0.5));
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(Arrays.asList(1.0), 0.5));
}

@Test
public void testBoundaryCondition() {
    List<Double> numbers = Arrays.asList(1.0, 2.0);
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(numbers, 1.0));
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(numbers, 1.0001));
}
}