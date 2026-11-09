package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME006 {
    @Test
    public void testMakePalindrome() {
        assertEquals("", humaneval.correct.MAKE_PALINDROME.make_palindrome(""));
        assertEquals("catac", humaneval.correct.MAKE_PALINDROME.make_palindrome("cat"));
        assertEquals("catafacatac", humaneval.correct.MAKE_PALINDROME.make_palindrome("catafa"));
    }
}
