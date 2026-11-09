package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME008 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.ALL_PREFIXES.all_prefixes(""));}
 @Test public void singleton(){assertEquals(Arrays.asList("x"),humaneval.correct.ALL_PREFIXES.all_prefixes("x"));}
 @Test public void threeChars(){assertEquals(Arrays.asList("a","ab","abc"),humaneval.correct.ALL_PREFIXES.all_prefixes("abc"));}
 @Test public void repeatedChars(){assertEquals(Arrays.asList("a","aa","aaa"),humaneval.correct.ALL_PREFIXES.all_prefixes("aaa"));}
 @Test public void includesSpacesAsCharacters(){assertEquals(Arrays.asList("a","a ","a b"),humaneval.correct.ALL_PREFIXES.all_prefixes("a b"));}
}
