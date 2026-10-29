package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE078 {

private File dbFile;
private StudentDatabaseProcessor processor;

@Before
public void setUp() throws IOException {
    dbFile = File.createTempFile("test_students_", ".db");
    processor = new StudentDatabaseProcessor(dbFile.getAbsolutePath());
    processor.createStudentTable();
}

@After
public void tearDown() {
    if (dbFile != null && dbFile.exists()) {
        dbFile.delete();
    }
}

@Test
public void testInsertAndSearchStudent() {
    StudentDatabaseProcessor.StudentData data =
            new StudentDatabaseProcessor.StudentData("Alice", 20, "Female", 3);
    processor.insertStudent(data);

    List<Map<String, Object>> results = processor.searchStudentByName("Alice");
    assertEquals(1, results.size());

    Map<String, Object> student = results.get(0);
    assertEquals("Alice", student.get("name"));
    assertEquals(20, student.get("age"));
    assertEquals("Female", student.get("gender"));
    assertEquals(3, student.get("grade"));
}

@Test
public void testDeleteStudentByName() {
    StudentDatabaseProcessor.StudentData data =
            new StudentDatabaseProcessor.StudentData("Bob", 22, "Male", 4);
    processor.insertStudent(data);

    List<Map<String, Object>> before = processor.searchStudentByName("Bob");
    assertEquals(1, before.size());

    processor.deleteStudentByName("Bob");
    List<Map<String, Object>> after = processor.searchStudentByName("Bob");
    assertTrue(after.isEmpty());
}

@Test
public void testSearchNonExistentStudent() {
    List<Map<String, Object>> results = processor.searchStudentByName("Nobody");
    assertTrue(results.isEmpty());
}

@Test
public void testStudentDataGetters() {
    StudentDatabaseProcessor.StudentData data =
            new StudentDatabaseProcessor.StudentData("Charlie", 19, "Male", 1);
    assertEquals("Charlie", data.getName());
    assertEquals(19, data.getAge());
    assertEquals("Male", data.getGender());
    assertEquals(1, data.getGrade());
}

}
