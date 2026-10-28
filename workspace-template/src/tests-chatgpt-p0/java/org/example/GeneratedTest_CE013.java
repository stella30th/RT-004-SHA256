package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE013 {
    @Test public void behavioralCase1() throws Exception {
        BlackjackGame b=new BlackjackGame(); assertEquals("Player wins",b.checkWinner(Arrays.asList("AS","KH"),Arrays.asList("10S","9H")));
    }
    @Test public void behavioralCase2() throws Exception {
        BlackjackGame b=new BlackjackGame(); assertEquals("Dealer wins",b.checkWinner(Arrays.asList("10H","8H"),Arrays.asList("10C","8C"))); assertEquals("Dealer wins",b.checkWinner(Arrays.asList("10H","9H","5H"),Arrays.asList("10C","8C")));
    }
}
