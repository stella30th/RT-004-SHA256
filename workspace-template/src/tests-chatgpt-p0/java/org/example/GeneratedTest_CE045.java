package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE045 {
    @Test public void behavioralCase1() throws Exception {
        IPAddress ip=new IPAddress("192.168.1.1"); assertTrue(ip.isValid()); assertEquals(4,ip.getOctets().size());
    }
    @Test public void behavioralCase2() throws Exception {
        assertFalse(new IPAddress("999.999.999.999").isValid());
    }
}
