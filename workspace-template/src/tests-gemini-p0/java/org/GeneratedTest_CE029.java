package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class GeneratedTest_CE029 {

private File dbFile;
private DatabaseProcessor processor;

@Before
public void setUp() throws IOException {
    dbFile = File.createTempFile("test_ce029_", ".db");
    processor = new DatabaseProcessor(dbFile.getAbsolutePath());
}

@After
public void tearDown() {
    if (dbFile != null && dbFile.exists()) {
        dbFile.delete();
    }
}

@Test
public void testCreateInsertAndSearch() {
    processor.createTable("users", "name", "age");

    List<Map<String, Object>> data = new ArrayList<>();
    Map<String, Object> user1 = new HashMap<>();
    user1.put("name", "Alice");
    user1.put("age", 25);
    data.add(user1);

    processor.insertIntoDatabase("users", data);

    List<Map<String, Object>> results = processor.searchDatabase("users", "Alice");
    assertNotNull(results);
    assertEquals(1, results.size());
    assertEquals("Alice", results.get(0).get("name"));
    assertEquals(25, results.get(0).get("age"));
}

@Test
public void testSearchNonExistentReturnsNull() {
    processor.createTable("members", "name", "age");
    List<Map<String, Object>> results = processor.searchDatabase("members", "Bob");
    assertNull(results);
}

@Test
public void testDeleteFromDatabase() {
    processor.createTable("clients", "name", "age");

    List<Map<String, Object>> data = new ArrayList<>();
    Map<String, Object> client = new HashMap<>();
    client.put("name", "Charlie");
    client.put("age", 30);
    data.add(client);

    processor.insertIntoDatabase("clients", data);
    assertNotNull(processor.searchDatabase("clients", "Charlie"));

    processor.deleteFromDatabase("clients", "Charlie");
    assertNull(processor.searchDatabase("clients", "Charlie"));
}

@Test
public void testInsertMultipleRecords() {
    processor.createTable("people", "name", "age");

    List<Map<String, Object>> data = new ArrayList<>();
    Map<String, Object> p1 = new HashMap<>();
    p1.put("name", "David");
    p1.put("age", 40);
    Map<String, Object> p2 = new HashMap<>();
    p2.put("name", "David");
    p2.put("age", 45);
    data.add(p1);
    data.add(p2);

    processor.insertIntoDatabase("people", data);

    List<Map<String, Object>> results = processor.searchDatabase("people", "David");
    assertNotNull(results);
    assertEquals(2, results.size());
}

}
