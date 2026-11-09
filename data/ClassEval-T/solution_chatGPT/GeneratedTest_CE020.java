package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE020 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(Arrays.asList(2,3,5,7),new ChandrasekharSieve(10).getPrimes()); assertTrue(new ChandrasekharSieve(1).getPrimes().isEmpty());
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals(Arrays.asList(2),new ChandrasekharSieve(2).getPrimes());
    }
}
