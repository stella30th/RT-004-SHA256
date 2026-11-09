package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE089 {
    @Test public void behavioralCase1() throws Exception {
        VendingMachine v=new VendingMachine(); v.addItem("Water",2,3); assertEquals(2,v.insertCoin(2),0);
    }
}
