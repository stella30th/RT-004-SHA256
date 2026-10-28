package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE003 {
    @Test public void behavioralCase1() throws Exception {
        ArgumentParser p=new ArgumentParser(); p.addArgument("count",true,Integer.class); assertFalse(p.parseArguments("app").x); assertTrue(p.parseArguments("app --count=12").x); assertEquals(12,p.getArgument("count"));
    }
    @Test public void behavioralCase2() throws Exception {
        ArgumentParser p=new ArgumentParser(); p.addArgument("flag",false,Boolean.class); p.parseArguments("app --flag=false"); assertEquals(Boolean.FALSE,p.getArgument("flag"));
    }
}
