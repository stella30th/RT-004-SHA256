package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME009 {
 @Test public void empty(){assertEquals(0,humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters(""));}
 @Test public void ignoresCase(){assertEquals(3,humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("xyzXYZ"));}
 @Test public void duplicates(){assertEquals(1,humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("AaAa"));}
 @Test public void ordinaryWord(){assertEquals(4,humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("Jerry"));}
 @Test public void punctuationDistinct(){assertEquals(3,humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("A a!"));}
}
