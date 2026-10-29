package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GeneratedTest_CE079 {

private File tempFile;
private TextFileProcessor processor;

@Before
public void setUp() throws IOException {
    tempFile = File.createTempFile("test_file_", ".txt");
    processor = new TextFileProcessor(tempFile.getAbsolutePath());
}

@After
public void tearDown() {
    if (tempFile != null && tempFile.exists()) {
        tempFile.delete();
    }
}

@Test
public void testWriteAndReadFile() throws IOException {
    processor.write_file("Hello World!");
    String content = processor.read_file();
    assertEquals("Hello World!", content);
}

@Test
public void testProcessFileStripsNonAlphabetCharacters() throws IOException {
    processor.write_file("Hello, World! 123 #$%");
    String processed = processor.process_file();

    assertEquals("HelloWorld", processed);
    assertEquals("HelloWorld", processor.read_file());
}

@Test
public void testReadFileAsJson() throws IOException {
    processor.write_file("{\"title\":\"Test Book\",\"price\":20}");
    Object json = processor.read_file_as_json();

    assertNotNull(json);
    assertTrue(json instanceof Map);
    Map<?, ?> map = (Map<?, ?>) json;
    assertEquals("Test Book", map.get("title"));
}

@Test
public void testProcessEmptyFile() throws IOException {
    processor.write_file("");
    String processed = processor.process_file();
    assertEquals("", processed);
}

}
