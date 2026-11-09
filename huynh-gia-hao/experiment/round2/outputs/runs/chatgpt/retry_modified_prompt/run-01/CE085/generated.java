
// BEGIN GeneratedTest_CE085
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class GeneratedTest_CE085 {

    @Test
    public void testCompleteUrl() {
        URLHandler handler = new URLHandler(
            "https://example.com/path?key=value&foo=bar#section"
        );

        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/path?key=value&foo=bar#section", handler.getPath());
        assertEquals("section", handler.getFragment());

        Map<String, String> params = handler.getQueryParams();
        assertNotNull(params);
        assertEquals(2, params.size());
        assertEquals("value", params.get("key"));
        assertEquals("bar", params.get("foo"));
    }

    @Test
    public void testUrlWithoutOptionalParts() {
        URLHandler handler = new URLHandler("http://example.com");

        assertEquals("http", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertNull(handler.getPath());
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void testUrlWithoutScheme() {
        URLHandler handler = new URLHandler("example.com/path");

        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertNull(handler.getPath());
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void testEmptyQueryAndFragment() {
        URLHandler handler = new URLHandler(
            "https://example.com/?#"
        );

        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/?#", handler.getPath());
        assertEquals("", handler.getFragment());

        Map<String, String> params = handler.getQueryParams();
        assertNotNull(params);
        assertTrue(params.isEmpty());
    }
}
// END GeneratedTest_CE085
