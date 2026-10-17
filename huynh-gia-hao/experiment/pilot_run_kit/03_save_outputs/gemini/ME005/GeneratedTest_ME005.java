package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME005 {
    @Test
    public void testTriangleArea() {
        assertEquals(7.5, humaneval.correct.TRIANGLE_AREA.triangle_area(5, 3), 0.0001);
        assertEquals(2.0, humaneval.correct.TRIANGLE_AREA.triangle_area(2, 2), 0.0001);
        assertEquals(40.0, humaneval.correct.TRIANGLE_AREA.triangle_area(10, 8), 0.0001);
    }
}
