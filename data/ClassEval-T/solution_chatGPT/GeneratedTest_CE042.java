package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE042 {
    @Test public void behavioralCase1() throws Exception {
        Hotel h=new Hotel("H",new HashMap<String,Integer>(Collections.singletonMap("single",2))); assertEquals(2,h.getAvailableRooms("single"));
    }
}
