package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE037 {
    @Test public void behavioralCase1() throws Exception {
        EncryptionUtils e=new EncryptionUtils("b"); assertEquals("Bcd!",e.caesarCipher("Abc!",1)); assertEquals("bcd",e.vigenereCipher("abc"));
    }
    @Test public void behavioralCase2() throws Exception {
        assertEquals("ACB",new EncryptionUtils("a").railFenceCipher("ABC",2));
    }
}
