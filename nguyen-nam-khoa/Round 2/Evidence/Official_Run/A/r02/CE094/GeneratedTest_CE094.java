package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.FileOutputStream; import
java.io.IOException; import java.nio.charset.StandardCharsets; import
java.nio.file.Files; import java.nio.file.Path; import java.util.LinkedHashMap;
import java.util.Map; import java.util.zip.ZipEntry; import
java.util.zip.ZipFile; import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertEquals; import static
org.junit.Assert.assertFalse; import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull; import static
org.junit.Assert.assertTrue;

public class GeneratedTest_CE094 {

@Rule
public TemporaryFolder tempFolder = new TemporaryFolder();

private File createZipFile(String zipName, Map<String, String> entries) throws IOException {
    File zipFile = tempFolder.newFile(zipName);
    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            ZipEntry ze = new ZipEntry(entry.getKey());
            zos.putNextEntry(ze);
            if (!entry.getKey().endsWith("/")) {
                zos.write(entry.getValue().getBytes(StandardCharsets.UTF_8));
            }
            zos.closeEntry();
        }
    }
    return zipFile;
}

@Test
public void testReadZipFile_ValidAndInvalid() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("test.txt", "Sample content");
    File zipFile = createZipFile("valid.zip", entries);

    ZipFileProcessor validProcessor = new ZipFileProcessor(zipFile.getAbsolutePath());
    ZipFile openedZip = validProcessor.readZipFile();
    assertNotNull(openedZip);
    openedZip.close();

    File nonExistentFile = new File(tempFolder.getRoot(), "missing.zip");
    ZipFileProcessor missingProcessor = new ZipFileProcessor(nonExistentFile.getAbsolutePath());
    assertNull(missingProcessor.readZipFile());

    File corruptFile = tempFolder.newFile("corrupt.zip");
    Files.write(corruptFile.toPath(), "Not a zip content".getBytes(StandardCharsets.UTF_8));
    ZipFileProcessor corruptProcessor = new ZipFileProcessor(corruptFile.getAbsolutePath());
    assertNull(corruptProcessor.readZipFile());
}

@Test
public void testExtractAll_Success() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("file1.txt", "First file content");
    entries.put("subfolder/", "");
    entries.put("subfolder/file2.txt", "Second file content");

    File zipFile = createZipFile("archive_all.zip", entries);
    File outputDir = new File(tempFolder.getRoot(), "out_all");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractAll(outputDir.getAbsolutePath());

    assertTrue(success);

    Path file1Path = outputDir.toPath().resolve("file1.txt");
    Path file2Path = outputDir.toPath().resolve("subfolder/file2.txt");

    assertTrue(Files.exists(file1Path));
    assertTrue(Files.exists(file2Path));

    String content1 = new String(Files.readAllBytes(file1Path), StandardCharsets.UTF_8);
    String content2 = new String(Files.readAllBytes(file2Path), StandardCharsets.UTF_8);

    assertEquals("First file content", content1);
    assertEquals("Second file content", content2);
}

@Test
public void testExtractAll_NonExistentZip() {
    File nonExistentZip = new File(tempFolder.getRoot(), "does_not_exist.zip");
    File outputDir = new File(tempFolder.getRoot(), "out_missing");

    ZipFileProcessor processor = new ZipFileProcessor(nonExistentZip.getAbsolutePath());
    boolean success = processor.extractAll(outputDir.getAbsolutePath());

    assertFalse(success);
    assertFalse(outputDir.exists());
}

@Test
public void testExtractFile_Success() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("target.txt", "Target payload data");
    entries.put("ignored.txt", "Ignore me");

    File zipFile = createZipFile("single.zip", entries);
    File outputDir = new File(tempFolder.getRoot(), "out_single");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("target.txt", outputDir.getAbsolutePath());

    assertTrue(success);

    Path extractedPath = outputDir.toPath().resolve("target.txt");
    Path ignoredPath = outputDir.toPath().resolve("ignored.txt");

    assertTrue(Files.exists(extractedPath));
    assertFalse(Files.exists(ignoredPath));

    String content = new String(Files.readAllBytes(extractedPath), StandardCharsets.UTF_8);
    assertEquals("Target payload data", content);
}

@Test
public void testExtractFile_NestedPathCreatesParentDirectories() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("deep/nested/path/config.json", "{\"key\":\"value\"}");

    File zipFile = createZipFile("nested.zip", entries);
    File outputDir = new File(tempFolder.getRoot(), "out_nested");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("deep/nested/path/config.json", outputDir.getAbsolutePath());

    assertTrue(success);

    Path extractedPath = outputDir.toPath().resolve("deep/nested/path/config.json");
    assertTrue(Files.exists(extractedPath));

    String content = new String(Files.readAllBytes(extractedPath), StandardCharsets.UTF_8);
    assertEquals("{\"key\":\"value\"}", content);
}

@Test
public void testExtractFile_FileNotFoundInZip() throws IOException {
    Map<String, String> entries = new LinkedHashMap<>();
    entries.put("present.txt", "I exist");

    File zipFile = createZipFile("lookup.zip", entries);
    File outputDir = new File(tempFolder.getRoot(), "out_lookup");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("not_present.txt", outputDir.getAbsolutePath());

    assertFalse(success);
    assertFalse(Files.exists(outputDir.toPath().resolve("not_present.txt")));
}

}