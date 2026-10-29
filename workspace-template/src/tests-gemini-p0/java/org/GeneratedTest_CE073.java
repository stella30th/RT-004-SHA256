package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE073 {

@Test
public void testSplitSentences() {
    SplitSentence splitter = new SplitSentence();
    String text = "Hello world. How are you today? I am fine.";
    List<String> sentences = splitter.splitSentences(text);

    assertEquals(3, sentences.size());
    assertEquals("Hello world.", sentences.get(0));
    assertEquals("How are you today?", sentences.get(1));
    assertEquals("I am fine.", sentences.get(2));
}

@Test
public void testCountWords() {
    SplitSentence splitter = new SplitSentence();
    assertEquals(3, splitter.countWords("One two three."));
    assertEquals(4, splitter.countWords("  Word, another! And   more? "));
}

@Test
public void testProcessTextFileReturnsMaxWordCount() {
    SplitSentence splitter = new SplitSentence();
    String text = "Short sentence. This is a much longer sentence with many words.";
    int maxCount = splitter.processTextFile(text);
    assertEquals(10, maxCount);
}

@Test
public void testSplitSentencesSingleSentence() {
    SplitSentence splitter = new SplitSentence();
    List<String> sentences = splitter.splitSentences("Just one sentence without boundary.");
    assertEquals(1, sentences.size());
    assertEquals("Just one sentence without boundary.", sentences.get(0));
}

}
