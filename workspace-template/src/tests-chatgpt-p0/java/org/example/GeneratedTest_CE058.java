package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE058 {
    @Test public void behavioralCase1() throws Exception {
        NLPDataProcessor n=new NLPDataProcessor(); assertEquals(1,n.removeStopWords(Arrays.asList("hello world"),Collections.emptyList()).size());
    }
}
