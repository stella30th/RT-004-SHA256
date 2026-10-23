package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME019 {
    @Test
    public void testChangeBase() {
        assertEquals("22", humaneval.correct.CHANGE_BASE.change_base(8, 3));
        assertEquals("1000", humaneval.correct.CHANGE_BASE.change_base(8, 2));
        assertEquals("111", humaneval.correct.CHANGE_BASE.change_base(7, 2));
    }
}
