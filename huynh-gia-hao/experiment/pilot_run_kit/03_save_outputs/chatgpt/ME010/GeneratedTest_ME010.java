package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME010 {
 @Test public void emptySource(){assertEquals(0,humaneval.correct.HOW_MANY_TIMES.how_many_times("","a"));}
 @Test public void noMatch(){assertEquals(0,humaneval.correct.HOW_MANY_TIMES.how_many_times("abcdef","zz"));}
 @Test public void overlapping(){assertEquals(3,humaneval.correct.HOW_MANY_TIMES.how_many_times("aaaa","aa"));}
 @Test public void singleCharacter(){assertEquals(3,humaneval.correct.HOW_MANY_TIMES.how_many_times("banana","a"));}
 @Test public void matchAtBothEnds(){assertEquals(2,humaneval.correct.HOW_MANY_TIMES.how_many_times("abcabc","abc"));}
}
