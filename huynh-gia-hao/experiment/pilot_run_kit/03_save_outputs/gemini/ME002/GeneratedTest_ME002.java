package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME002 {
    @Test
    public void testGreatestCommonDivisor() {
        assertEquals(1, humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(3, 7));
        assertEquals(5, humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(10, 15));
        assertEquals(7, humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(49, 14));
    }
}
