package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE050 {
    @Test public void behavioralCase1() throws Exception {
        LongestWord l=new LongestWord(); assertEquals("elephant",l.findLongestWord("cat elephant dog"));
    }
    @Test public void behavioralCase2() throws Exception {
        LongestWord l=new LongestWord(); l.addWord("alphabet"); assertEquals("alphabet",l.findLongestWord("hi"));
    }
}
