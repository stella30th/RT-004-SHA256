package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE019 {
    @Test public void behavioralCase1() throws Exception {
        CamelCaseMap m=new CamelCaseMap(); m.put("first_name",42); assertEquals(42,m.get("firstName")); assertEquals(1,m.size()); m.remove("first_name"); assertEquals(0,m.size());
    }
    @Test public void behavioralCase2() throws Exception {
        CamelCaseMap m=new CamelCaseMap(); m.put("a_b",1); m.put("aB",2); assertEquals(1,m.size()); assertEquals(2,m.get("a_b"));
    }
}
