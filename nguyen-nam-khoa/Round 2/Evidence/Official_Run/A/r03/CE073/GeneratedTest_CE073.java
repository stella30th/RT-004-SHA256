package org.example;

import org.junit.Before; import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertTrue;

public class GeneratedTest_CE073 {

private SplitSentence splitSentence;

@Before
public void setUp() {
    splitSentence = new SplitSentence();
}

@Test
public void testSplitSentencesBasic() {
    String input = "Hello world. How are you? Everything is fine.";
    List<String> sentences = splitSentence.splitSentences(input);

    assertEquals(3, sentences.size());
    assertEquals("Hello world.", sentences.get(0));
    assertEquals("How are you?", sentences.get(1));
    assertEquals("Everything is fine.", sentences.get(2));
}

@Test
public void testSplitSentencesWithTitleAbbreviation() {
    String input = "Mr. Smith went to Washington. Did he arrive safely?";
    List<String> sentences = splitSentence.splitSentences(input);

    assertEquals(2, sentences.size());
    assertEquals("Mr. Smith went to Washington.", sentences.get(0));
    assertEquals("Did he arrive safely?", sentences.get(1));
}

@Test
public void testSplitSentencesWithPatternAbbreviation() {
    String input = "Use e.g. this example. Then check the result.";
    List<String> sentences = splitSentence.splitSentences(input);

    assertEquals(2, sentences.size());
    assertEquals("Use e.g. this example.", sentences.get(0));
    assertEquals("Then check the result.", sentences.get(1));
}

@Test
public void testSplitSentencesSingleSentence() {
    String input = "Just one simple sentence without terminating space.";
    List<String> sentences = splitSentence.splitSentences(input);

    assertEquals(1, sentences.size());
    assertEquals("Just one simple sentence without terminating space.", sentences.get(0));
}

@Test
public void testCountWordsStandardSentence() {
    String sentence = "The quick brown fox jumps over the lazy dog.";
    int count = splitSentence.countWords(sentence);
    assertEquals(9, count);
}

@Test
public void testCountWordsWithPunctuationAndNumbers() {
    String sentence = "In 2023, he bought 3 apples and 5 oranges!";
    // Non-letter characters like digits and commas are removed, leaving words separated by spaces.
    int count = splitSentence.countWords(sentence);
    assertEquals(6, count);
}

@Test
public void testProcessTextFileSelectsMaxWordCount() {
    String text = "Short sentence. This second sentence clearly contains many more words. Is it?";
    int maxWords = splitSentence.processTextFile(text);

    // "Short sentence." -> 2 words
    // "This second sentence clearly contains many more words." -> 8 words
    // "Is it?" -> 2 words
    assertEquals(8, maxWords);
}

@Test
public void testProcessTextFileEmptyHandling() {
    int maxWords = splitSentence.processTextFile("");
    assertEquals(0, maxWords);
}

}