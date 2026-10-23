package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME019 {
 @Test public void binary(){assertEquals("1000",humaneval.correct.CHANGE_BASE.change_base(8,2));}
 @Test public void ternary(){assertEquals("22",humaneval.correct.CHANGE_BASE.change_base(8,3));}
 @Test public void baseSeven(){assertEquals("20",humaneval.correct.CHANGE_BASE.change_base(14,7));}
 @Test public void singleDigit(){assertEquals("5",humaneval.correct.CHANGE_BASE.change_base(5,8));}
 @Test public void repeatedRemainders(){assertEquals("1111",humaneval.correct.CHANGE_BASE.change_base(15,2));}
}
