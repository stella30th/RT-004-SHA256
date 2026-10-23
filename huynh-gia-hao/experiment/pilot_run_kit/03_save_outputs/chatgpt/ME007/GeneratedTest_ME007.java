package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME007 {
 @Test public void mixedBits(){assertEquals("100",humaneval.correct.STRING_XOR.string_xor("010","110"));}
 @Test public void identical(){assertEquals("0000",humaneval.correct.STRING_XOR.string_xor("1011","1011"));}
 @Test public void opposites(){assertEquals("1111",humaneval.correct.STRING_XOR.string_xor("0000","1111"));}
 @Test public void singleBits(){assertEquals("1",humaneval.correct.STRING_XOR.string_xor("1","0"));}
 @Test public void alternating(){assertEquals("111111",humaneval.correct.STRING_XOR.string_xor("101010","010101"));}
}
