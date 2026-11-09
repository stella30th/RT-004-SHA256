package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME003 {
    @Test
    public void testLargestDivisor() {
        assertEquals(1, humaneval.correct.LARGEST_DIVISOR.largest_divisor(3));
        assertEquals(2, humaneval.correct.LARGEST_DIVISOR.largest_divisor(4));
        assertEquals(5, humaneval.correct.LARGEST_DIVISOR.largest_divisor(10));
        assertEquals(5, humaneval.correct.LARGEST_DIVISOR.largest_divisor(15));
    }
}
