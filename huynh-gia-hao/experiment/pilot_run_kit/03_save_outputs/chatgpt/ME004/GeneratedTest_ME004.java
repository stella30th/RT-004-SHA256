package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME004 {
 @Test public void belowTwoNotPrime(){assertFalse(humaneval.correct.IS_PRIME.is_prime(1));assertFalse(humaneval.correct.IS_PRIME.is_prime(0));}
 @Test public void twoAndThree(){assertTrue(humaneval.correct.IS_PRIME.is_prime(2));assertTrue(humaneval.correct.IS_PRIME.is_prime(3));}
 @Test public void composites(){assertFalse(humaneval.correct.IS_PRIME.is_prime(4));assertFalse(humaneval.correct.IS_PRIME.is_prime(49));}
 @Test public void ordinaryPrimes(){assertTrue(humaneval.correct.IS_PRIME.is_prime(11));assertTrue(humaneval.correct.IS_PRIME.is_prime(101));}
 @Test public void negativeNotPrime(){assertFalse(humaneval.correct.IS_PRIME.is_prime(-7));}
}
