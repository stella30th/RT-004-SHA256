package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE085 {
    @Test public void behavioralCase1() throws Exception {
        URLHandler u=new URLHandler("https://example.com/path?a=1#frag"); assertEquals("https",u.getScheme()); assertEquals("example.com",u.getHost()); assertEquals("/path",u.getPath());
    }
}
