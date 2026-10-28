package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE050 {

@Test public void dictionaryMembershipRequired() {
 LongestWord w=new LongestWord();
 assertEquals("",w.findLongestWord("longest absent"));
 w.addWord("cat");w.addWord("elephant");
 assertEquals("cat",w.findLongestWord("unlisted! cat"));
 assertEquals("elephant",w.findLongestWord("cat elephant!"));
}
@Test public void caseAndPunctuationTransformation() {
 LongestWord w=new LongestWord();w.addWord("hello");w.addWord("world");
 assertEquals("hello",w.findLongestWord("HELLO, world"));
}

}
