package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE071 {
    @Test public void behavioralCase1() throws Exception {
        SignInSystem s=new SignInSystem(); assertTrue(s.addUser("Ann")); assertFalse(s.addUser("Ann")); assertFalse(s.checkSignIn("Ann")); assertTrue(s.signIn("Ann")); assertTrue(s.checkSignIn("Ann"));
    }
}
