package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME013 {
    @Test
    public void testRollingMax() {
        assertEquals(Arrays.asList(1, 2, 3, 3, 3, 4),
                humaneval.correct.ROLLING_MAX.rolling_max(Arrays.asList(1, 2, 3, 2, 3, 4)));
    }
}
