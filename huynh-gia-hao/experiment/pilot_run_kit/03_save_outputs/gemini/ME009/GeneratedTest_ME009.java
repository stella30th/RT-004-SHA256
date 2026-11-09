package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME009 {
    @Test
    public void testCountDistinctCharacters() {
        assertEquals(3,
                humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("abcdeA"));
        assertEquals(4,
                humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("Jerry"));
    }
}
