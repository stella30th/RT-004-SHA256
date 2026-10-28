package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE065 {
    @Test public void behavioralCase1() throws Exception {
        PersonRequest p=new PersonRequest("Ann","F","123"); assertEquals("Ann",p.getName()); assertEquals("F",p.getSex()); assertEquals("123",p.getPhoneNumber());
    }
}
