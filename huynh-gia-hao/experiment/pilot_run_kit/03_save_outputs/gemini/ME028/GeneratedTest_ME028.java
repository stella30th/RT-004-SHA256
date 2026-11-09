package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME028 {
    @Test
    public void testSortThird() {
        assertEquals(Arrays.asList(1, 2, 3, 2, 5, 6, 9),
                humaneval.correct.SORT_THIRD.sort_third(
                        Arrays.asList(9, 2, 3, 2, 5, 6, 1)));
    }
}
