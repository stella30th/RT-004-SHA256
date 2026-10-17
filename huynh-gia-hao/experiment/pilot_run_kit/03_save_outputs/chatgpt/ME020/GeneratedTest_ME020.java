package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME020 {
 @Test public void ordinary(){assertEquals(8,humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution("5 apples and 6 oranges",19));}
 @Test public void includesZero(){assertEquals(2,humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution("0 apples and 1 oranges",3));}
 @Test public void largeTotal(){assertEquals(95,humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution("2 apples and 3 oranges",100));}
 @Test public void largeAppleCount(){assertEquals(19,humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution("100 apples and 1 oranges",120));}
 @Test public void noNamedCounts(){assertEquals(10,humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution("apples and oranges",10));}
}
