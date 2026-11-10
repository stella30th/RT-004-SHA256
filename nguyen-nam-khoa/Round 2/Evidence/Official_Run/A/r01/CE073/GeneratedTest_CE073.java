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
public void testSplitSentencesStandard() {
    String text = "Hello world. How are you? I am fine.";
    List<String> result = splitSentence.splitSentences(text);

    assertEquals(3, result.size());
    assertEquals("Hello world.", result.get(0));
    assertEquals("How are you?", result.get(1));
    assertEquals("I am fine.", result.get(2));
}

@Test
public void testSplitSentencesWithAbbreviations() {
    String text = "Mr. Smith went to Washington. He met Dr. Brown.";
    List<String> result = splitSentence.splitSentences(text);

    assertEquals(2, result.size());
    assertEquals("Mr. Smith went to Washington.", result.get(0));
    assertEquals("He met Dr. Brown.", result.get(1));
}

@Test
public void testSplitSentencesSingleAndEmpty() {
    String text = "This is a single sentence without terminal punctuation";
    List<String> singleResult = splitSentence.splitSentences(text);
    assertEquals(1, singleResult.size());
    assertEquals(text, singleResult.get(0));

    List<String> emptyResult = splitSentence.splitSentences("");
    assertTrue(emptyResult.isEmpty());
}

@Test
public void testCountWords() {
    assertEquals(2, splitSentence.countWords("Hello, world!"));
    assertEquals(3, splitSentence.countWords("There are 123 apples."));
    assertEquals(4, splitSentence.countWords("One   two   three   four"));
}

@Test
public void testProcessTextFile() {
    String text = "Short sentence. This second sentence has more words than the first one. Okay?";
    int maxWords = splitSentence.processTextFile(text);

    assertEquals(10, maxWords);
}

@Test
public void testProcessTextFileEmpty() {
    int maxWords = splitSentence.processTextFile("");
    assertEquals(0, maxWords);
}

}