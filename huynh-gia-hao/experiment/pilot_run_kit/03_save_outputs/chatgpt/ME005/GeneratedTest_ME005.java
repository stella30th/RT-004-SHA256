package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME005 {
 @Test public void integerDimensions(){assertEquals(7.5,humaneval.correct.TRIANGLE_AREA.triangle_area(5,3),1e-9);}
 @Test public void fractionalDimensions(){assertEquals(1.5,humaneval.correct.TRIANGLE_AREA.triangle_area(2.5,1.2),1e-9);}
 @Test public void zeroBase(){assertEquals(0.0,humaneval.correct.TRIANGLE_AREA.triangle_area(0,8),1e-9);}
 @Test public void zeroHeight(){assertEquals(0.0,humaneval.correct.TRIANGLE_AREA.triangle_area(8,0),1e-9);}
 @Test public void smallValues(){assertEquals(0.125,humaneval.correct.TRIANGLE_AREA.triangle_area(0.5,0.5),1e-9);}
}
