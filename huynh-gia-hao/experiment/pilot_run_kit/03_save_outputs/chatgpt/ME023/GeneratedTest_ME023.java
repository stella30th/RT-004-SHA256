package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME023 {
 @Test public void identical(){assertTrue(humaneval.correct.SAME_CHARS.same_chars("abc","abc"));}
 @Test public void multiplicityIgnored(){assertTrue(humaneval.correct.SAME_CHARS.same_chars("abcd","dddddddabc"));}
 @Test public void orderIgnored(){assertTrue(humaneval.correct.SAME_CHARS.same_chars("abc","cba"));}
 @Test public void extraCharacterFails(){assertFalse(humaneval.correct.SAME_CHARS.same_chars("abcd","abcde"));}
 @Test public void emptyCases(){assertTrue(humaneval.correct.SAME_CHARS.same_chars("",""));assertFalse(humaneval.correct.SAME_CHARS.same_chars("","a"));}
}
