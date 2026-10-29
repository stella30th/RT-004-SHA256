package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

public class GeneratedTest_CE059 {

@Test
public void testProcessDataNormalizesAndStripsPunctuation() {
    NLPDataProcessor2 processor = new NLPDataProcessor2();
    List<String> strings = Arrays.asList("Hello, World!", "Testing 123...");
    List<List<String>> processed = processor.processData(strings);

    assertEquals(2, processed.size());
    assertEquals(Arrays.asList("hello", "world"), processed.get(0));
    assertEquals(Arrays.asList("testing"), processed.get(1));
}

@Test
public void testCalculateWordFrequencyRetainsDuplicates() {
    NLPDataProcessor2 processor = new NLPDataProcessor2();
    List<List<String>> wordsList = Arrays.asList(
            Arrays.asList("apple", "banana", "apple"),
            Arrays.asList("banana", "cherry", "banana")
    );

    Vector<NLPDataProcessor2.WordFrequency> freq = processor.calculateWordFrequency(wordsList);
    // "banana": 3, "apple": 2, "cherry": 1 (filtered out because freq <= 1 and != "%%%")
    assertEquals(2, freq.size());
    assertEquals("banana", freq.get(0).getWord());
    assertEquals(3, freq.get(0).getFrequency());
    assertEquals("apple", freq.get(1).getWord());
    assertEquals(2, freq.get(1).getFrequency());
}

@Test
public void testWordFrequencyEqualsHashCodeToStringAndComparator() {
    NLPDataProcessor2.WordFrequency wf1 = new NLPDataProcessor2.WordFrequency("cat", 5);
    NLPDataProcessor2.WordFrequency wf2 = new NLPDataProcessor2.WordFrequency("cat", 5);
    NLPDataProcessor2.WordFrequency wf3 = new NLPDataProcessor2.WordFrequency("dog", 2);

    assertEquals(wf1, wf2);
    assertNotEquals(wf1, wf3);
    assertEquals(wf1.hashCode(), wf2.hashCode());
    assertTrue(wf1.toString().contains("cat"));

    var comparator = NLPDataProcessor2.WordFrequency.byFrequencyThenWord();
    assertTrue(comparator.compare(wf1, wf3) < 0); // wf1 has higher frequency -> precedes wf3
}

@Test
public void testProcessEndToEnd() {
    NLPDataProcessor2 processor = new NLPDataProcessor2();
    List<String> data = Arrays.asList("quick fox", "fox jumped over lazy dog", "quick fox");

    Vector<NLPDataProcessor2.WordFrequency> result = processor.process(data);
    assertEquals(2, result.size());
    assertEquals("fox", result.get(0).getWord());
    assertEquals(3, result.get(0).getFrequency());
    assertEquals("quick", result.get(1).getWord());
    assertEquals(2, result.get(1).getFrequency());
}

}
