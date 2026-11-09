package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE081 {
    @Test public void behavioralCase1() throws Exception {
        TicTacToe t=new TicTacToe(); assertNull(t.checkWinner()); assertTrue(t.makeMove(0,0)); assertFalse(t.makeMove(0,0)); assertFalse(t.isBoardFull());
    }
}
