package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.FileOutputStream; import
java.io.IOException; import java.nio.charset.StandardCharsets; import
java.nio.file.Files; import java.nio.file.Path; import java.util.LinkedHashMap;
import java.util.Map; import java.util.zip.ZipEntry; import
java.util.zip.ZipFile; import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class GeneratedTest_CE094 {

@Rule
public TemporaryFolder tempFolder = new TemporaryFolder();

private File createTestZip(String zipName, Map<String, String> entries) throws IOException {
    File zipFile = tempFolder.newFile(zipName);
    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            ZipEntry zipEntry = new ZipEntry(entry.getKey());
            zos.putNextEntry(zipEntry);
            if (entry.getValue() != null) {
                zos.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
            }
            zos.closeEntry();
        }
    }
    return zipFile;
}

@Test
public void testExtractAllSuccess() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("test_folder/", null);
    entries.put("test_folder/nested.txt", "Nested Content");
    entries.put("root.txt", "Root Content");

    File zipFile = createTestZip("archive.zip", entries);
    File destDir = new File(tempFolder.getRoot(), "extracted_all");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractAll(destDir.getAbsolutePath());

    assertTrue("extractAll should return true on valid zip extraction", success);
    assertTrue("Destination folder should be created", destDir.exists() && destDir.isDirectory());

    Path rootFilePath = destDir.toPath().resolve("root.txt");
    assertTrue("root.txt should exist", Files.exists(rootFilePath));
    assertEquals("Root Content", Files.readString(rootFilePath, StandardCharsets.UTF_8));

    Path nestedFilePath = destDir.toPath().resolve("test_folder/nested.txt");
    assertTrue("nested.txt should exist", Files.exists(nestedFilePath));
    assertEquals("Nested Content", Files.readString(nestedFilePath, StandardCharsets.UTF_8));
}

@Test
public void testExtractSingleFileSuccess() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("file1.txt", "First File");
    entries.put("deep/sub/file2.txt", "Deep File");

    File zipFile = createTestZip("single.zip", entries);
    File destDir = new File(tempFolder.getRoot(), "extracted_single");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());

    boolean successNested = processor.extractFile("deep/sub/file2.txt", destDir.getAbsolutePath());
    assertTrue("extractFile should succeed for nested file and auto-create parent dirs", successNested);

    Path extractedPath = destDir.toPath().resolve("deep/sub/file2.txt");
    assertTrue("Extracted nested file should exist", Files.exists(extractedPath));
    assertEquals("Deep File", Files.readString(extractedPath, StandardCharsets.UTF_8));

    Path unextractedPath = destDir.toPath().resolve("file1.txt");
    assertFalse("file1.txt should not have been extracted", Files.exists(unextractedPath));
}

@Test
public void testExtractFileWhenFileNotFoundInZip() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("existing.txt", "Existing");

    File zipFile = createTestZip("find_test.zip", entries);
    File destDir = tempFolder.newFolder("dest_missing_file");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("missing.txt", destDir.getAbsolutePath());

    assertFalse("extractFile should return false when file does not exist in zip", success);
    assertFalse("missing.txt should not exist in output dir", Files.exists(destDir.toPath().resolve("missing.txt")));
}

@Test
public void testOperationsOnNonExistentZip() {
    File nonExistentZip = new File(tempFolder.getRoot(), "ghost.zip");
    File destDir = new File(tempFolder.getRoot(), "dest_ghost");

    ZipFileProcessor processor = new ZipFileProcessor(nonExistentZip.getAbsolutePath());

    assertNull("readZipFile should return null for non-existent zip file", processor.readZipFile());
    assertFalse("extractAll should return false for non-existent zip file", processor.extractAll(destDir.getAbsolutePath()));
    assertFalse("extractFile should return false for non-existent zip file", processor.extractFile("any.txt", destDir.getAbsolutePath()));
}

@Test
public void testReadZipFileInvalidFormat() throws IOException {
    File corruptedZip = tempFolder.newFile("corrupted.zip");
    Files.writeString(corruptedZip.toPath(), "This is not a zip file", StandardCharsets.UTF_8);

    ZipFileProcessor processor = new ZipFileProcessor(corruptedZip.getAbsolutePath());
    ZipFile zipFile = processor.readZipFile();
    assertNull("readZipFile should return null for a corrupted/invalid zip file", zipFile);

    File destDir = tempFolder.newFolder("dest_invalid");
    assertFalse("extractAll should return false for invalid zip file", processor.extractAll(destDir.getAbsolutePath()));
    assertFalse("extractFile should return false for invalid zip file", processor.extractFile("any.txt", destDir.getAbsolutePath()));
}

}