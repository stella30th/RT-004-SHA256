package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE029 {
    @Test public void behavioralCase1() throws Exception {
        java.nio.file.Path p=java.nio.file.Files.createTempFile("dbproc",".sqlite"); try {DatabaseProcessor db=new DatabaseProcessor(p.toString()); db.createTable("people","name","age"); Map<String,Object> row=new HashMap<>(); row.put("name","Ann"); row.put("age",21); db.insertIntoDatabase("people",Arrays.asList(row)); assertEquals("Ann",db.searchDatabase("people","Ann").get(0).get("name")); db.deleteFromDatabase("people","Ann"); assertNull(db.searchDatabase("people","Ann"));}finally{java.nio.file.Files.deleteIfExists(p);}
    }
}
