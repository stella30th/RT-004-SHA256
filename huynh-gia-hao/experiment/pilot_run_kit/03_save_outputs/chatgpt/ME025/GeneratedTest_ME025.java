package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME025 {
 @Test public void empty(){assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{}));}
 @Test public void singleton(){assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{5}));}
 @Test public void increasing(){assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{1,2,4,20}));}
 @Test public void decreasing(){assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{4,1,0,-10}));}
 @Test public void duplicatesAllowed(){assertTrue(humaneval.correct.MONOTONIC.monotonic(new int[]{1,1,2,2}));}
 @Test public void directionChangeFails(){assertFalse(humaneval.correct.MONOTONIC.monotonic(new int[]{1,20,4,10}));}
}
