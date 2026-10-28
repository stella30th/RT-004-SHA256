package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE066 {
    @Test public void behavioralCase1() throws Exception {
        PushBoxGame p=new PushBoxGame(new char[][]{{'#','#','#'},{'#','@','#'},{'#','#','#'}}); assertEquals(0,p.getTargetCount()); assertEquals(1,p.getPlayerRow());
    }
}
