package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE035 {
    @Test public void behavioralCase1() throws Exception {
        int[][] goal={{1,2,3},{4,5,6},{7,8,0}}; EightPuzzle p=new EightPuzzle(goal); assertArrayEquals(new int[]{2,2},p.findBlank(goal)); assertEquals(Collections.emptyList(),p.solve());
    }
    @Test public void behavioralCase2() throws Exception {
        int[][] a={{1,2,3},{4,5,6},{7,0,8}}; EightPuzzle p=new EightPuzzle(a); assertEquals(Arrays.asList("up","left","right"),p.getPossibleMoves(a)); assertEquals(Arrays.asList("right"),p.solve());
    }
}
