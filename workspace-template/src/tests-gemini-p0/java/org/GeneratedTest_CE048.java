package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE048 {

private File tempJson;

@Before
public void setUp() throws IOException {
    tempJson = File.createTempFile("test_data_", ".json");
}

@After
public void tearDown() {
    if (tempJson != null && tempJson.exists()) {
        tempJson.delete();
    }
}

@Test
public void testWriteAndReadJson() {
    JSONProcessor processor = new JSONProcessor();
    Map<String, Object> data = new HashMap<>();
    data.put("name", "Alice");
    data.put("age", 25.0);

    boolean written = processor.writeJson(data, tempJson.getAbsolutePath());
    assertTrue(written);

    Map<String, Object> read = processor.readJson(tempJson.getAbsolutePath());
    assertNotNull(read);
    assertEquals("Alice", read.get("name"));
    assertEquals(25.0, ((Number) read.get("age")).doubleValue(), 1e-4);
}

@Test
public void testProcessJsonRemovesKey() {
    JSONProcessor processor = new JSONProcessor();
    Map<String, Object> data = new HashMap<>();
    data.put("title", "Post 1");
    data.put("obsoleteKey", "dropMe");
    processor.writeJson(data, tempJson.getAbsolutePath());

    boolean processed = processor.processJson(tempJson.getAbsolutePath(), "obsoleteKey");
    assertTrue(processed);

    Map<String, Object> updated = processor.readJson(tempJson.getAbsolutePath());
    assertNotNull(updated);
    assertFalse(updated.containsKey("obsoleteKey"));
    assertTrue(updated.containsKey("title"));
}

@Test
public void testProcessJsonMissingKeyReturnsFalse() {
    JSONProcessor processor = new JSONProcessor();
    Map<String, Object> data = new HashMap<>();
    data.put("foo", "bar");
    processor.writeJson(data, tempJson.getAbsolutePath());

    boolean processed = processor.processJson(tempJson.getAbsolutePath(), "nonExistentKey");
    assertFalse(processed);
}

@Test
public void testReadJsonNonExistentFileReturnsNull() {
    JSONProcessor processor = new JSONProcessor();
    Map<String, Object> read = processor.readJson("non_existent_file_path_123.json");
    assertNull(read);
}

}
