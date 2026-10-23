package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME004 {
    @Test
    public void testIsPrime() {
        assertFalse(humaneval.correct.IS_PRIME.is_prime(1));
        assertTrue(humaneval.correct.IS_PRIME.is_prime(2));
        assertTrue(humaneval.correct.IS_PRIME.is_prime(3));
        assertFalse(humaneval.correct.IS_PRIME.is_prime(4));
        assertTrue(humaneval.correct.IS_PRIME.is_prime(11));
        assertFalse(humaneval.correct.IS_PRIME.is_prime(15));
    }
}
