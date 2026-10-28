package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE074 {
    @Test public void behavioralCase1() throws Exception {
        SQLGenerator g=new SQLGenerator("users"); assertTrue(g.select(new String[]{"name"},"age > 1").contains("users"));
    }
}
