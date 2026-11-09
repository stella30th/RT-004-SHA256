package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME006 {

@Test
public void testMakePalindromeEmpty() {
    assertEquals("", humaneval.correct.MAKE_PALINDROME.make_palindrome(""));
}

@Test
public void testMakePalindromeAlreadyPalindrome() {
    assertEquals("racecar", humaneval.correct.MAKE_PALINDROME.make_palindrome("racecar"));
    assertEquals("a", humaneval.correct.MAKE_PALINDROME.make_palindrome("a"));
}

@Test
public void testMakePalindromeStandard() {
    assertEquals("catac", humaneval.correct.MAKE_PALINDROME.make_palindrome("cat"));
    assertEquals("catac", humaneval.correct.MAKE_PALINDROME.make_palindrome("cata"));
}

@Test
public void testIsPalindrome() {
    assertTrue(humaneval.correct.MAKE_PALINDROME.is_palindrome(""));
    assertTrue(humaneval.correct.MAKE_PALINDROME.is_palindrome("a"));
    assertTrue(humaneval.correct.MAKE_PALINDROME.is_palindrome("aba"));
    assertTrue(humaneval.correct.MAKE_PALINDROME.is_palindrome("abba"));
    assertFalse(humaneval.correct.MAKE_PALINDROME.is_palindrome("abc"));
    assertFalse(humaneval.correct.MAKE_PALINDROME.is_palindrome("ab"));
}
}