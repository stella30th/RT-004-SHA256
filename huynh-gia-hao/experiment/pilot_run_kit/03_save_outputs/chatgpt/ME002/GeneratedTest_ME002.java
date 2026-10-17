package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME002 {
 @Test public void coprime(){assertEquals(1,humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(17,13));}
 @Test public void commonFactor(){assertEquals(6,humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(54,24));}
 @Test public void oneDividesOther(){assertEquals(12,humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(36,12));}
 @Test public void equalInputs(){assertEquals(25,humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(25,25));}
 @Test public void secondLarger(){assertEquals(7,humaneval.correct.GREATEST_COMMON_DIVISOR.greatest_common_divisor(21,35));}
}
