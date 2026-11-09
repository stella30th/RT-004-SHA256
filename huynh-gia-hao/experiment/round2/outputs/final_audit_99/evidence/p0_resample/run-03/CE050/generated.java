// BEGIN GeneratedTest_CE050
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE050 {

    @Test
    public void testInitialDictionaryIsEmpty() {
        LongestWord finder = new LongestWord();

        assertEquals("", finder.findLongestWord("hello world"));
        assertEquals("", finder.findLongestWord(""));
    }

    @Test
    public void testFindLongestMatchingWord() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");
        finder.addWord("elephant");
        finder.addWord("dog");

        assertEquals("elephant",
                finder.findLongestWord("the cat and elephant saw a dog"));
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
    public void testNoMatchingWordsReturnsEmptyString() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("banana");

        assertEquals("", finder.findLongestWord("orange grape melon"));
    }

    @Test
    public void testSingleMatchingWord() {
        LongestWord finder = new LongestWord();
        finder.addWord("programming");

        assertEquals("programming",
                finder.findLongestWord("programming"));
    }

    @Test
    public void testSentenceConvertedToLowercase() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("hello", finder.findLongestWord("HELLO World"));
        assertEquals("world", finder.findLongestWord("WORLD"));
    }

    @Test
    public void testDictionaryMatchingIsCaseSensitive() {
        LongestWord finder = new LongestWord();
        finder.addWord("HELLO");
        finder.addWord("world");

        assertEquals("world",
                finder.findLongestWord("HELLO WORLD"));
        assertEquals("",
                finder.findLongestWord("HELLO"));
    }

    @Test
    public void testPunctuationIsRemoved() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("world",
                finder.findLongestWord("Hello, World!"));
    }

    @Test
    public void testPunctuationRemovalJoinsWords() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");
        finder.addWord("helloworld");

        assertEquals("helloworld",
                finder.findLongestWord("Hello-World!"));
    }

    @Test
    public void testMultiplePunctuationCharacters() {
        LongestWord finder = new LongestWord();
        finder.addWord("abcdef");
        finder.addWord("xyz");

        assertEquals("abcdef",
                finder.findLongestWord("ab-cd!ef? xyz."));
    }

    @Test
    public void testEqualLengthWordsReturnFirstOccurrence() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");
        finder.addWord("dog");
        finder.addWord("sun");

        assertEquals("dog",
                finder.findLongestWord("dog cat sun"));
        assertEquals("sun",
                finder.findLongestWord("sun dog cat"));
    }

    @Test
    public void testDictionaryInsertionOrderDoesNotDetermineResult() {
        LongestWord finder = new LongestWord();
        finder.addWord("elephant");
        finder.addWord("cat");
        finder.addWord("dog");

        assertEquals("cat",
                finder.findLongestWord("cat dog"));
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
    public void testLeadingAndTrailingSpaces() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("banana");

        assertEquals("banana",
                finder.findLongestWord("   apple banana   "));
    }

    @Test
    public void testTabIsNotAWordSeparator() {
        LongestWord finder = new LongestWord();
        finder.addWord("hello");
        finder.addWord("world");

        assertEquals("",
                finder.findLongestWord("hello\tworld"));

        finder.addWord("hello\tworld");

        assertEquals("hello\tworld",
                finder.findLongestWord("hello\tworld"));
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
    public void testAddWordAfterPreviousSearch() {
        LongestWord finder = new LongestWord();
        finder.addWord("cat");

        assertEquals("cat",
                finder.findLongestWord("cat elephant"));

        finder.addWord("elephant");

        assertEquals("elephant",
                finder.findLongestWord("cat elephant"));
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
    public void testEmptyWordInDictionary() {
        LongestWord finder = new LongestWord();
        finder.addWord("");

        assertEquals("", finder.findLongestWord(""));
        assertEquals("", finder.findLongestWord("   "));
    }

    @Test
    public void testNullWordInDictionary() {
        LongestWord finder = new LongestWord();
        finder.addWord(null);
        finder.addWord("hello");

        assertEquals("hello",
                finder.findLongestWord("hello"));
    }

    @Test
    public void testRepeatedSearchesDoNotChangeDictionary() {
        LongestWord finder = new LongestWord();
        finder.addWord("apple");
        finder.addWord("banana");

        assertEquals("banana",
                finder.findLongestWord("apple banana"));
        assertEquals("apple",
                finder.findLongestWord("apple orange"));
        assertEquals("banana",
                finder.findLongestWord("banana apple"));
    }

    @Test
    public void testWordListContainsAddedWords() {
        LongestWord finder = new LongestWord();

        finder.addWord("apple");
        finder.addWord("banana");
        finder.addWord("apple");

        assertEquals(3, finder.wordList.size());
        assertEquals("apple", finder.wordList.get(0));
        assertEquals("banana", finder.wordList.get(1));
        assertEquals("apple", finder.wordList.get(2));
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

    @Test(expected = NullPointerException.class)
    public void testNullSentenceThrowsException() {
        LongestWord finder = new LongestWord();
        finder.findLongestWord(null);
    }
}
// END GeneratedTest_CE050