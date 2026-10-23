package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME021 {
 @Test public void empty(){assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome(""));}
 @Test public void singleton(){assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome("x"));}
 @Test public void oddPalindrome(){assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome("racecar"));}
 @Test public void evenPalindrome(){assertTrue(humaneval.correct.IS_PALINDROME.is_palindrome("abba"));}
 @Test public void notPalindrome(){assertFalse(humaneval.correct.IS_PALINDROME.is_palindrome("abca"));}
}
