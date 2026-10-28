package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE064 {
    @Test public void behavioralCase1() throws Exception {
        PageUtil p=new PageUtil(Arrays.asList(1,2,3,4,5),2); assertEquals(Arrays.asList(1,2),p.getPage(1));
    }
}
