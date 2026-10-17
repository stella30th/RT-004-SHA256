package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class GeneratedTest_ME012 {
    @Test
    public void testFilterBySubstring() {
        assertEquals(Collections.emptyList(),
                humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(Collections.emptyList(), "a"));
        assertEquals(Arrays.asList("abc", "bac", "array"),
                humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(
                        Arrays.asList("abc", "bac", "def", "array"), "a"));
    }
}
