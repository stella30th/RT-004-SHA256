package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class GeneratedTest_ME014 {
    @Test
    public void testLongest() {
        assertNull(humaneval.correct.LONGEST.longest(Collections.emptyList()));
        assertEquals("zzzz",
                humaneval.correct.LONGEST.longest(Arrays.asList("a", "bb", "ccc", "zzzz")));
    }
}
