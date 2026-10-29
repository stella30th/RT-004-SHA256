package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE020 {

@Test
public void testPrimesUpToTwenty() {
    ChandrasekharSieve sieve = new ChandrasekharSieve(20);
    List<Integer> primes = sieve.getPrimes();
    assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19), primes);
}

@Test
public void testPrimesLessThanTwo() {
    ChandrasekharSieve sieveNegative = new ChandrasekharSieve(-5);
    assertTrue(sieveNegative.getPrimes().isEmpty());

    ChandrasekharSieve sieveZero = new ChandrasekharSieve(0);
    assertTrue(sieveZero.getPrimes().isEmpty());

    ChandrasekharSieve sieveOne = new ChandrasekharSieve(1);
    assertTrue(sieveOne.getPrimes().isEmpty());
}

@Test
public void testSmallBoundaryPrimes() {
    ChandrasekharSieve sieveTwo = new ChandrasekharSieve(2);
    assertEquals(Collections.singletonList(2), sieveTwo.getPrimes());

    ChandrasekharSieve sieveThree = new ChandrasekharSieve(3);
    assertEquals(Arrays.asList(2, 3), sieveThree.getPrimes());
}

@Test
public void testPrimesUpToThirty() {
    ChandrasekharSieve sieve = new ChandrasekharSieve(30);
    List<Integer> primes = sieve.getPrimes();
    assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29), primes);
}

}
