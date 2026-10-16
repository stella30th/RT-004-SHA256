package humaneval.generated;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;
public class GeneratedTest_HE000 {
@Test
public void testCloseElementsExist() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(1.0, 2.0, 3.0, 1.4),
            0.5
    ));
}

@Test
public void testNoCloseElements() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(1.0, 2.0, 3.0),
            0.5
    ));
}

@Test
public void testDistanceExactlyThresholdIsFalse() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(1.0, 1.5),
            0.5
    ));
}

@Test
public void testDistanceJustBelowThreshold() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(1.0, 1.49),
            0.5
    ));
}

@Test
public void testDuplicateElements() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(2.0, 5.0, 2.0),
            0.01
    ));
}

@Test
public void testNegativeNumbers() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(-2.0, -1.6, 4.0),
            0.5
    ));
}

@Test
public void testNegativeNumbersNotClose() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(-5.0, -3.0, -1.0),
            1.0
    ));
}

@Test
public void testSingleElement() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Collections.singletonList(1.0),
            100.0
    ));
}

@Test
public void testEmptyList() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Collections.<Double>emptyList(),
            1.0
    ));
}

@Test
public void testZeroThresholdWithDuplicates() {
    assertFalse(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(1.0, 1.0),
            0.0
    ));
}

@Test
public void testLargeThreshold() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(100.0, -100.0),
            201.0
    ));
}

@Test
public void testClosePairNotAdjacent() {
    assertTrue(humaneval.correct.HAS_CLOSE_ELEMENTS.has_close_elements(
            Arrays.asList(10.0, 100.0, 200.0, 10.01),
            0.02
    ));
}

}