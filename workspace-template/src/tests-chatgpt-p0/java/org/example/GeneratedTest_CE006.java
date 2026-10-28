package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE006 {
    @Test public void behavioralCase1() throws Exception {
        AutomaticGuitarSimulator a=new AutomaticGuitarSimulator("Am123 C45"); assertEquals(2,a.interpret(false).size()); assertEquals("Am",a.interpret(false).get(0).getChord()); assertEquals("123",a.interpret(false).get(0).getTune());
    }
    @Test public void behavioralCase2() throws Exception {
        assertNull(new AutomaticGuitarSimulator(" ").interpret(false)); assertEquals("Normal Guitar Playing -- Chord: C, Play Tune: 1",new AutomaticGuitarSimulator("C1").display("C","1"));
    }
}
