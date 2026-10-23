package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratedTest_HE003 {

@Test
public void testEmptyList() {
    List<Integer> operations = Collections.emptyList();
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(operations));
}

@Test
public void testNoBelowZero() {
    List<Integer> operations = Arrays.asList(1, 2, 3);
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(operations));
}

@Test
public void testImmediateBelowZero() {
    List<Integer> operations = Arrays.asList(-1, 5, 5);
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(operations));
}

@Test
public void testLaterBelowZero() {
    List<Integer> operations = Arrays.asList(1, 2, -4);
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(operations));
}

@Test
public void testReachesZeroButNotBelow() {
    List<Integer> operations = Arrays.asList(1, -1, 2, -2);
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(operations));
}
}