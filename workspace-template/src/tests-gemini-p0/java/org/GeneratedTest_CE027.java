package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE027 {

private File tempCsv;
private File processCsv;

@Before
public void setUp() throws IOException {
    tempCsv = File.createTempFile("test_data_", ".csv");
    processCsv = new File(tempCsv.getAbsolutePath().split("\\.")[0] + "_process.csv");
}

@After
public void tearDown() {
    if (tempCsv != null && tempCsv.exists()) {
        tempCsv.delete();
    }
    if (processCsv != null && processCsv.exists()) {
        processCsv.delete();
    }
}

@Test
public void testWriteAndReadCSVWithTitles() throws IOException {
    CSVProcessor processor = new CSVProcessor();
    List<String> titles = Arrays.asList("Name", "Age", "City");
    List<List<String>> data = new ArrayList<>();
    data.add(Arrays.asList("Alice", "30", "Paris"));
    data.add(Arrays.asList("Bob", "25", "London"));

    int writeResult = processor.writeCSV(titles, data, tempCsv.getAbsolutePath());
    assertEquals(1, writeResult);

    List<String> readTitles = new ArrayList<>();
    List<List<String>> readData = new ArrayList<>();
    processor.readCSV(tempCsv.getAbsolutePath(), readTitles, readData);

    assertEquals(titles, readTitles);
    assertEquals(2, readData.size());
    assertEquals("Alice", readData.get(0).get(0));
}

@Test
public void testWriteCSVDataOnly() throws IOException {
    CSVProcessor processor = new CSVProcessor();
    List<List<String>> data = new ArrayList<>();
    data.add(Arrays.asList("1", "2"));
    data.add(Arrays.asList("3", "4"));

    int writeResult = processor.writeCSV(data, tempCsv.getAbsolutePath());
    assertEquals(1, writeResult);

    List<String> titles = new ArrayList<>();
    List<List<String>> readData = new ArrayList<>();
    processor.readCSV(tempCsv.getAbsolutePath(), titles, readData);

    // First row read as title
    assertEquals(Arrays.asList("1", "2"), titles);
    assertEquals(1, readData.size());
    assertEquals(Arrays.asList("3", "4"), readData.get(0));
}

@Test
public void testProcessCSVDataConvertsColumnToUpper() throws IOException {
    CSVProcessor processor = new CSVProcessor();
    List<String> titles = Arrays.asList("Col1", "Col2");
    List<List<String>> data = new ArrayList<>();
    data.add(Arrays.asList("apple", "banana"));
    data.add(Arrays.asList("carrot", "date"));

    processor.writeCSV(titles, data, tempCsv.getAbsolutePath());

    int result = processor.processCSVData(1, tempCsv.getAbsolutePath());
    assertEquals(1, result);

    List<String> procTitles = new ArrayList<>();
    List<List<String>> procData = new ArrayList<>();
    processor.readCSV(processCsv.getAbsolutePath(), procTitles, procData);

    assertEquals(titles, procTitles);
    assertEquals(1, procData.size());
    assertEquals(Arrays.asList("BANANA", "DATE"), procData.get(0));
}

@Test
public void testWriteCSVFailureOnInvalidPath() {
    CSVProcessor processor = new CSVProcessor();
    int result = processor.writeCSV(Arrays.asList("col"), new ArrayList<>(), "/invalid_dir/bad_file.csv");
    assertEquals(0, result);
}

}
