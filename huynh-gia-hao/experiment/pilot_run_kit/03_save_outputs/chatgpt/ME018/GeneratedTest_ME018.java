package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME018 {
 @Test public void alreadySorted(){assertEquals("zero one two",humaneval.correct.SORT_NUMBERS.sort_numbers("zero one two"));}
 @Test public void unsorted(){assertEquals("one three five",humaneval.correct.SORT_NUMBERS.sort_numbers("three one five"));}
 @Test public void allDigits(){assertEquals("zero one two three four five six seven eight nine",humaneval.correct.SORT_NUMBERS.sort_numbers("nine zero five three eight one seven two six four"));}
 @Test public void duplicates(){assertEquals("one one two three three",humaneval.correct.SORT_NUMBERS.sort_numbers("three one two three one"));}
 @Test public void singleton(){assertEquals("seven",humaneval.correct.SORT_NUMBERS.sort_numbers("seven"));}
}
