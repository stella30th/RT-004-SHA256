package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME020 {
    @Test
    public void testFruitDistribution() {
        assertEquals(8,
                humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution(
                        "5 apples and 6 oranges", 19));
        assertEquals(2,
                humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution(
                        "0 apples and 1 oranges", 3));
        assertEquals(95,
                humaneval.correct.FRUIT_DISTRIBUTION.fruit_distribution(
                        "2 apples and 3 oranges", 100));
    }
}
