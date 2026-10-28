package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE008 {
    @Test public void behavioralCase1() throws Exception {
        BalancedBrackets a=new BalancedBrackets("a([{}])b"); assertTrue(a.checkBalancedBrackets()); assertEquals("([{}])",a.getExpr());
    }
    @Test public void behavioralCase2() throws Exception {
        assertFalse(new BalancedBrackets("([)]").checkBalancedBrackets()); assertFalse(new BalancedBrackets("(").checkBalancedBrackets()); assertTrue(new BalancedBrackets("plain").checkBalancedBrackets());
    }
}
