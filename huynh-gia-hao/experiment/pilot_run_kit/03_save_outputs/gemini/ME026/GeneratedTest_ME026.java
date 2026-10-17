package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME026 {
    @Test
    public void testFindClosestElements() {
        assertArrayEquals(new double[]{3.9, 4.0},
                humaneval.correct.FIND_CLOSEST_ELEMENTS.find_closest_elements(
                        new double[]{1.0, 2.0, 3.9, 4.0, 5.0, 2.2}),
                0.0001);
    }
}
