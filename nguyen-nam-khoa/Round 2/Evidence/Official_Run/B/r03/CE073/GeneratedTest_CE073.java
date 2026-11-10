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
public void testSplitSentencesStandardPunctuation() {
    String text = "This is the first sentence. Is this the second sentence? Yes it is.";
    List<String> sentences = splitSentence.splitSentences(text);

    assertEquals(3, sentences.size());
    assertEquals("This is the first sentence.", sentences.get(0));
    assertEquals("Is this the second sentence?", sentences.get(1));
    assertEquals("Yes it is.", sentences.get(2));
}

@Test
public void testSplitSentencesWithTitleAbbreviation() {
    String text = "Mr. Smith went to Washington. He met Dr. Brown there.";
    List<String> sentences = splitSentence.splitSentences(text);

    assertEquals(2, sentences.size());
    assertEquals("Mr. Smith went to Washington.", sentences.get(0));
    assertEquals("He met Dr. Brown there.", sentences.get(1));
}

@Test
public void testSplitSentencesWithLookbehindAbbreviation() {
    String text = "Look at e.g. this example. It is very simple.";
    List<String> sentences = splitSentence.splitSentences(text);

    assertEquals(2, sentences.size());
    assertEquals("Look at e.g. this example.", sentences.get(0));
    assertEquals("It is very simple.", sentences.get(1));
}

@Test
public void testSplitSentencesSingleSentenceWithoutPunctuation() {
    String text = "Just a single sentence with no ending punctuation";
    List<String> sentences = splitSentence.splitSentences(text);

    assertEquals(1, sentences.size());
    assertEquals("Just a single sentence with no ending punctuation", sentences.get(0));
}

@Test
public void testSplitSentencesEmptyString() {
    String text = "";
    List<String> sentences = splitSentence.splitSentences(text);

    assertTrue(sentences.isEmpty());
}

@Test
public void testCountWordsStandardSentence() {
    String sentence = "Hello, world! This is a test sentence.";
    int count = splitSentence.countWords(sentence);

    assertEquals(7, count);
}

@Test
public void testCountWordsWithExtraSpacesAndSpecialCharacters() {
    String sentence = "Word1   Word2 #$% Word3";
    // After removing non-alpha non-space: "Word   Word  Word"
    // Splitting by \s+ gives 3 tokens: ["Word", "Word", "Word"]
    int count = splitSentence.countWords(sentence);

    assertEquals(3, count);
}

@Test
public void testProcessTextFileFindsMaxWordCount() {
    String text = "Short sentence. A much longer sentence with many more words in it.";
    int maxCount = splitSentence.processTextFile(text);

    // First sentence has 2 words ("Short", "sentence").
    // Second sentence has 9 words ("A", "much", "longer", "sentence", "with", "many", "more", "words", "in", "it").
    assertEquals(9, maxCount);
}

@Test
public void testProcessTextFileSingleSentence() {
    String text = "Only four simple words";
    int maxCount = splitSentence.processTextFile(text);

    assertEquals(4, maxCount);
}

}