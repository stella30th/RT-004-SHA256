package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME001 {
 @Test public void emptyIdentity(){assertArrayEquals(new Integer[]{0,1}, humaneval.correct.SUM_PRODUCT.sum_product(Collections.<Integer>emptyList()));}
 @Test public void positives(){assertArrayEquals(new Integer[]{10,24}, humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(1,2,3,4)));}
 @Test public void zeroKillsProduct(){assertArrayEquals(new Integer[]{7,0}, humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(2,0,5)));}
 @Test public void negatives(){assertArrayEquals(new Integer[]{-2,6}, humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(-1,-2,1)));}
 @Test public void singleton(){assertArrayEquals(new Integer[]{-7,-7}, humaneval.correct.SUM_PRODUCT.sum_product(Arrays.asList(-7)));}
}
