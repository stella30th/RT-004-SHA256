package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME018 {
    @Test
    public void testSortNumbers() {
        assertEquals("zero one two three four five",
                humaneval.correct.SORT_NUMBERS.sort_numbers(
                        "three five zero one two four"));
    }
}
