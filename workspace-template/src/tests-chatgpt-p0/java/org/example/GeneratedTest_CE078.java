package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE078 {
    @Test public void behavioralCase1() throws Exception {
        StudentDatabaseProcessor.StudentData s=new StudentDatabaseProcessor.StudentData("Ann",20,"F",3); assertEquals("Ann",s.getName()); assertEquals(20,s.getAge()); assertEquals("F",s.getGender()); assertEquals(3,s.getGrade());
    }
}
