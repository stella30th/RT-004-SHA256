package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE001 {
    @Test public void behavioralCase1() throws Exception {
        AccessGatewayFilter a=new AccessGatewayFilter(); assertTrue(a.isStartWith("/api/users")); assertTrue(a.isStartWith("/login")); assertFalse(a.isStartWith("/private"));
    }
    @Test public void behavioralCase2() throws Exception {
        AccessGatewayFilter a=new AccessGatewayFilter(); Map<String,Object> r=new HashMap<>(); r.put("path","/api/test"); assertTrue(a.filter(r)); r.put("path","/private"); assertFalse(a.filter(r));
    }
}
