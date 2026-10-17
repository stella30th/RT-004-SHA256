package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class GeneratedTest_ME011 {
    @Test
    public void testIntersperse() {
        assertEquals(Collections.emptyList(),
                humaneval.correct.INTERSPERSE.intersperse(Collections.emptyList(), 4));
        assertEquals(Arrays.asList(1, 4, 2, 4, 3),
                humaneval.correct.INTERSPERSE.intersperse(Arrays.asList(1, 2, 3), 4));
    }
}
