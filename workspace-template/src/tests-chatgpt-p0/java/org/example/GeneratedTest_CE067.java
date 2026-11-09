package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE067 {
    @Test public void behavioralCase1() throws Exception {
        RegexUtils r=new RegexUtils(); assertTrue(r.match("a+","aaa")); assertEquals(Arrays.asList("12","34"),r.findall("[0-9]+","a12b34")); assertEquals("XbX",r.sub("a","X","aba"));
    }
    @Test public void behavioralCase2() throws Exception {
        RegexUtils r=new RegexUtils(); assertEquals(Arrays.asList("a","b"),r.split(",","a,b"));
    }
}
