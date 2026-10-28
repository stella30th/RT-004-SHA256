package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE041 {
    @Test public void behavioralCase1() throws Exception {
        GomokuGame g=new GomokuGame(5); assertNull(g.checkWinner()); assertTrue(g.makeMove(0,0)); assertFalse(g.makeMove(0,0));
    }
    @Test public void behavioralCase2() throws Exception {
        GomokuGame g=new GomokuGame(5); assertFalse(g.makeMove(-1,0));
    }
}
