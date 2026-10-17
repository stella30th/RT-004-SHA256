package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class GeneratedTest_ME008 {
    @Test
    public void testAllPrefixes() {
        assertEquals(Collections.emptyList(), humaneval.correct.ALL_PREFIXES.all_prefixes(""));
        assertEquals(Arrays.asList("a", "ab", "abc"),
                humaneval.correct.ALL_PREFIXES.all_prefixes("abc"));
    }
}
