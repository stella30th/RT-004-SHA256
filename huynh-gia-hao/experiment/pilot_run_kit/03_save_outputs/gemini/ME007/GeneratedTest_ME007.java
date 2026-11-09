package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_ME007 {
    @Test
    public void testStringXor() {
        assertEquals("010", humaneval.correct.STRING_XOR.string_xor("010", "000"));
        assertEquals("01", humaneval.correct.STRING_XOR.string_xor("11", "10"));
        assertEquals("1111", humaneval.correct.STRING_XOR.string_xor("0101", "1010"));
    }
}
