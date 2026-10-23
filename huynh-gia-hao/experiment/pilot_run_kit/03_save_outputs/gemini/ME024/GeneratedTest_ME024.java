package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME024 {
    @Test
    public void testCorrectBracketing() {
        assertFalse(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("<"));
        assertTrue(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("<>"));
        assertFalse(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("><>"));
    }
}
