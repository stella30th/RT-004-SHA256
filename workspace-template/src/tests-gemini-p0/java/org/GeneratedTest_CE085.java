package org.example;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE085 {

@Test
public void testFullUrlParsing() {
    URLHandler handler = new URLHandler("https://example.com/api/v1/resource?id=123&type=item#summary");

    assertEquals("https", handler.getScheme());
    assertEquals("example.com", handler.getHost());
    assertEquals("/api/v1/resource?id=123&type=item#summary", handler.getPath());

    Map<String, String> params = handler.getQueryParams();
    assertNotNull(params);
    assertEquals("123", params.get("id"));
    assertEquals("item", params.get("type"));

    assertEquals("summary", handler.getFragment());
}

@Test
public void testUrlWithoutScheme() {
    URLHandler handler = new URLHandler("just-a-plain-string");
    assertNull(handler.getScheme());
    assertNull(handler.getHost());
    assertNull(handler.getPath());
}

@Test
public void testUrlWithoutQueryOrFragment() {
    URLHandler handler = new URLHandler("http://localhost:8080");
    assertEquals("http", handler.getScheme());
    assertEquals("localhost:8080", handler.getHost());
    assertNull(handler.getQueryParams());
    assertNull(handler.getFragment());
}

@Test
public void testUrlWithPathOnly() {
    URLHandler handler = new URLHandler("ftp://fileserver/downloads/doc.pdf");
    assertEquals("ftp", handler.getScheme());
    assertEquals("fileserver", handler.getHost());
    assertEquals("/downloads/doc.pdf", handler.getPath());
}

}
