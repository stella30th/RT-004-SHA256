package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME030 {
    @Test
    public void testTriplesSumToZero() {
        assertFalse(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(
                new int[]{1, 3, 5, 0}));
        assertTrue(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(
                new int[]{1, 3, -2, 1}));
    }
}
