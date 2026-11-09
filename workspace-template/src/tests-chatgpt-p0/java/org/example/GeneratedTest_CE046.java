package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE046 {
    @Test public void behavioralCase1() throws Exception {
        assertTrue(IpUtil.isValidIpv4("127.0.0.1")); assertFalse(IpUtil.isValidIpv4("999.0.0.1"));
    }
    @Test public void behavioralCase2() throws Exception {
        assertTrue(IpUtil.isValidIpv6("::1"));
    }
}
