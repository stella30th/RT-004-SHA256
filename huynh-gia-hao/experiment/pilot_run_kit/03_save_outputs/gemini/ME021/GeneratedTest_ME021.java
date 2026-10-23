package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME021 {
    @Test
    public void testIsPalindrome() {
        assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome(""));
        assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome("aba"));
        assertFalse(humaneval.correct.IS_PALINDROME.is_palindrome("zbcd"));
    }
}
