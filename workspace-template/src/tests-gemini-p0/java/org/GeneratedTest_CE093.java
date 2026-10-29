package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE093 {

private File tempXml;
private File outputXml;

@Before
public void setUp() throws IOException {
    tempXml = File.createTempFile("test_doc_", ".xml");
    outputXml = File.createTempFile("test_out_", ".xml");

    try (FileWriter writer = new FileWriter(tempXml)) {
        writer.write("<root><item>apple</item><item>banana</item></root>");
    }
}

@After
public void tearDown() {
    if (tempXml != null && tempXml.exists()) {
        tempXml.delete();
    }
    if (outputXml != null && outputXml.exists()) {
        outputXml.delete();
    }
}

@Test
public void testReadXml() {
    XMLProcessor processor = new XMLProcessor(tempXml.getAbsolutePath());
    Document doc = processor.readXml();

    assertNotNull(doc);
    assertEquals("root", doc.getDocumentElement().getNodeName());
    assertEquals(doc, processor.getDocument());
}

@Test
public void testFindElement() {
    XMLProcessor processor = new XMLProcessor(tempXml.getAbsolutePath());
    processor.readXml();

    List<Element> items = processor.findElement("item");
    assertEquals(2, items.size());
    assertEquals("apple", items.get(0).getTextContent());
    assertEquals("banana", items.get(1).getTextContent());

    assertTrue(processor.findElement("nonExistent").isEmpty());
}

@Test
public void testProcessXmlDataConvertsToUpper() {
    XMLProcessor processor = new XMLProcessor(tempXml.getAbsolutePath());
    processor.readXml();

    boolean processed = processor.processXmlData(outputXml.getAbsolutePath());
    assertTrue(processed);

    XMLProcessor checkProcessor = new XMLProcessor(outputXml.getAbsolutePath());
    checkProcessor.readXml();
    List<Element> items = checkProcessor.findElement("item");
    assertEquals("APPLE", items.get(0).getTextContent());
    assertEquals("BANANA", items.get(1).getTextContent());
}

@Test
public void testWriteXmlWithoutDocumentReturnsFalse() {
    XMLProcessor processor = new XMLProcessor("empty.xml");
    assertFalse(processor.writeXml(outputXml.getAbsolutePath()));
    assertFalse(processor.writeXml(""));
}

}
