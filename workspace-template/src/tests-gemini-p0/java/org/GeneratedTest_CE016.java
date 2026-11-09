package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE016 {

@Test
public void testSingleOccurrence() {
    BoyerMooreSearch search = new BoyerMooreSearch("HERE IS A SIMPLE EXAMPLE", "EXAMPLE");
    List<Integer> positions = search.badCharacterHeuristic();
    assertEquals(Arrays.asList(17), positions);
}

@Test
public void testMultipleOccurrences() {
    BoyerMooreSearch search = new BoyerMooreSearch("ABAAABCDABAA", "ABAA");
    List<Integer> positions = search.badCharacterHeuristic();
    assertEquals(Arrays.asList(0, 8), positions);
}

@Test
public void testPatternNotFound() {
    BoyerMooreSearch search = new BoyerMooreSearch("HELLO WORLD", "XYZ");
    List<Integer> positions = search.badCharacterHeuristic();
    assertTrue(positions.isEmpty());
}

@Test
public void testEmptyPattern() {
    BoyerMooreSearch search = new BoyerMooreSearch("ABC", "");
    List<Integer> positions = search.badCharacterHeuristic();
    assertEquals(Arrays.asList(0, 1, 2, 3), positions);
}

}
