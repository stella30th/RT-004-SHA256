package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE005 {

@Test
public void testAddStudentAndCalculateGPA() {
    AssessmentSystem system = new AssessmentSystem();
    system.addStudent("Alice", 1, "CS");
    system.addCourseScore("Alice", "Math", 90);
    system.addCourseScore("Alice", "Physics", 80);

    Double gpa = system.getGPA("Alice");
    assertNotNull(gpa);
    assertEquals(85.0, gpa, 0.001);

    assertNull(system.getGPA("NonExistent"));
}

@Test
public void testGetAllStudentsWithFailCourse() {
    AssessmentSystem system = new AssessmentSystem();
    system.addStudent("Bob", 2, "EE");
    system.addStudent("Charlie", 2, "EE");

    system.addCourseScore("Bob", "Circuits", 55);
    system.addCourseScore("Charlie", "Circuits", 75);

    List<String> failingStudents = system.getAllStudentsWithFailCourse();
    assertEquals(1, failingStudents.size());
    assertTrue(failingStudents.contains("Bob"));
    assertFalse(failingStudents.contains("Charlie"));
}

@Test
public void testGetCourseAverage() {
    AssessmentSystem system = new AssessmentSystem();
    system.addStudent("StudentA", 3, "ME");
    system.addStudent("StudentB", 3, "ME");
    system.addStudent("StudentC", 3, "ME");

    system.addCourseScore("StudentA", "Thermodynamics", 70);
    system.addCourseScore("StudentB", "Thermodynamics", 80);
    system.addCourseScore("StudentC", "Thermodynamics", 90);

    Double avg = system.getCourseAverage("Thermodynamics");
    assertNotNull(avg);
    assertEquals(80.0, avg, 0.001);

    assertNull(system.getCourseAverage("ArtHistory"));
}

@Test
public void testGetTopStudent() {
    AssessmentSystem system = new AssessmentSystem();
    system.addStudent("Alice", 1, "CS");
    system.addStudent("Bob", 1, "CS");

    system.addCourseScore("Alice", "Math", 95);
    system.addCourseScore("Bob", "Math", 85);

    String topStudent = system.getTopStudent();
    assertEquals("Alice", topStudent);
}

}
