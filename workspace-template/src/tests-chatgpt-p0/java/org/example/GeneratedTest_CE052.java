package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE052 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals("abba",new Manacher("zabbaq").palindromicString()); assertEquals("a",new Manacher("a").palindromicString());
    }
}
