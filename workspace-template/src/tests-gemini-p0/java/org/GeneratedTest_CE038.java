package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE038 {

private File tempXlsx;
private File processXlsx;

@Before
public void setUp() throws IOException {
    tempXlsx = File.createTempFile("test_excel_", ".xlsx");
    processXlsx = new File(tempXlsx.getAbsolutePath().replace(".xlsx", "_process.xlsx"));
}

@After
public void tearDown() {
    if (tempXlsx != null && tempXlsx.exists()) {
        tempXlsx.delete();
    }
    if (processXlsx != null && processXlsx.exists()) {
        processXlsx.delete();
    }
}

@Test
public void testWriteAndReadExcel() {
    ExcelProcessor processor = new ExcelProcessor();
    List<List<Object>> data = new ArrayList<>();
    data.add(Arrays.asList("Name", "Score"));
    data.add(Arrays.asList("Alice", 95));

    boolean writeSuccess = processor.writeExcel(data, tempXlsx.getAbsolutePath());
    assertTrue(writeSuccess);

    List<List<Object>> readData = processor.readExcel(tempXlsx.getAbsolutePath());
    assertNotNull(readData);
    assertEquals(2, readData.size());
    assertEquals("Alice", readData.get(1).get(0));
    assertEquals(95, readData.get(1).get(1));
}

@Test
public void testProcessExcelData() {
    ExcelProcessor processor = new ExcelProcessor();
    List<List<Object>> data = new ArrayList<>();
    data.add(Arrays.asList("first", "item"));
    data.add(Arrays.asList("second", "element"));

    processor.writeExcel(data, tempXlsx.getAbsolutePath());

    String resultPath = processor.processExcelData(1, tempXlsx.getAbsolutePath());
    assertNotNull(resultPath);

    List<List<Object>> processedData = processor.readExcel(resultPath);
    assertNotNull(processedData);
    assertEquals(2, processedData.size());
    // Column 1 was uppercase appended
    assertEquals("ITEM", processedData.get(0).get(2));
    assertEquals("ELEMENT", processedData.get(1).get(2));
}

@Test
public void testReadExcelNonExistentReturnsNull() {
    ExcelProcessor processor = new ExcelProcessor();
    List<List<Object>> data = processor.readExcel("non_existent_file_path_123.xlsx");
    assertNull(data);
}

@Test
public void testProcessExcelDataOutOfBoundsReturnsNull() {
    ExcelProcessor processor = new ExcelProcessor();
    List<List<Object>> data = new ArrayList<>();
    data.add(Arrays.asList("onlyOneColumn"));
    processor.writeExcel(data, tempXlsx.getAbsolutePath());

    String result = processor.processExcelData(5, tempXlsx.getAbsolutePath());
    assertNull(result);
}

}
