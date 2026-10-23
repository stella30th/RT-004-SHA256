package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME016 {
    @Test
    public void testParseNestedParens() {
        assertEquals(Arrays.asList(2, 3, 1, 3),
                humaneval.correct.PARSE_NESTED_PARENS.parse_nested_parens(
                        "(()()) ((())) () ((())()())"));
    }
}
