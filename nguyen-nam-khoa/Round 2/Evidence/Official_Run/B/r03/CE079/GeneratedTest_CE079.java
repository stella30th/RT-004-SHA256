package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.IOException; import java.nio.file.Files;
import java.util.List; import java.util.Map;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE079 {

@Rule
public TemporaryFolder tempFolder = new TemporaryFolder();

@Test
public void testWriteAndReadFile() throws IOException {
    File file = tempFolder.newFile("test_rw.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String content = "Hello, World! 123";
    processor.write_file(content);

    String readResult = processor.read_file();
    assertEquals("Hello, World! 123", readResult);
}

@Test
public void testReadFileAsJsonObject() throws IOException {
    File file = tempFolder.newFile("test_json_map.json");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String jsonContent = "{\"name\":\"Alice\",\"age\":30}";
    processor.write_file(jsonContent);

    Object result = processor.read_file_as_json();
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals("Alice", map.get("name"));
    assertEquals(30, map.get("age"));
}

@Test
public void testReadFileAsJsonArray() throws IOException {
    File file = tempFolder.newFile("test_json_list.json");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String jsonContent = "[\"apple\",\"banana\",\"cherry\"]";
    processor.write_file(jsonContent);

    Object result = processor.read_file_as_json();
    assertTrue(result instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(3, list.size());
    assertEquals("apple", list.get(0));
    assertEquals("banana", list.get(1));
    assertEquals("cherry", list.get(2));
}

@Test
public void testProcessFileFiltersNonAlphabeticalCharactersAndOverwrites() throws IOException {
    File file = tempFolder.newFile("test_process.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String originalContent = "Hello 123, World! @2026 #JUnit";
    processor.write_file(originalContent);

    String processed = processor.process_file();
    assertEquals("HelloWorldJUnit", processed);

    String fileContentAfter = processor.read_file();
    assertEquals("HelloWorldJUnit", fileContentAfter);
}

@Test
public void testProcessFileWithEmptyAndNonAlphabeticalContent() throws IOException {
    File file = tempFolder.newFile("test_empty_result.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    processor.write_file("12345!@#$%^&*()_+ \n\t");
    String processed = processor.process_file();
    assertEquals("", processed);
    assertEquals("", processor.read_file());
}

@Test(expected = IOException.class)
public void testReadNonExistentFileThrowsException() throws IOException {
    File nonExistent = new File(tempFolder.getRoot(), "missing_file.txt");
    TextFileProcessor processor = new TextFileProcessor(nonExistent.getAbsolutePath());
    processor.read_file();
}

}