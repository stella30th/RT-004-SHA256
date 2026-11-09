package org.example;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE079 {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File testFile;
    private TextFileProcessor processor;

    @Before
    public void setUp() throws IOException {
        testFile = tempFolder.newFile("sample.txt");
        processor = new TextFileProcessor(testFile.getAbsolutePath());
    }

    @Test
    public void testWriteAndReadFile() throws IOException {
        String content = "Hello World 123!";
        processor.write_file(content);
        String readBack = processor.read_file();
        assertEquals(content, readBack);
    }

    @Test
    public void testProcessFile() throws IOException {
        processor.write_file("Hello, 123 World!");
        String processed = processor.process_file();
        assertEquals("HelloWorld", processed);
        assertEquals("HelloWorld", processor.read_file());
    }

    @Test
    public void testReadFileAsJson() throws IOException {
        String jsonContent = "{\"name\":\"Alice\",\"age\":30}";
        processor.write_file(jsonContent);

        Object result = processor.read_file_as_json();
        assertTrue(result instanceof Map);
        Map<?, ?> map = (Map<?, ?>) result;
        assertEquals("Alice", map.get("name"));
        assertEquals(30, map.get("age"));
    }
}
