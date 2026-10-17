package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME030 {
 @Test public void tooShort(){assertFalse(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{1,-1}));}
 @Test public void noTriple(){assertFalse(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{1,3,5,0}));}
 @Test public void tripleExists(){assertTrue(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{1,3,-2,1}));}
 @Test public void tripleLate(){assertTrue(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{2,4,-5,3,9,7}));}
 @Test public void threeZerosNeedDistinctPositions(){assertTrue(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{0,0,0}));}
 @Test public void twoZerosInsufficient(){assertFalse(humaneval.correct.TRIPLES_SUM_TO_ZERO.triples_sum_to_zero(new int[]{0,0,1}));}
}
