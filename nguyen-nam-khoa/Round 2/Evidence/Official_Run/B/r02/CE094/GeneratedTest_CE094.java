package org.example;

import org.junit.Rule; import org.junit.Test; import
org.junit.rules.TemporaryFolder;

import java.io.File; import java.io.FileOutputStream; import
java.io.IOException; import java.nio.charset.StandardCharsets; import
java.nio.file.Files; import java.nio.file.Path; import java.util.zip.ZipEntry;
import java.util.zip.ZipFile; import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class GeneratedTest_CE094 {

@Rule
public TemporaryFolder temporaryFolder = new TemporaryFolder();

private File createTestZipFile() throws IOException {
    File zipFile = temporaryFolder.newFile("archive.zip");
    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
        ZipEntry dirEntry = new ZipEntry("nested/");
        zos.putNextEntry(dirEntry);
        zos.closeEntry();

        ZipEntry file1 = new ZipEntry("hello.txt");
        zos.putNextEntry(file1);
        zos.write("Hello World".getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();

        ZipEntry file2 = new ZipEntry("nested/sub.txt");
        zos.putNextEntry(file2);
        zos.write("Sub Content".getBytes(StandardCharsets.UTF_8));
        zos.closeEntry();
    }
    return zipFile;
}

@Test
public void testReadZipFile_Success() throws IOException {
    File zipFile = createTestZipFile();
    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());

    ZipFile openedZip = processor.readZipFile();
    assertNotNull(openedZip);
    assertEquals(3, openedZip.size());
    openedZip.close();
}

@Test
public void testReadZipFile_NonExistent() {
    File nonExistent = new File(temporaryFolder.getRoot(), "missing.zip");
    ZipFileProcessor processor = new ZipFileProcessor(nonExistent.getAbsolutePath());

    ZipFile result = processor.readZipFile();
    assertNull(result);
}

@Test
public void testReadZipFile_CorruptFile() throws IOException {
    File corrupt = temporaryFolder.newFile("corrupt.zip");
    Files.writeString(corrupt.toPath(), "This is not a zip file", StandardCharsets.UTF_8);

    ZipFileProcessor processor = new ZipFileProcessor(corrupt.getAbsolutePath());
    ZipFile result = processor.readZipFile();
    assertNull(result);
}

@Test
public void testExtractAll_Success() throws IOException {
    File zipFile = createTestZipFile();
    File outputDir = new File(temporaryFolder.getRoot(), "extracted_all");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean result = processor.extractAll(outputDir.getAbsolutePath());

    assertTrue(result);

    Path helloPath = outputDir.toPath().resolve("hello.txt");
    Path subPath = outputDir.toPath().resolve("nested").resolve("sub.txt");

    assertTrue(Files.exists(helloPath));
    assertEquals("Hello World", Files.readString(helloPath, StandardCharsets.UTF_8));

    assertTrue(Files.exists(subPath));
    assertEquals("Sub Content", Files.readString(subPath, StandardCharsets.UTF_8));
}

@Test
public void testExtractAll_NonExistentZip() {
    File nonExistent = new File(temporaryFolder.getRoot(), "ghost.zip");
    File outputDir = new File(temporaryFolder.getRoot(), "ghost_out");

    ZipFileProcessor processor = new ZipFileProcessor(nonExistent.getAbsolutePath());
    boolean result = processor.extractAll(outputDir.getAbsolutePath());

    assertFalse(result);
}

@Test
public void testExtractFile_Success() throws IOException {
    File zipFile = createTestZipFile();
    File outputDir = new File(temporaryFolder.getRoot(), "extracted_single");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean result = processor.extractFile("nested/sub.txt", outputDir.getAbsolutePath());

    assertTrue(result);

    Path extractedPath = outputDir.toPath().resolve("nested").resolve("sub.txt");
    assertTrue(Files.exists(extractedPath));
    assertEquals("Sub Content", Files.readString(extractedPath, StandardCharsets.UTF_8));
}

@Test
public void testExtractFile_FileNotFoundInZip() throws IOException {
    File zipFile = createTestZipFile();
    File outputDir = new File(temporaryFolder.getRoot(), "extracted_missing");

    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean result = processor.extractFile("not_in_zip.txt", outputDir.getAbsolutePath());

    assertFalse(result);
    assertFalse(Files.exists(outputDir.toPath().resolve("not_in_zip.txt")));
}

@Test
public void testExtractFile_NonExistentZip() {
    File nonExistent = new File(temporaryFolder.getRoot(), "missing_archive.zip");
    File outputDir = new File(temporaryFolder.getRoot(), "out");

    ZipFileProcessor processor = new ZipFileProcessor(nonExistent.getAbsolutePath());
    boolean result = processor.extractFile("any.txt", outputDir.getAbsolutePath());

    assertFalse(result);
}

}