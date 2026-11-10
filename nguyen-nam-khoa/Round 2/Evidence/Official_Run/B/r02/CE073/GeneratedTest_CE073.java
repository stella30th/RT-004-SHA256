package org.example;

import org.junit.Before; import org.junit.Test;

import java.util.Arrays; import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE073 {

private SplitSentence splitSentence;

@Before
public void setUp() {
    splitSentence = new SplitSentence();
}

@Test
public void testSplitSentences_basic() {
    String text = "Hello world. How are you? Fine thanks.";
    List<String> actual = splitSentence.splitSentences(text);
    List<String> expected = Arrays.asList("Hello world.", "How are you?", "Fine thanks.");
    assertEquals(expected, actual);
}

@Test
public void testSplitSentences_withAbbreviations() {
    String text = "Mr. Smith went to Washington. He saw e.g. monuments.";
    List<String> actual = splitSentence.splitSentences(text);
    List<String> expected = Arrays.asList("Mr. Smith went to Washington.", "He saw e.g. monuments.");
    assertEquals(expected, actual);
}

@Test
public void testSplitSentences_singleSentenceNoEndingPunctuation() {
    String text = "Just a single sentence without terminal punctuation";
    List<String> actual = splitSentence.splitSentences(text);
    List<String> expected = Arrays.asList("Just a single sentence without terminal punctuation");
    assertEquals(expected, actual);
}

@Test
public void testCountWords_normal() {
    String sentence = "Hello, world! This is a test.";
    int count = splitSentence.countWords(sentence);
    assertEquals(6, count);
}

@Test
public void testCountWords_withPunctuationAndExtraSpaces() {
    String sentence = "There are... cats   and   dogs!";
    int count = splitSentence.countWords(sentence);
    assertEquals(5, count);
}

@Test
public void testProcessTextFile_findsMaxWordCount() {
    String text = "One. Two words. Three words here.";
    int maxWords = splitSentence.processTextFile(text);
    assertEquals(3, maxWords);
}

@Test
public void testProcessTextFile_singleSentence() {
    String text = "The quick brown fox jumps over the lazy dog";
    int maxWords = splitSentence.processTextFile(text);
    assertEquals(9, maxWords);
}

}