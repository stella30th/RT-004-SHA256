package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE072 {
    @Test public void behavioralCase1() throws Exception {
        Snake.Position p=new Snake.Position(2,3); assertEquals(2,p.getX()); assertEquals(3,p.getY()); assertEquals(new Snake.Position(2,3),p);
    }
    @Test public void behavioralCase2() throws Exception {
        Snake s=new Snake(100,100,10,new Snake.Position(50,50)); assertTrue(s.getLength()>0);
    }
}
