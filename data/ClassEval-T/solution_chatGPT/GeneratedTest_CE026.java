package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE026 {
    @Test public void behavioralCase1() throws Exception {
        java.nio.file.Path p=java.nio.file.Files.createTempFile("cookies",".json"); try{ CookiesUtil c=new CookiesUtil(p.toString()); Map<String,Map<String,String>> r=new HashMap<>(); r.put("cookies",Collections.singletonMap("session","abc")); c.getCookies(r); assertEquals("abc",c.loadCookies().get("session")); Map<String,String> req=new HashMap<>(); c.setCookies(req); assertEquals("session=abc",req.get("cookies")); }finally{java.nio.file.Files.deleteIfExists(p);}
    }
}
