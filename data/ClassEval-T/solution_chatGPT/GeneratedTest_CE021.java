package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE021 {
    @Test public void behavioralCase1() throws Exception {
        Chat c=new Chat(); assertTrue(c.addUser("A")); assertFalse(c.addUser("A")); assertTrue(c.addUser("B")); assertTrue(c.sendMessage("A","B","hello")); assertEquals("hello",c.getMessages("B").get(0).getMessage());
    }
    @Test public void behavioralCase2() throws Exception {
        Chat c=new Chat(); assertFalse(c.sendMessage("x","y","hi")); assertTrue(c.addUser("x")); assertTrue(c.removeUser("x")); assertFalse(c.removeUser("x"));
    }
}
