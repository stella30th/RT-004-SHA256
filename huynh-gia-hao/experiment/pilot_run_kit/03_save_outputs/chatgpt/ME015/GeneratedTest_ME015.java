package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME015 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.FILTER_INTEGERS.filter_integers(Collections.<Object>emptyList()));}
 @Test public void mixedTypes(){assertEquals(Arrays.asList(5),humaneval.correct.FILTER_INTEGERS.filter_integers(Arrays.<Object>asList("a",3.14,5)));}
 @Test public void preservesIntegers(){assertEquals(Arrays.asList(1,2,3),humaneval.correct.FILTER_INTEGERS.filter_integers(Arrays.<Object>asList(1,2,3,"abc",new HashMap<Object,Object>(),new ArrayList<Object>())));}
 @Test public void negativesAndZero(){assertEquals(Arrays.asList(-2,0,7),humaneval.correct.FILTER_INTEGERS.filter_integers(Arrays.<Object>asList(-2,"0",0,2.0,7)));}
 @Test public void duplicatesPreserved(){assertEquals(Arrays.asList(4,4),humaneval.correct.FILTER_INTEGERS.filter_integers(Arrays.<Object>asList(4,"x",4)));}
}
