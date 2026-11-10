package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.IOException; import java.util.Map;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE079 {

@Rule
public TemporaryFolder tempFolder = new TemporaryFolder();

@Test
public void testWriteAndReadFile() throws IOException {
    File tempFile = tempFolder.newFile("test_rw.txt");
    TextFileProcessor processor = new TextFileProcessor(tempFile.getAbsolutePath());

    String content = "Hello World! Line 1\nLine 2";
    processor.write_file(content);

    String readContent = processor.read_file();
    assertEquals(content, readContent);
}

@Test
public void testProcessFileFiltersNonAlphabetical() throws IOException {
    File tempFile = tempFolder.newFile("test_process.txt");
    TextFileProcessor processor = new TextFileProcessor(tempFile.getAbsolutePath());

    processor.write_file("Hello, World! 123\n#Tag_2023");
    String result = processor.process_file();

    assertEquals("HelloWorldTag", result);
    assertEquals("HelloWorldTag", processor.read_file());
}

@Test
public void testProcessFileWithAllNonAlphabetical() throws IOException {
    File tempFile = tempFolder.newFile("test_empty.txt");
    TextFileProcessor processor = new TextFileProcessor(tempFile.getAbsolutePath());

    processor.write_file("123456!@#$%^&*() \t\n");
    String result = processor.process_file();

    assertEquals("", result);
    assertEquals("", processor.read_file());
}

@Test
public void testProcessFileOnlyAlphabetical() throws IOException {
    File tempFile = tempFolder.newFile("test_letters.txt");
    TextFileProcessor processor = new TextFileProcessor(tempFile.getAbsolutePath());

    processor.write_file("HelloWorld");
    String result = processor.process_file();

    assertEquals("HelloWorld", result);
    assertEquals("HelloWorld", processor.read_file());
}

@Test
public void testReadFileAsJson() throws IOException {
    File tempFile = tempFolder.newFile("test_data.json");
    TextFileProcessor processor = new TextFileProcessor(tempFile.getAbsolutePath());

    String jsonContent = "{\"name\":\"Alice\",\"age\":30}";
    processor.write_file(jsonContent);

    Object parsed = processor.read_file_as_json();
    assertNotNull(parsed);
    assertTrue(parsed instanceof Map);

    Map<?, ?> map = (Map<?, ?>) parsed;
    assertEquals("Alice", map.get("name"));
    assertEquals(30, map.get("age"));
}

}