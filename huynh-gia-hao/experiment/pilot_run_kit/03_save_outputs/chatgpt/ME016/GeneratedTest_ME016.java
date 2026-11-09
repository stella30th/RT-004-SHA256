package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME016 {
 @Test public void sampleShape(){assertEquals(Arrays.asList(2,3,1,3),humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens("(()()) ((())) () ((())()())"));}
 @Test public void singlePair(){assertEquals(Arrays.asList(1),humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens("()"));}
 @Test public void sequentialPairsDepthOne(){assertEquals(Arrays.asList(1),humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens("()()()"));}
 @Test public void deepNesting(){assertEquals(Arrays.asList(4),humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens("(((())))"));}
 @Test public void multipleGroups(){assertEquals(Arrays.asList(2,1,3),humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens("(()) () ((()))"));}
}
