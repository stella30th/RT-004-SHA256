package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME027 {
 @Test public void powerOfTwo(){assertEquals(Arrays.asList(2,2,2),humaneval.correct.FACTORIZE.factorize(8));}
 @Test public void repeatedOddPrime(){assertEquals(Arrays.asList(5,5),humaneval.correct.FACTORIZE.factorize(25));}
 @Test public void mixedFactors(){assertEquals(Arrays.asList(2,5,7),humaneval.correct.FACTORIZE.factorize(70));}
 @Test public void primeInput(){assertEquals(Arrays.asList(29),humaneval.correct.FACTORIZE.factorize(29));}
 @Test public void manyFactors(){assertEquals(Arrays.asList(2,2,3,3,5),humaneval.correct.FACTORIZE.factorize(180));}
}
