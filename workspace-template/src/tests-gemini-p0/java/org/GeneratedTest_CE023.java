package org.example;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE023 {

private ClassroomManagementTest.Course createCourse(String name, String startStr, String endStr) {
    try {
        Class<?> clazz = ClassroomManagementTest.Course.class;
        LocalTime start = LocalTime.parse(startStr);
        LocalTime end = LocalTime.parse(endStr);
        for (java.lang.reflect.Constructor<?> ctor : clazz.getConstructors()) {
            Class<?>[] pTypes = ctor.getParameterTypes();
            if (pTypes.length == 3 && pTypes[0] == String.class && pTypes[1] == String.class && pTypes[2] == String.class) {
                return (ClassroomManagementTest.Course) ctor.newInstance(name, startStr, endStr);
            }
            if (pTypes.length == 3 && pTypes[0] == String.class && pTypes[1] == LocalTime.class && pTypes[2] == LocalTime.class) {
                return (ClassroomManagementTest.Course) ctor.newInstance(name, start, end);
            }
            if (pTypes.length == 2 && pTypes[0] == LocalTime.class && pTypes[1] == LocalTime.class) {
                return (ClassroomManagementTest.Course) ctor.newInstance(start, end);
            }
            if (pTypes.length == 2 && pTypes[0] == String.class && pTypes[1] == String.class) {
                return (ClassroomManagementTest.Course) ctor.newInstance(startStr, endStr);
            }
        }
        ClassroomManagementTest.Course course = (ClassroomManagementTest.Course) clazz.getDeclaredConstructor().newInstance();
        for (java.lang.reflect.Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getName().equals("name")) f.set(course, name);
            if (f.getName().equals("startTime")) f.set(course, start);
            if (f.getName().equals("endTime")) f.set(course, end);
        }
        return course;
    } catch (Exception e) {
        throw new RuntimeException("Failed to instantiate ClassroomManagementTest.Course", e);
    }
}

@Test
public void testAddAndRemoveCourse() {
    Classroom classroom = new Classroom(101);
    ClassroomManagementTest.Course c1 = createCourse("Math", "08:00", "09:30");

    classroom.addCourse(c1);
    assertFalse(classroom.isFreeAt("08:30"));

    classroom.removeCourse(c1);
    assertTrue(classroom.isFreeAt("08:30"));
}

@Test
public void testIsFreeAt() {
    Classroom classroom = new Classroom(102);
    ClassroomManagementTest.Course c1 = createCourse("Physics", "09:00", "11:00");
    classroom.addCourse(c1);

    assertTrue(classroom.isFreeAt("08:59"));
    assertFalse(classroom.isFreeAt("09:00"));
    assertFalse(classroom.isFreeAt("10:00"));
    assertFalse(classroom.isFreeAt("11:00"));
    assertTrue(classroom.isFreeAt("11:01"));
}

@Test
public void testCheckCourseConflictWhenNoConflict() {
    Classroom classroom = new Classroom(103);
    ClassroomManagementTest.Course c1 = createCourse("Chemistry", "09:00", "10:30");
    classroom.addCourse(c1);

    ClassroomManagementTest.Course nonConflictingBefore = createCourse("Lab1", "07:30", "08:59");
    assertTrue(classroom.checkCourseConflict(nonConflictingBefore));

    ClassroomManagementTest.Course nonConflictingAfter = createCourse("Lab2", "10:31", "12:00");
    assertTrue(classroom.checkCourseConflict(nonConflictingAfter));
}

@Test
public void testCheckCourseConflictWhenOverlapping() {
    Classroom classroom = new Classroom(104);
    ClassroomManagementTest.Course c1 = createCourse("Biology", "10:00", "12:00");
    classroom.addCourse(c1);

    ClassroomManagementTest.Course conflictOverlap = createCourse("Seminar", "11:00", "13:00");
    assertFalse(classroom.checkCourseConflict(conflictOverlap));

    ClassroomManagementTest.Course conflictContained = createCourse("Quiz", "10:30", "11:30");
    assertFalse(classroom.checkCourseConflict(conflictContained));
}

}
