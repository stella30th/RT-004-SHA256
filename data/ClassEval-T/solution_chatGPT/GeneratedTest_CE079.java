package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE079 {
    @Test public void behavioralCase1() throws Exception {
        java.nio.file.Path f=java.nio.file.Files.createTempFile("text",".txt"); try{TextFileProcessor p=new TextFileProcessor(f.toString()); p.write_file("hello"); assertEquals("hello",p.read_file());}finally{java.nio.file.Files.deleteIfExists(f);}
    }
}
