package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME013 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.ROLLING_MAX.rolling_max(Collections.<Integer>emptyList()));}
 @Test public void increasing(){assertEquals(Arrays.asList(1,2,3,4),humaneval.correct.ROLLING_MAX.rolling_max(Arrays.asList(1,2,3,4)));}
 @Test public void decreasing(){assertEquals(Arrays.asList(4,4,4,4),humaneval.correct.ROLLING_MAX.rolling_max(Arrays.asList(4,3,2,1)));}
 @Test public void mixed(){assertEquals(Arrays.asList(1,2,3,3,3,4,4),humaneval.correct.ROLLING_MAX.rolling_max(Arrays.asList(1,2,3,2,3,4,2)));}
 @Test public void negatives(){assertEquals(Arrays.asList(-5,-2,-2,-1),humaneval.correct.ROLLING_MAX.rolling_max(Arrays.asList(-5,-2,-8,-1)));}
}
