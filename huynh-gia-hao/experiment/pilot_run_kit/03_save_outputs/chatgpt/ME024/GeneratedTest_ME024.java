package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME024 {
 @Test public void emptyBalanced(){assertTrue(humaneval.correct.CORRECT_BRACKETING.correct_bracketing(""));}
 @Test public void simplePair(){assertTrue(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("<>"));}
 @Test public void nestedAndSequential(){assertTrue(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("<<><>>"));}
 @Test public void prematureClose(){assertFalse(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("><>"));}
 @Test public void unclosedOpen(){assertFalse(humaneval.correct.CORRECT_BRACKETING.correct_bracketing("<<>"));}
}
