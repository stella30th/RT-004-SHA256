package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME015 {
    @Test
    public void testFilterIntegers() {
        assertEquals(Arrays.asList(5, 1, 3),
                humaneval.correct.FILTER_INTEGERS.filter_integers(
                        Arrays.asList("a", 3.14, 5, 1, "b", 3)));
    }
}
