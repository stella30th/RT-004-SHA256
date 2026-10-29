package org.example;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class GeneratedTest_CE094 {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File zipFile;
    private File outputDir;

    @Before
    public void setUp() throws IOException {
        zipFile = tempFolder.newFile("test.zip");
        outputDir = tempFolder.newFolder("output");
    }

    private void createArchive(File archive, Map<String, String> entries) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archive))) {
            for (Map.Entry<String, String> entry : entries.entrySet()) {
                ZipEntry ze = new ZipEntry(entry.getKey());
                zos.putNextEntry(ze);
                zos.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
                zos.closeEntry();
            }
        }
    }

    @Test
    public void testExtractAllSuccess() throws IOException {
        Map<String, String> entries = new LinkedHashMap<>();
        entries.put("first.txt", "Content of first");
        entries.put("second.txt", "Content of second");
        createArchive(zipFile, entries);

        ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
        boolean success = processor.extractAll(outputDir.getAbsolutePath());

        assertTrue(success);
        Path firstPath = outputDir.toPath().resolve("first.txt");
        Path secondPath = outputDir.toPath().resolve("second.txt");

        assertTrue(Files.exists(firstPath));
        assertTrue(Files.exists(secondPath));
        assertEquals("Content of first", Files.readString(firstPath));
        assertEquals("Content of second", Files.readString(secondPath));
    }

    @Test
    public void testExtractFileSuccess() throws IOException {
        Map<String, String> entries = new LinkedHashMap<>();
        entries.put("file1.txt", "Target file content");
        entries.put("sub/file2.txt", "Subdir file content");
        createArchive(zipFile, entries);

        ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());

        boolean success = processor.extractFile("file1.txt", outputDir.getAbsolutePath());
        assertTrue(success);
        Path extracted1 = outputDir.toPath().resolve("file1.txt");
        assertTrue(Files.exists(extracted1));
        assertEquals("Target file content", Files.readString(extracted1));

        boolean successNested = processor.extractFile("sub/file2.txt", outputDir.getAbsolutePath());
        assertTrue(successNested);
        Path extracted2 = outputDir.toPath().resolve("sub/file2.txt");
        assertTrue(Files.exists(extracted2));
        assertEquals("Subdir file content", Files.readString(extracted2));
    }

    @Test
    public void testExtractNonexistentFile() throws IOException {
        Map<String, String> entries = new LinkedHashMap<>();
        entries.put("existing.txt", "Existing content");
        createArchive(zipFile, entries);

        ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
        boolean success = processor.extractFile("not_in_zip.txt", outputDir.getAbsolutePath());
        assertFalse(success);
    }

    @Test
    public void testNonexistentZipArchive() {
        File missingZip = new File(tempFolder.getRoot(), "missing.zip");
        ZipFileProcessor processor = new ZipFileProcessor(missingZip.getAbsolutePath());

        assertFalse(processor.extractAll(outputDir.getAbsolutePath()));
        assertFalse(processor.extractFile("any.txt", outputDir.getAbsolutePath()));
    }
}
