package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE009 {
    @Test public void behavioralCase1() throws Exception {
        BankAccount a=new BankAccount(); assertEquals(0,a.viewBalance()); assertEquals(100,a.deposit(100)); assertEquals(60,a.withdraw(40));
    }
    @Test public void behavioralCase2() throws Exception {
        BankAccount a=new BankAccount(100),b=new BankAccount(20); a.transfer(b,30); assertEquals(70,a.viewBalance()); assertEquals(50,b.viewBalance());
    }
    @Test public void behavioralCase3() throws Exception {
        try { new BankAccount().withdraw(1); fail("Expected exception"); } catch(IllegalArgumentException expected) {assertEquals("Insufficient balance.",expected.getMessage());}
    }
}
