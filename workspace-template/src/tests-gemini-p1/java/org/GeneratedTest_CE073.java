package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE073 {

    private SplitSentence splitSentence;

    @Before
    public void setUp() {
        splitSentence = new SplitSentence();
    }

    @Test
    public void testSplitSentencesStandard() {
        String input = "This is sentence one. This is sentence two? And three.";
        List<String> sentences = splitSentence.splitSentences(input);
        assertEquals(3, sentences.size());
        assertEquals("This is sentence one.", sentences.get(0));
        assertEquals("This is sentence two?", sentences.get(1));
        assertEquals("And three.", sentences.get(2));
    }

    @Test
    public void testSplitSentencesWithTitleAbbreviation() {
        String input = "Mr. Smith went home. How are you?";
        List<String> sentences = splitSentence.splitSentences(input);
        assertEquals(2, sentences.size());
        assertEquals("Mr. Smith went home.", sentences.get(0));
        assertEquals("How are you?", sentences.get(1));
    }

    @Test
    public void testCountWords() {
        assertEquals(4, splitSentence.countWords("This is a test."));
        assertEquals(3, splitSentence.countWords("How are you?"));
        assertEquals(2, splitSentence.countWords("Hello, world!"));
    }

    @Test
    public void testProcessTextFile() {
        String input = "Mr. Smith went to the market today. It was raining.";
        int maxWords = splitSentence.processTextFile(input);
        assertEquals(7, maxWords);
    }
}
