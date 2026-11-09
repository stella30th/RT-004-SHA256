package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME022 {
    @Test
    public void testBelowThreshold() {
        assertTrue(humaneval.correct.BELOW_THRESHOLD.below_threshold(
                new int[]{1, 2, 4, 10}, 100));
        assertFalse(humaneval.correct.BELOW_THRESHOLD.below_threshold(
                new int[]{1, 20, 4, 10}, 5));
    }
}
