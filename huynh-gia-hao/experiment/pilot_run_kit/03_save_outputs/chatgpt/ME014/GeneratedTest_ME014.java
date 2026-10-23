package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME014 {
 @Test public void emptyReturnsNull(){assertNull(humaneval.correct.LONGEST.longest(Collections.<String>emptyList()));}
 @Test public void firstTieWins(){assertEquals("aa",humaneval.correct.LONGEST.longest(Arrays.asList("aa","bb","c")));}
 @Test public void longestAtEnd(){assertEquals("abcd",humaneval.correct.LONGEST.longest(Arrays.asList("a","abc","abcd")));}
 @Test public void emptyStrings(){assertEquals("",humaneval.correct.LONGEST.longest(Arrays.asList("","")));}
 @Test public void singleton(){assertEquals("only",humaneval.correct.LONGEST.longest(Arrays.asList("only")));}
}
