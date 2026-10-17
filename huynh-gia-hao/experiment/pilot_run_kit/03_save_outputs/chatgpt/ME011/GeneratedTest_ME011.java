package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME011 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.INTERSPERSE.intersperse(Collections.<Integer>emptyList(),9));}
 @Test public void singleton(){assertEquals(Arrays.asList(5),humaneval.correct.INTERSPERSE.intersperse(Arrays.asList(5),0));}
 @Test public void several(){assertEquals(Arrays.asList(1,4,2,4,3),humaneval.correct.INTERSPERSE.intersperse(Arrays.asList(1,2,3),4));}
 @Test public void negativeDelimiter(){assertEquals(Arrays.asList(2,-1,2,-1,2),humaneval.correct.INTERSPERSE.intersperse(Arrays.asList(2,2,2),-1));}
 @Test public void delimiterEqualsElement(){assertEquals(Arrays.asList(7,7,7),humaneval.correct.INTERSPERSE.intersperse(Arrays.asList(7,7),7));}
}
