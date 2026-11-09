// BEGIN GeneratedTest_CE050
package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE050 {

    @Test
    public void testLongestMatchingWord() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");
        finder.addWord("elephant");
        finder.addWord("dog");

        assertEquals("elephant",
                finder.findLongestWord("cat elephant dog"));
    }

    @Test
    public void testNoMatchingWordAndEmptySentence() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");

        assertEquals("", finder.findLongestWord("world"));
        assertEquals("", finder.findLongestWord(""));
        assertEquals("", finder.findLongestWord("   "));
    }

    @Test
    public void testCaseConversionAndPunctuationRemoval() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("hello",
                finder.findLongestWord("HELLO, WORLD!"));
        assertEquals("world",
                finder.findLongestWord("(WORLD)"));
    }

    @Test
    public void testEqualLengthKeepsFirstMatch() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("grape");

        assertEquals("apple",
                finder.findLongestWord("apple grape"));
        assertEquals("grape",
                finder.findLongestWord("grape apple"));
    }

    @Test
    public void testDictionaryUpdatesBetweenSearches() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");

        assertEquals("cat",
                finder.findLongestWord("cat elephant"));

        finder.addWord("elephant");

        assertEquals("elephant",
                finder.findLongestWord("cat elephant"));
        assertEquals("cat",
                finder.findLongestWord("cat"));
    }

    @Test
    public void testPunctuationRemovalMergesAdjacentWords() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");
        finder.addWord("helloworld");

        assertEquals("helloworld",
                finder.findLongestWord("hello-world"));
        assertEquals("helloworld",
                finder.findLongestWord("hello,world"));
    }
}
// END GeneratedTest_CE050