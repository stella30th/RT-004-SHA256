package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class GeneratedTest_ME017 {
    @Test
    public void testParseMusic() {
        assertEquals(Arrays.asList(4, 2, 1, 2, 1, 1, 4),
                humaneval.correct.PARSE_MUSIC.parse_music("o o| . o| . . o"));
    }
}
