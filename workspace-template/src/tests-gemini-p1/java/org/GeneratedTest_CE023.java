package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE023 {

    private Classroom classroom;
    private Classroom.Course mathCourse;
    private Classroom.Course physicsCourse;

    @Before
    public void setUp() {
        classroom = new Classroom(101);
        mathCourse = new Classroom.Course("Math", "09:00", "10:30");
        physicsCourse = new Classroom.Course("Physics", "11:00", "12:30");
    }

    @Test
    public void testCourseEqualsAndHashCode() {
        Classroom.Course duplicateMath = new Classroom.Course("Math", "09:00", "10:30");
        Classroom.Course differentName = new Classroom.Course("English", "09:00", "10:30");

        assertEquals(mathCourse, duplicateMath);
        assertEquals(mathCourse.hashCode(), duplicateMath.hashCode());
        assertNotEquals(mathCourse, differentName);
        assertNotEquals(mathCourse, null);
    }

    @Test
    public void testAddAndRemoveCourse() {
        classroom.addCourse(mathCourse);
        assertEquals(1, classroom.courses.size());

        classroom.addCourse(mathCourse);
        assertEquals(1, classroom.courses.size());

        classroom.addCourse(physicsCourse);
        assertEquals(2, classroom.courses.size());

        classroom.removeCourse(mathCourse);
        assertEquals(1, classroom.courses.size());
        assertFalse(classroom.courses.contains(mathCourse));
        assertTrue(classroom.courses.contains(physicsCourse));
    }

    @Test
    public void testIsFreeAt() {
        classroom.addCourse(mathCourse);

        assertTrue(classroom.isFreeAt("08:59"));
        assertFalse(classroom.isFreeAt("09:00"));
        assertFalse(classroom.isFreeAt("09:45"));
        assertFalse(classroom.isFreeAt("10:30"));
        assertTrue(classroom.isFreeAt("10:31"));
    }

    @Test
    public void testCheckCourseConflict() {
        classroom.addCourse(mathCourse);

        Classroom.Course overlappingCourse = new Classroom.Course("Chemistry", "10:00", "11:30");
        assertFalse(classroom.checkCourseConflict(overlappingCourse));

        Classroom.Course nonOverlappingCourse = new Classroom.Course("History", "11:00", "12:00");
        assertTrue(classroom.checkCourseConflict(nonOverlappingCourse));

        Classroom.Course touchingEndCourse = new Classroom.Course("Biology", "10:30", "11:00");
        assertFalse(classroom.checkCourseConflict(touchingEndCourse));

        Classroom.Course beforeCourse = new Classroom.Course("PE", "07:00", "08:59");
        assertTrue(classroom.checkCourseConflict(beforeCourse));
    }
}
