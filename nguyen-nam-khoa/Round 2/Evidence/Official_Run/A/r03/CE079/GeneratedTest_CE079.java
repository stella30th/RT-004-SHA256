package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.IOException; import java.util.List; import
java.util.Map;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE079 {

@Rule
public TemporaryFolder temporaryFolder = new TemporaryFolder();

@Test
public void testWriteAndReadFile() throws IOException {
    File file = temporaryFolder.newFile("test_rw.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String content = "Hello JUnit 4\nLine 2";
    processor.write_file(content);

    String readContent = processor.read_file();
    assertEquals(content, readContent);
}

@Test
public void testReadFileAsJsonObject() throws IOException {
    File file = temporaryFolder.newFile("test_obj.json");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String jsonContent = "{\"name\":\"Alice\",\"age\":30}";
    processor.write_file(jsonContent);

    Object result = processor.read_file_as_json();
    assertTrue("Parsed JSON should be a Map", result instanceof Map);

    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals("Alice", map.get("name"));
    assertEquals(30, map.get("age"));
}

@Test
public void testReadFileAsJsonArray() throws IOException {
    File file = temporaryFolder.newFile("test_array.json");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String jsonContent = "[\"first\", \"second\", \"third\"]";
    processor.write_file(jsonContent);

    Object result = processor.read_file_as_json();
    assertTrue("Parsed JSON should be a List", result instanceof List);

    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) result;
    assertEquals(3, list.size());
    assertEquals("first", list.get(0));
    assertEquals("second", list.get(1));
    assertEquals("third", list.get(2));
}

@Test
public void testProcessFileWithMixedContent() throws IOException {
    File file = temporaryFolder.newFile("test_process.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    processor.write_file("Hello, World! 123 #$%");
    String processed = processor.process_file();

    assertEquals("HelloWorld", processed);
    assertEquals("HelloWorld", processor.read_file());
}

@Test
public void testProcessFileWithNonAlphabetOnly() throws IOException {
    File file = temporaryFolder.newFile("test_non_alpha.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    processor.write_file("12345!@#$%^&*() \t\n");
    String processed = processor.process_file();

    assertEquals("", processed);
    assertEquals("", processor.read_file());
}

@Test
public void testProcessFileWithAlphabetOnly() throws IOException {
    File file = temporaryFolder.newFile("test_alpha_only.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String alphaContent = "AbCdEfGhIjKlMnOpQrStUvWxYz";
    processor.write_file(alphaContent);
    String processed = processor.process_file();

    assertEquals(alphaContent, processed);
    assertEquals(alphaContent, processor.read_file());
}

@Test(expected = IOException.class)
public void testReadNonExistentFileThrowsException() throws IOException {
    File nonExistent = new File(temporaryFolder.getRoot(), "missing_file.txt");
    TextFileProcessor processor = new TextFileProcessor(nonExistent.getAbsolutePath());
    processor.read_file();
}

}