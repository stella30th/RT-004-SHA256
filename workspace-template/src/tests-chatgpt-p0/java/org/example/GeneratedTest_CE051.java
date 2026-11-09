package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE051 {
    @Test public void behavioralCase1() throws Exception {
        MahjongConnect m=new MahjongConnect(new int[]{2,2},Arrays.asList("A","B")); assertEquals(2,m.createBoard().length);
    }
}
