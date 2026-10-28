package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE005 {
    @Test public void behavioralCase1() throws Exception {
        AssessmentSystem a=new AssessmentSystem(); a.addStudent("Ann",1,"CS"); a.addCourseScore("Ann","math",80); a.addCourseScore("Ann","eng",60); assertEquals(70.0,a.getGPA("Ann"),0); assertEquals("Ann",a.getTopStudent());
    }
    @Test public void behavioralCase2() throws Exception {
        AssessmentSystem a=new AssessmentSystem(); a.addStudent("A",1,"IT"); a.addCourseScore("A","math",59); assertEquals(Arrays.asList("A"),a.getAllStudentsWithFailCourse()); assertNull(a.getCourseAverage("eng"));
    }
}
