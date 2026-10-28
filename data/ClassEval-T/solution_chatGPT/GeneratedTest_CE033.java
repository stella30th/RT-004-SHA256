package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE033 {
    @Test public void behavioralCase1() throws Exception {
        DecryptionUtils d=new DecryptionUtils("b"); assertEquals("Abc!",d.caesarDecipher("Bcd!",1)); assertEquals("abc",d.vigenereDecipher("bcd"));
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals("ABC",new DecryptionUtils("a").railFenceDecipher("ABC",2));
    }
}
