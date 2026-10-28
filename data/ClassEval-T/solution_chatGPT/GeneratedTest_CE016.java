package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE016 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(Arrays.asList(0,3),new BoyerMooreSearch("abcabc","abc").badCharacterHeuristic());
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals(Arrays.asList(0,1,2,3),new BoyerMooreSearch("abc","").badCharacterHeuristic()); assertTrue(new BoyerMooreSearch("abc","xyz").badCharacterHeuristic().isEmpty());
    }
}
