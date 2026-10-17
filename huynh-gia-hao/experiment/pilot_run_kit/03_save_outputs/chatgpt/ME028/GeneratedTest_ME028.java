package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME028 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.SORT_THIRD.sort_third(Collections.<Integer>emptyList()));}
 @Test public void shorterThanFour(){assertEquals(Arrays.asList(3,2,1),humaneval.correct.SORT_THIRD.sort_third(Arrays.asList(3,2,1)));}
 @Test public void sampleLike(){assertEquals(Arrays.asList(2,6,3,4,8,9,5),humaneval.correct.SORT_THIRD.sort_third(Arrays.asList(5,6,3,4,8,9,2)));}
 @Test public void onlyThirdIndicesMove(){assertEquals(Arrays.asList(1,9,8,4,7,6,10,5,3,12),humaneval.correct.SORT_THIRD.sort_third(Arrays.asList(10,9,8,4,7,6,1,5,3,12)));}
 @Test public void duplicates(){assertEquals(Arrays.asList(1,8,7,2,5,4,2),humaneval.correct.SORT_THIRD.sort_third(Arrays.asList(2,8,7,1,5,4,2)));}
}
