package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE022 {

@Test
public void testRegisterStudent() {
    ClassRegistrationSystem system = new ClassRegistrationSystem();
    ClassRegistrationSystem.Student s1 = new ClassRegistrationSystem.Student("Alice", "CS");
    ClassRegistrationSystem.Student s2 = new ClassRegistrationSystem.Student("Alice", "CS");

    assertEquals(1, system.registerStudent(s1));
    assertEquals(0, system.registerStudent(s2)); // Duplicate
}

@Test
public void testRegisterClass() {
    ClassRegistrationSystem system = new ClassRegistrationSystem();
    List<String> registered = system.registerClass("Alice", "Algorithms");
    assertEquals(1, registered.size());
    assertEquals("Algorithms", registered.get(0));

    registered = system.registerClass("Alice", "Databases");
    assertEquals(2, registered.size());
    assertEquals("Databases", registered.get(1));
}

@Test
public void testGetStudentsByMajorAndGetAllMajor() {
    ClassRegistrationSystem system = new ClassRegistrationSystem();
    system.registerStudent(new ClassRegistrationSystem.Student("Alice", "CS"));
    system.registerStudent(new ClassRegistrationSystem.Student("Bob", "CS"));
    system.registerStudent(new ClassRegistrationSystem.Student("Charlie", "EE"));

    List<String> csStudents = system.getStudentsByMajor("CS");
    assertEquals(2, csStudents.size());
    assertTrue(csStudents.contains("Alice"));
    assertTrue(csStudents.contains("Bob"));

    List<String> majors = system.getAllMajor();
    assertEquals(2, majors.size());
    assertTrue(majors.contains("CS"));
    assertTrue(majors.contains("EE"));
}

@Test
public void testGetMostPopularClassInMajor() {
    ClassRegistrationSystem system = new ClassRegistrationSystem();
    system.registerStudent(new ClassRegistrationSystem.Student("Alice", "CS"));
    system.registerStudent(new ClassRegistrationSystem.Student("Bob", "CS"));

    system.registerClass("Alice", "Algorithms");
    system.registerClass("Alice", "Networking");
    system.registerClass("Bob", "Algorithms");

    String popular = system.getMostPopularClassInMajor("CS");
    assertEquals("Algorithms", popular);
}

}
