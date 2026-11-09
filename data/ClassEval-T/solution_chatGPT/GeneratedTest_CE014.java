package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE014 {
    @Test public void behavioralCase1() throws Exception {
        BookManagement b=new BookManagement(); b.addBook("X",3); b.addBook("X",2); assertEquals(5,b.viewBookQuantity("X")); b.removeBook("X",5); assertEquals(0,b.viewBookQuantity("X"));
    }
    @Test public void behavioralCase2() throws Exception {
        BookManagement b=new BookManagement(); b.addBook("A",2); Map<String,Integer> m=b.viewInventory(); m.put("B",3); assertEquals(0,b.viewBookQuantity("B"));
    }
}
