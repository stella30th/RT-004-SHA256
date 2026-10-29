package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE050 {

@Test
public void testFindLongestWordPresentInList() {
    LongestWord finder = new LongestWord();
    finder.addWord("a");
    finder.addWord("am");
    finder.addWord("student");

    String result = finder.findLongestWord("I am a student.");
    assertEquals("student", result);
}

@Test
public void testWordCaseInsensitiveMatch() {
    LongestWord finder = new LongestWord();
    finder.addWord("hello");
    finder.addWord("world");

    String result = finder.findLongestWord("HELLO, wonderful world!");
    // Both "hello" and "world" have length 5, first found with length > longest is "hello"
    assertEquals("hello", result);
}

@Test
public void testNoWordsInListMatchSentence() {
    LongestWord finder = new LongestWord();
    finder.addWord("apple");
    finder.addWord("banana");

    String result = finder.findLongestWord("The quick brown fox.");
    assertEquals("", result);
}

@Test
public void testPunctuationStripped() {
    LongestWord finder = new LongestWord();
    finder.addWord("programming");

    String result = finder.findLongestWord("Is (programming!) fun? Yes!");
    assertEquals("programming", result);
}

}
