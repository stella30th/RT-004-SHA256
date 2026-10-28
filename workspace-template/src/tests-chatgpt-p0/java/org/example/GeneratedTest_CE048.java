package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE048 {
    @Test public void behavioralCase1() throws Exception {
        JSONProcessor j=new JSONProcessor(); java.nio.file.Path p=java.nio.file.Files.createTempFile("json",".json"); try{Map<String,Object> m=new HashMap<>();m.put("name","A"); assertTrue(j.writeJson(m,p.toString())); assertEquals("A",j.readJson(p.toString()).get("name"));}finally{java.nio.file.Files.deleteIfExists(p);}
    }
}
