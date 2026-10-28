package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE076 {
    @Test public void behavioralCase1() throws Exception {
        Statistics3 s=new Statistics3(); assertEquals(2,s.median(new int[]{3,1,2}),0); assertEquals(2.0,s.mean(new int[]{1,2,3}),0);
    }
}
