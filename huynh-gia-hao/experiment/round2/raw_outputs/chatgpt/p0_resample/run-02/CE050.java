// BEGIN GeneratedTest_CE050
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE050 {

    @Test
    public void testFindLongestMatchingWord() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");
        finder.addWord("elephant");
        finder.addWord("dog");

        assertEquals("elephant",
                finder.findLongestWord("The cat and elephant saw a dog"));
    }

    @Test
    public void testNoMatchingWordReturnsEmptyString() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("banana");

        assertEquals("", finder.findLongestWord("Orange grape melon"));
        assertEquals("", finder.findLongestWord(""));
    }

    @Test
    public void testCaseInsensitiveSentenceWithLowercaseDictionary() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("hello", finder.findLongestWord("HELLO World"));
        assertEquals("world", finder.findLongestWord("WORLD"));
    }

    @Test
    public void testPunctuationIsRemoved() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");
        finder.addWord("helloworld");

        assertEquals("helloworld",
                finder.findLongestWord("Hello-World!"));
        assertEquals("world",
                finder.findLongestWord("Hello, World!"));
    }

    @Test
    public void testEqualLengthWordsReturnFirstMatch() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");
        finder.addWord("dog");
        finder.addWord("sun");

        assertEquals("dog", finder.findLongestWord("dog cat sun"));
    }

    @Test
    public void testDictionaryIsCaseSensitive() {
        LongestWord finder = new LongestWord();
        finder.addWord("HELLO");
        finder.addWord("world");

        assertEquals("world",
                finder.findLongestWord("HELLO WORLD"));
        assertEquals("",
                finder.findLongestWord("HELLO"));
    }

    @Test
    public void testAddWordAfterInitialSearch() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");

        assertEquals("cat",
                finder.findLongestWord("cat elephant"));

        finder.addWord("elephant");

        assertEquals("elephant",
                finder.findLongestWord("cat elephant"));
    }

    @Test
    public void testLongestWordMustExistInDictionary() {
        LongestWord finder = new LongestWord();
        finder.addWord("short");
        finder.addWord("medium");

        assertEquals("medium",
                finder.findLongestWord("extraordinary medium short"));
    }

    @Test
    public void testMultipleSpacesBetweenWords() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("banana");

        assertEquals("banana",
                finder.findLongestWord("apple    banana"));
    }

    @Test
    public void testSingleWordSentence() {
        LongestWord finder = new LongestWord();
        finder.addWord("programming");

        assertEquals("programming",
                finder.findLongestWord("Programming!"));
    }

    @Test
    public void testEmptyDictionary() {
        LongestWord finder = new LongestWord();

        assertEquals("",
                finder.findLongestWord("This is a test sentence"));
    }

    @Test
    public void testDuplicateDictionaryEntries() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("hello",
                finder.findLongestWord("hello world"));
    }

    @Test
    public void testNumbersArePreserved() {
        LongestWord finder = new LongestWord();
        finder.addWord("abc123");
        finder.addWord("test");

        assertEquals("abc123",
                finder.findLongestWord("ABC123 test"));
    }

    @Test
    public void testIndependentInstances() {
        LongestWord first = new LongestWord();
        LongestWord second = new LongestWord();

        first.addWord("apple");
        second.addWord("banana");

        assertEquals("apple",
                first.findLongestWord("apple banana"));
        assertEquals("banana",
                second.findLongestWord("apple banana"));
    }
}
// END GeneratedTest_CE050