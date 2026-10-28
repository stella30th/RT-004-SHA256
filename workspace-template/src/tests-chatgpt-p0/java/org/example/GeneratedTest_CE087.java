package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE087 {
    @Test public void behavioralCase1() throws Exception {
        java.nio.file.Path f=java.nio.file.Files.createTempFile("users",".sqlite"); try{UserLoginDB d=new UserLoginDB(f.toString()); d.insertUser("ann","pass"); assertTrue(d.validateUserLogin("ann","pass")); assertFalse(d.validateUserLogin("ann","wrong")); d.close();}finally{java.nio.file.Files.deleteIfExists(f);}
    }
}
