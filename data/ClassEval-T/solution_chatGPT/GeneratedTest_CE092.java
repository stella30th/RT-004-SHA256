package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE092 {
    @Test public void behavioralCase1() throws Exception {
        Words2Numbers w=new Words2Numbers(); assertTrue(w.isValidInput("one")); assertEquals("1",w.text2int("one"));
    }
}
