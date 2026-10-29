package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE094 {

private File zipFile;
private Path outputDir;

@Before
public void setUp() throws IOException {
    zipFile = File.createTempFile("test_archive_", ".zip");
    outputDir = Files.createTempDirectory("test_extract_");

    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
        ZipEntry file1 = new ZipEntry("file1.txt");
        zos.putNextEntry(file1);
        zos.write("Content of file 1".getBytes());
        zos.closeEntry();

        ZipEntry file2 = new ZipEntry("subdir/file2.txt");
        zos.putNextEntry(file2);
        zos.write("Content of file 2".getBytes());
        zos.closeEntry();
    }
}

@After
public void tearDown() throws IOException {
    if (zipFile != null && zipFile.exists()) {
        zipFile.delete();
    }
    if (outputDir != null && Files.exists(outputDir)) {
        Files.walk(outputDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}

@Test
public void testExtractAll() throws IOException {
    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractAll(outputDir.toString());

    assertTrue(success);
    Path extractedFile1 = outputDir.resolve("file1.txt");
    Path extractedFile2 = outputDir.resolve("subdir/file2.txt");

    assertTrue(Files.exists(extractedFile1));
    assertTrue(Files.exists(extractedFile2));
    assertEquals("Content of file 1", Files.readString(extractedFile1));
    assertEquals("Content of file 2", Files.readString(extractedFile2));
}

@Test
public void testExtractSingleFile() throws IOException {
    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("file1.txt", outputDir.toString());

    assertTrue(success);
    Path extractedFile1 = outputDir.resolve("file1.txt");
    assertTrue(Files.exists(extractedFile1));
    assertEquals("Content of file 1", Files.readString(extractedFile1));
}

@Test
public void testExtractNonExistentFileReturnsFalse() {
    ZipFileProcessor processor = new ZipFileProcessor(zipFile.getAbsolutePath());
    boolean success = processor.extractFile("ghost.txt", outputDir.toString());
    assertFalse(success);
}

@Test
public void testNonExistentZipFileReturnsFalse() {
    ZipFileProcessor processor = new ZipFileProcessor("non_existent_zip_file.zip");
    assertFalse(processor.extractAll(outputDir.toString()));
    assertFalse(processor.extractFile("file1.txt", outputDir.toString()));
}

}
