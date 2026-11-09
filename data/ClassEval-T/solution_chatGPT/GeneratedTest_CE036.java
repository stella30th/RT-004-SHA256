package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE036 {
    @Test public void behavioralCase1() throws Exception {
        EmailClient a=new EmailClient("a",100),b=new EmailClient("b",10); assertTrue(a.sendTo(b,"hello",3)); assertEquals(3,b.getOccupiedSize(),0); assertEquals("hello",b.fetch().get("content")); assertNull(b.fetch());
    }
    @Test public void behavioralCase2() throws Exception {
        EmailClient a=new EmailClient("a",10),b=new EmailClient("b",2); assertFalse(a.sendTo(b,"hi",3)); assertEquals(0,b.getInbox().size());
    }
}
