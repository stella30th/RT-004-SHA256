package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE038 {
    @Test public void behavioralCase1() throws Exception {
        ExcelProcessor e=new ExcelProcessor(); java.nio.file.Path p=java.nio.file.Files.createTempFile("cells",".xlsx"); try{assertTrue(e.writeExcel(Arrays.asList(Arrays.asList((Object)"Name",(Object)12.0)),p.toString())); assertEquals("Name",e.readExcel(p.toString()).get(0).get(0));}finally{java.nio.file.Files.deleteIfExists(p);}
    }
}
