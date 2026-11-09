// BEGIN GeneratedTest_CE050
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE050 {

    @Test public void longestMustBeInDictionary() {
        LongestWord l = new LongestWord();
        assertEquals("", l.findLongestWord("no words"));
        l.addWord("cat");
        l.addWord("elephant");
        l.addWord("dog");
        assertEquals("elephant", l.findLongestWord("cat elephant dog"));
        assertEquals("cat", l.findLongestWord("extraordinary cat"));
    }
    @Test public void lowercasesSentenceNotDictionary() {
        LongestWord l = new LongestWord();
        l.addWord("hello");
        l.addWord("WORLD");
        assertEquals("hello", l.findLongestWord("HELLO WORLD"));
        assertEquals("", l.findLongestWord("WORLD"));
    }
    @Test public void removesPunctuationWithoutAddingSpaces() {
        LongestWord l = new LongestWord();
        l.addWord("helloworld");
        l.addWord("world");
        assertEquals("helloworld", l.findLongestWord("Hello-World!"));
        assertEquals("world", l.findLongestWord("hi, world."));
    }
    @Test public void tiesGoToFirstMatchingWordInSentence() {
        LongestWord l = new LongestWord();
        l.addWord("cat");
        l.addWord("dog");
        assertEquals("dog", l.findLongestWord("dog cat"));
        assertEquals("cat", l.findLongestWord("cat dog"));
    }
    @Test public void splitsOnSpaceNotTabs() {
        LongestWord l = new LongestWord();
        l.addWord("apple");
        l.addWord("banana");
        assertEquals("banana", l.findLongestWord("   apple    banana   "));
        assertEquals("", l.findLongestWord("apple\tbanana"));
    }
    @Test(expected = NullPointerException.class) public void nullSentenceThrows() {
        new LongestWord().findLongestWord(null);
    }

    @Test public void emptySentenceReturnsEmpty() { LongestWord l = new LongestWord(); l.addWord("a"); assertEquals("", l.findLongestWord("")); }
}
// END GeneratedTest_CE050
