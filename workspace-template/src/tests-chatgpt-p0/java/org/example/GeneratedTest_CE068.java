package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE068 {
    @Test public void behavioralCase1() throws Exception {
        RPGCharacter c=new RPGCharacter("Hero",100,20,5); assertTrue(c.isAlive()); assertEquals(100,c.getHp()); c.attack(new RPGCharacter("Mob",50,1,0)); assertEquals("Hero",c.getName());
    }
}
