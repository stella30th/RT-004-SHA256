package humaneval.generated;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_ME007 {

    @Test
    public void testEmptyStrings() {
        assertEquals("", humaneval.correct.STRING_XOR.string_xor("", ""));
    }

    @Test
    public void testSingleZeroXorZero() {
        assertEquals("0", humaneval.correct.STRING_XOR.string_xor("0", "0"));
    }

    @Test
    public void testSingleZeroXorOne() {
        assertEquals("1", humaneval.correct.STRING_XOR.string_xor("0", "1"));
    }

    @Test
    public void testSingleOneXorZero() {
        assertEquals("1", humaneval.correct.STRING_XOR.string_xor("1", "0"));
    }

    @Test
    public void testSingleOneXorOne() {
        assertEquals("0", humaneval.correct.STRING_XOR.string_xor("1", "1"));
    }

    @Test
    public void testIdenticalStringsProduceAllZeros() {
        assertEquals(
                "00000000",
                humaneval.correct.STRING_XOR.string_xor(
                        "10110101",
                        "10110101"
                )
        );
    }

    @Test
    public void testOppositeStringsProduceAllOnes() {
        assertEquals(
                "11111111",
                humaneval.correct.STRING_XOR.string_xor(
                        "10101010",
                        "01010101"
                )
        );
    }

    @Test
    public void testMixedBinaryStrings() {
        assertEquals(
                "01101",
                humaneval.correct.STRING_XOR.string_xor(
                        "01010",
                        "00111"
                )
        );
    }

    @Test
    public void testLeadingZerosArePreserved() {
        assertEquals(
                "0011",
                humaneval.correct.STRING_XOR.string_xor(
                        "0010",
                        "0001"
                )
        );
    }

    @Test
    public void testAllZerosAgainstMixedString() {
        assertEquals(
                "101001",
                humaneval.correct.STRING_XOR.string_xor(
                        "000000",
                        "101001"
                )
        );
    }

    @Test
    public void testAllOnesAgainstMixedString() {
        assertEquals(
                "010110",
                humaneval.correct.STRING_XOR.string_xor(
                        "111111",
                        "101001"
                )
        );
    }

    @Test
    public void testCommutativeInputs() {
        String a = "11001001";
        String b = "01110100";
        String expected = "10111101";

        assertEquals(
                expected,
                humaneval.correct.STRING_XOR.string_xor(a, b)
        );

        assertEquals(
                expected,
                humaneval.correct.STRING_XOR.string_xor(b, a)
        );
    }

    @Test
    public void testLongerBinaryStrings() {
        assertEquals(
                "1111111100000000",
                humaneval.correct.STRING_XOR.string_xor(
                        "1010101010101010",
                        "0101010110101010"
                )
        );
    }
}