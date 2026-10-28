package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE022 {
    @Test public void behavioralCase1() throws Exception {
        ClassRegistrationSystem c=new ClassRegistrationSystem(); ClassRegistrationSystem.Student a=new ClassRegistrationSystem.Student("A","CS"); assertEquals(1,c.registerStudent(a)); assertEquals(0,c.registerStudent(new ClassRegistrationSystem.Student("A","CS"))); assertEquals(Arrays.asList("A"),c.getStudentsByMajor("CS"));
    }
    @Test public void behavioralCase2() throws Exception {
        ClassRegistrationSystem c=new ClassRegistrationSystem(); c.registerStudent(new ClassRegistrationSystem.Student("A","CS")); assertEquals(Arrays.asList("Math"),c.registerClass("A","Math")); assertEquals("Math",c.getMostPopularClassInMajor("CS"));
    }
}
