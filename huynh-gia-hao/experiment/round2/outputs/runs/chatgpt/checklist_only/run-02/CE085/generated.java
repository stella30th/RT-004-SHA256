// BEGIN GeneratedTest_CE085
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE085 {

    @Test public void extractsSchemeHostAndPath() {
        URLHandler h = new URLHandler("https://example.com:8080/a/b");
        assertEquals("https", h.getScheme());
        assertEquals("example.com:8080", h.getHost());
        assertEquals("/a/b", h.getPath());
        assertNull(h.getFragment());
        assertNull(h.getQueryParams());
    }
    @Test public void absentSchemeAndPathReturnNull() {
        URLHandler plain = new URLHandler("example.com/a");
        assertNull(plain.getScheme());
        assertNull(plain.getHost());
        assertNull(plain.getPath());
        URLHandler noSlash = new URLHandler("ftp://host");
        assertEquals("ftp", noSlash.getScheme());
        assertEquals("host", noSlash.getHost());
        assertNull(noSlash.getPath());
    }
    @Test public void queryAndFragmentParsing() {
        URLHandler h = new URLHandler("http://host/search?q=one&n=2#top");
        assertEquals("/search?q=one&n=2#top", h.getPath());
        assertEquals("top", h.getFragment());
        assertEquals("one", h.getQueryParams().get("q"));
        assertEquals("2", h.getQueryParams().get("n"));
    }
    @Test public void skipsMalformedQueryPairsAndKeepsLastDuplicate() {
        URLHandler h = new URLHandler("https://host/?x=1&x=2&broken&y=1=2&z=");
        assertEquals(1, h.getQueryParams().size());
        assertEquals("2", h.getQueryParams().get("x"));
    }
    @Test public void emptyQueryAndFragmentArePresent() {
        URLHandler h = new URLHandler("https://host/path?#");
        assertNotNull(h.getQueryParams());
        assertTrue(h.getQueryParams().isEmpty());
        assertEquals("", h.getFragment());
    }
    @Test public void queryMapsAreFreshAndNotDecoded() {
        URLHandler h = new URLHandler("https://host/?a=John%20Doe");
        java.util.Map<String, String> first = h.getQueryParams();
        assertEquals("John%20Doe", first.get("a"));
        first.clear();
        assertEquals("John%20Doe", h.getQueryParams().get("a"));
    }
    @Test(expected = StringIndexOutOfBoundsException.class) public void fragmentBeforeQueryCausesInvalidSubstring() {
        new URLHandler("https://host/#fragment?q=1").getQueryParams();
    }

    @Test public void emptySchemeIsReturned() { URLHandler h=new URLHandler("://host/"); assertEquals("",h.getScheme()); assertEquals("host",h.getHost()); }
}
// END GeneratedTest_CE085
