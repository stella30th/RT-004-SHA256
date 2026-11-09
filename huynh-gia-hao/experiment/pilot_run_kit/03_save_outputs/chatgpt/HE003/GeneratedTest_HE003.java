package humaneval.generated;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class GeneratedTest_HE003 {
@Test
public void testEmptyOperations() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Collections.<Integer>emptyList()
    ));
}

@Test
public void testSinglePositiveOperation() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(5)
    ));
}

@Test
public void testSingleZeroOperation() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(0)
    ));
}

@Test
public void testSingleNegativeOperation() {
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(-1)
    ));
}

@Test
public void testBalanceNeverBelowZero() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(10, -5, 3, -8, 4)
    ));
}

@Test
public void testBalanceBecomesExactlyZero() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(10, -10)
    ));
}

@Test
public void testBalanceFallsBelowZeroAfterSeveralOperations() {
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(10, -5, -6)
    ));
}

@Test
public void testBalanceFallsBelowZeroThenWouldRecover() {
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(5, -10, 20)
    ));
}

@Test
public void testMultipleZeros() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(0, 0, 0, 0)
    ));
}

@Test
public void testAlternatingOperationsWithoutNegativeBalance() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(3, -2, 4, -3, -2)
    ));
}

@Test
public void testNegativeAtFinalOperation() {
    assertTrue(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(4, 3, -2, -6)
    ));
}

@Test
public void testLargePositiveAndNegativeOperations() {
    assertFalse(humaneval.correct.BELOW_ZERO.below_zero(
            Arrays.asList(1000000, -999999, 5, -6)
    ));
}

}