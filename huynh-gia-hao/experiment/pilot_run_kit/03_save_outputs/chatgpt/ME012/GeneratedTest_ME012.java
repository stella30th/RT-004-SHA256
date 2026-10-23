package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME012 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Collections.<String>emptyList(),"a"));}
 @Test public void ordinary(){assertEquals(Arrays.asList("abc","bacd","array"),humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Arrays.asList("abc","bacd","cde","array"),"a"));}
 @Test public void preservesOrderAndDuplicates(){assertEquals(Arrays.asList("aba","aba"),humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Arrays.asList("aba","x","aba"),"ab"));}
 @Test public void caseSensitive(){assertEquals(Arrays.asList("Apple"),humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Arrays.asList("Apple","apple"),"A"));}
 @Test public void emptySubstringMatchesAll(){assertEquals(Arrays.asList("a","","b"),humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Arrays.asList("a","","b"),""));}
}
