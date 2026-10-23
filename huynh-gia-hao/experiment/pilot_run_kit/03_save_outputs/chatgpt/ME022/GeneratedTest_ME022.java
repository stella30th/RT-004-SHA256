package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME022 {
 @Test public void emptyVacuouslyTrue(){assertTrue(humaneval.correct.BELOW_THRESHOLD.below_threshold(new int[]{},5));}
 @Test public void allBelow(){assertTrue(humaneval.correct.BELOW_THRESHOLD.below_threshold(new int[]{1,2,4,10},100));}
 @Test public void oneAbove(){assertFalse(humaneval.correct.BELOW_THRESHOLD.below_threshold(new int[]{1,20,4,10},5));}
 @Test public void equalityFails(){assertFalse(humaneval.correct.BELOW_THRESHOLD.below_threshold(new int[]{1,5,2},5));}
 @Test public void negatives(){assertTrue(humaneval.correct.BELOW_THRESHOLD.below_threshold(new int[]{-10,-2,-1},0));}
}
