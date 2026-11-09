package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME026 {
 @Test public void ordinary(){assertArrayEquals(new double[]{2.0,2.2},humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(new double[]{1,2,3,4,5,2.2}),1e-9);}
 @Test public void duplicatesAreClosest(){assertArrayEquals(new double[]{2.0,2.0},humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(new double[]{1,2,3,2}),1e-9);}
 @Test public void twoElementsSorted(){assertArrayEquals(new double[]{-3.0,5.0},humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(new double[]{5,-3}),1e-9);}
 @Test public void negatives(){assertArrayEquals(new double[]{-2.1,-2.0},humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(new double[]{-10,-2,-2.1,7}),1e-9);}
 @Test public void closestLate(){assertArrayEquals(new double[]{9.0,9.01},humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(new double[]{0,5,20,9,9.01}),1e-9);}
}
