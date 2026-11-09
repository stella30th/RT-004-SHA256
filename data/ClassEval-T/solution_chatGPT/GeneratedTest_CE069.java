package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE069 {
    @Test public void behavioralCase1() throws Exception {
        Server s=new Server(); assertTrue(s.addWhiteList(12).contains(12)); assertFalse(s.delWhiteList(12).contains(12));
    }
}
