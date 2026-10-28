package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE094 {
    @Test public void behavioralCase1() throws Exception {
        ZipFileProcessor z=new ZipFileProcessor("nonexistent.zip"); assertFalse(z.extractAll("nonexistent-directory"));
    }
}
