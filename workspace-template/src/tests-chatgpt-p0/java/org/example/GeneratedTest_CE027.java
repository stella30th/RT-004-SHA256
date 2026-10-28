package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE027 {
    @Test public void behavioralCase1() throws Exception {
        java.nio.file.Path p=java.nio.file.Files.createTempFile("csv",".csv"); try{CSVProcessor c=new CSVProcessor(); assertEquals(1,c.writeCSV(Arrays.asList("a","b"),Arrays.asList(Arrays.asList("x","1")),p.toString())); List<String> h=new ArrayList<>(); List<List<String>> rows=new ArrayList<>(); c.readCSV(p.toString(),h,rows); assertEquals(Arrays.asList("a","b"),h); assertEquals(Arrays.asList("x","1"),rows.get(0));}finally{java.nio.file.Files.deleteIfExists(p);}
    }
}
