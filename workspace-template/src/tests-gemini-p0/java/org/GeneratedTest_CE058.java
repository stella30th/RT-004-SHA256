package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE058 {

@Test
public void testConstructStopWordList() {
    NLPDataProcessor processor = new NLPDataProcessor();
    List<String> stopWords = processor.constructStopWordList();
    assertEquals(Arrays.asList("a", "an", "the"), stopWords);
}

@Test
public void testRemoveStopWords() {
    NLPDataProcessor processor = new NLPDataProcessor();
    List<String> input = Arrays.asList("this is a test", "an apple and the orange");
    List<String> stopWords = Arrays.asList("a", "an", "the");

    List<List<String>> result = processor.removeStopWords(input, stopWords);
    assertEquals(2, result.size());
    assertEquals(Arrays.asList("this", "is", "test"), result.get(0));
    assertEquals(Arrays.asList("apple", "and", "orange"), result.get(1));
}

@Test
public void testProcessUsesDefaultStopWords() {
    NLPDataProcessor processor = new NLPDataProcessor();
    List<String> input = Arrays.asList("the quick fox jumped over a lazy dog");

    List<List<String>> result = processor.process(input);
    assertEquals(1, result.size());
    assertEquals(Arrays.asList("quick", "fox", "jumped", "over", "lazy", "dog"), result.get(0));
}

@Test
public void testProcessSentenceWithOnlyStopWords() {
    NLPDataProcessor processor = new NLPDataProcessor();
    List<String> input = Arrays.asList("a an the");

    List<List<String>> result = processor.process(input);
    assertEquals(1, result.size());
    assertTrue(result.get(0).isEmpty());
}

}
