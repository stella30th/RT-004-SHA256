package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE043 {
    @Test public void behavioralCase1() throws Exception {
        HRManagementSystem h=new HRManagementSystem(); assertTrue(h.addEmployee(1,"Ann","Dev","IT",1000)); assertFalse(h.addEmployee(1,"Bob","QA","IT",900)); assertEquals(1,h.listEmployees().size());
    }
    @Test public void behavioralCase2() throws Exception {
        HRManagementSystem h=new HRManagementSystem(); assertFalse(h.removeEmployee(5)); assertTrue(h.addEmployee(3,"X","Dev","IT",800)); assertTrue(h.removeEmployee(3)); assertNull(h.getEmployee(3));
    }
}
