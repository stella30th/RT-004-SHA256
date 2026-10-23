package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME027 {
    @Test
    public void testFactorize() {
        assertEquals(Arrays.asList(2, 2, 2),
                humaneval.correct.FACTORIZE.factorize(8));
        assertEquals(Arrays.asList(5, 7),
                humaneval.correct.FACTORIZE.factorize(35));
    }
}
