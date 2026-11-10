package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.IOException; import java.nio.file.Files;
import java.util.Map;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertNotNull; import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE079 {

@Rule
public TemporaryFolder tempFolder = new TemporaryFolder();

@Test
public void testReadFile() throws IOException {
    File file = tempFolder.newFile("sample.txt");
    String content = "Hello, world! 123";
    Files.write(file.toPath(), content.getBytes());

    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());
    String result = processor.read_file();

    assertEquals(content, result);
}

@Test
public void testWriteFile() throws IOException {
    File file = tempFolder.newFile("output.txt");
    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());

    String contentToWrite = "Testing write functionality.";
    processor.write_file(contentToWrite);

    String readBack = new String(Files.readAllBytes(file.toPath()));
    assertEquals(contentToWrite, readBack);
}

@Test
public void testProcessFile() throws IOException {
    File file = tempFolder.newFile("process.txt");
    String originalContent = "Hello, World! 123\n#JUnit";
    Files.write(file.toPath(), originalContent.getBytes());

    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());
    String processed = processor.process_file();

    assertEquals("HelloWorldJUnit", processed);

    String fileContentAfter = processor.read_file();
    assertEquals("HelloWorldJUnit", fileContentAfter);
}

@Test
public void testProcessFileNonAlphabetOnly() throws IOException {
    File file = tempFolder.newFile("symbols.txt");
    String symbolsOnly = "123456!@#$%^&*()_+ \t\n";
    Files.write(file.toPath(), symbolsOnly.getBytes());

    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());
    String processed = processor.process_file();

    assertEquals("", processed);
    assertEquals("", processor.read_file());
}

@Test
public void testReadFileAsJson() throws IOException {
    File file = tempFolder.newFile("data.json");
    String jsonContent = "{\"name\":\"TestUser\",\"count\":42}";
    Files.write(file.toPath(), jsonContent.getBytes());

    TextFileProcessor processor = new TextFileProcessor(file.getAbsolutePath());
    Object result = processor.read_file_as_json();

    assertNotNull(result);
    assertTrue(result instanceof Map);
    @SuppressWarnings("unchecked")
    Map<String, Object> map = (Map<String, Object>) result;
    assertEquals("TestUser", map.get("name"));
    assertEquals(42, map.get("count"));
}

@Test(expected = IOException.class)
public void testReadFileThrowsExceptionWhenFileNotFound() throws IOException {
    File nonExistentFile = new File(tempFolder.getRoot(), "missing_file.txt");
    TextFileProcessor processor = new TextFileProcessor(nonExistentFile.getAbsolutePath());
    processor.read_file();
}

}