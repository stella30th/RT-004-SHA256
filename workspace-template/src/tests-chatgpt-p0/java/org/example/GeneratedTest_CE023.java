package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE023 {
    @Test public void behavioralCase1() throws Exception {
        Classroom c=new Classroom(1); assertTrue(c.isFreeAt("10:00")); assertTrue(c.courses.isEmpty());
    }
}
