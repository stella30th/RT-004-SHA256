package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME025 {
    @Test
    public void testMonotonic() {
        assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{1, 2, 4, 20}));
        assertFalse(humaneval.correct.MONOTONIC.monotonic(new int[]{1, 20, 4, 10}));
        assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{4, 1, 0, -10}));
    }
}
