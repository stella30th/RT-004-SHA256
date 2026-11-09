package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME023 {
    @Test
    public void testSameChars() {
        assertTrue(humaneval.correct.SAME_CHARS.same_chars(
                "eabcdzzzz", "dddzzzzzzzddeddabc"));
        assertFalse(humaneval.correct.SAME_CHARS.same_chars(
                "eabcd", "dddddddabc"));
    }
}
