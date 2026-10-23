package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME006 {
 @Test public void empty(){assertEquals("",humaneval.correct.MAKE_PALINDROME.make_palindrome(""));}
 @Test public void alreadyPalindrome(){assertEquals("racecar",humaneval.correct.MAKE_PALINDROME.make_palindrome("racecar"));}
 @Test public void simpleWord(){assertEquals("catac",humaneval.correct.MAKE_PALINDROME.make_palindrome("cat"));}
 @Test public void palindromicSuffix(){assertEquals("catac",humaneval.correct.MAKE_PALINDROME.make_palindrome("cata"));}
 @Test public void repeatedSuffix(){assertEquals("abccba",humaneval.correct.MAKE_PALINDROME.make_palindrome("abcc"));}
}
