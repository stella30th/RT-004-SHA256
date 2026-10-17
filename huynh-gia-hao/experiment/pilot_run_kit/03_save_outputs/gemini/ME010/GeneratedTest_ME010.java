package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME010 {
    @Test
    public void testHowManyTimes() {
        assertEquals(0, humaneval.correct.HOW_MANY_TIMES.how_many_times("", "a"));
        assertEquals(3, humaneval.correct.HOW_MANY_TIMES.how_many_times("aaa", "a"));
        assertEquals(3, humaneval.correct.HOW_MANY_TIMES.how_many_times("aaaa", "aa"));
    }
}
