// BEGIN GeneratedTest_CE085
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class GeneratedTest_CE085 {

    @Test
    public void testBasicUrlComponents() {
        URLHandler handler = new URLHandler("https://example.com/path/to/page");

        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/path/to/page", handler.getPath());
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void testUrlWithoutPath() {
        URLHandler handler = new URLHandler("http://example.com");

        assertEquals("http", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertNull(handler.getPath());
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void testUrlWithRootPath() {
        URLHandler handler = new URLHandler("https://example.com/");

        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/", handler.getPath());
    }

    @Test
    public void testUrlWithPort() {
        URLHandler handler = new URLHandler("http://localhost:8080/api");

        assertEquals("http", handler.getScheme());
        assertEquals("localhost:8080", handler.getHost());
        assertEquals("/api", handler.getPath());
    }

    @Test
    public void testUrlWithDifferentScheme() {
        URLHandler handler = new URLHandler("ftp://files.example.com/download");

        assertEquals("ftp", handler.getScheme());
        assertEquals("files.example.com", handler.getHost());
        assertEquals("/download", handler.getPath());
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
    public void testEmptyUrl() {
        URLHandler handler = new URLHandler("");

        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertNull(handler.getPath());
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void testUrlWithEmptySchemeAndHost() {
        URLHandler handler = new URLHandler(":///path");

        assertEquals("", handler.getScheme());
        assertEquals("", handler.getHost());
        assertEquals("/path", handler.getPath());
    }

    @Test
    public void testUrlWithQueryParameters() {
        URLHandler handler = new URLHandler(
                "https://example.com/search?name=John&age=25");

        Map<String, String> params = handler.getQueryParams();

        assertNotNull(params);
        assertEquals(2, params.size());
        assertEquals("John", params.get("name"));
        assertEquals("25", params.get("age"));
        assertEquals("/search?name=John&age=25", handler.getPath());
    }

    @Test
    public void testUrlWithSingleQueryParameter() {
        URLHandler handler = new URLHandler(
                "https://example.com/?q=test");

        Map<String, String> params = handler.getQueryParams();

        assertNotNull(params);
        assertEquals(1, params.size());
        assertEquals("test", params.get("q"));
    }

    @Test
    public void testUrlWithEmptyQuery() {
        URLHandler handler = new URLHandler(
                "https://example.com/path?");

        Map<String, String> params = handler.getQueryParams();

        assertNotNull(params);
        assertTrue(params.isEmpty());
        assertEquals("/path?", handler.getPath());
    }

    @Test
    public void testUrlWithQueryWithoutEquals() {
        URLHandler handler = new URLHandler(
                "https://example.com/?name&age=25");

        Map<String, String> params = handler.getQueryParams();

        assertNotNull(params);
        assertEquals(1, params.size());
        assertFalse(params.containsKey("name"));
        assertEquals("25", params.get("age"));
    }

    @Test
    public void testQueryParameterWithMultipleEquals() {
        URLHandler handler = new URLHandler(
                "https://example.com/?a=1=2&b=3");

        Map<String, String> params = handler.getQueryParams();

        assertEquals(1, params.size());
        assertFalse(params.containsKey("a"));
        assertEquals("3", params.get("b"));
    }

    @Test
    public void testDuplicateQueryParameters() {
        URLHandler handler = new URLHandler(
                "https://example.com/?id=1&id=2");

        Map<String, String> params = handler.getQueryParams();

        assertEquals(1, params.size());
        assertEquals("2", params.get("id"));
    }

    @Test
    public void testQueryParametersWithEmptyValues() {
        URLHandler handler = new URLHandler(
                "https://example.com/?a=&b=2");

        Map<String, String> params = handler.getQueryParams();

        assertEquals(1, params.size());
        assertFalse(params.containsKey("a"));
        assertEquals("2", params.get("b"));
    }

    @Test
    public void testQueryParameterWithEmptyKey() {
        URLHandler handler = new URLHandler(
                "https://example.com/?=value");

        Map<String, String> params = handler.getQueryParams();

        assertEquals(1, params.size());
        assertEquals("value", params.get(""));
    }

    @Test
    public void testQueryParametersAreNotUrlDecoded() {
        URLHandler handler = new URLHandler(
                "https://example.com/?name=John%20Doe&city=New+York");

        Map<String, String> params = handler.getQueryParams();

        assertEquals("John%20Doe", params.get("name"));
        assertEquals("New+York", params.get("city"));
    }

    @Test
    public void testQueryParametersWithoutScheme() {
        URLHandler handler = new URLHandler(
                "/search?q=hello&page=2");

        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertNull(handler.getPath());

        Map<String, String> params = handler.getQueryParams();

        assertEquals("hello", params.get("q"));
        assertEquals("2", params.get("page"));
    }

    @Test
    public void testUrlWithFragment() {
        URLHandler handler = new URLHandler(
                "https://example.com/page#section1");

        assertEquals("section1", handler.getFragment());
        assertEquals("/page#section1", handler.getPath());
        assertNull(handler.getQueryParams());
    }

    @Test
    public void testUrlWithEmptyFragment() {
        URLHandler handler = new URLHandler(
                "https://example.com/page#");

        assertEquals("", handler.getFragment());
        assertEquals("/page#", handler.getPath());
    }

    @Test
    public void testFragmentWithMultipleHashCharacters() {
        URLHandler handler = new URLHandler(
                "https://example.com/page#first#second");

        assertEquals("first#second", handler.getFragment());
    }

    @Test
    public void testFragmentWithoutScheme() {
        URLHandler handler = new URLHandler("page#section");

        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertNull(handler.getPath());
        assertEquals("section", handler.getFragment());
    }

    @Test
    public void testUrlWithQueryAndFragment() {
        URLHandler handler = new URLHandler(
                "https://example.com/search?q=test&page=2#results");

        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/search?q=test&page=2#results", handler.getPath());

        Map<String, String> params = handler.getQueryParams();

        assertEquals(2, params.size());
        assertEquals("test", params.get("q"));
        assertEquals("2", params.get("page"));
        assertEquals("results", handler.getFragment());
    }

    @Test
    public void testQueryWithEmptyFragment() {
        URLHandler handler = new URLHandler(
                "https://example.com/?a=1#");

        assertEquals("1", handler.getQueryParams().get("a"));
        assertEquals("", handler.getFragment());
    }

    @Test
    public void testUrlWithQueryButNoSlashAfterHost() {
        URLHandler handler = new URLHandler(
                "https://example.com?key=value");

        assertEquals("https", handler.getScheme());
        assertEquals("example.com?key=value", handler.getHost());
        assertNull(handler.getPath());
        assertEquals("value", handler.getQueryParams().get("key"));
    }

    @Test
    public void testUrlWithFragmentButNoSlashAfterHost() {
        URLHandler handler = new URLHandler(
                "https://example.com#section");

        assertEquals("example.com#section", handler.getHost());
        assertNull(handler.getPath());
        assertEquals("section", handler.getFragment());
    }

    @Test
    public void testPathIncludesQueryAndFragment() {
        URLHandler handler = new URLHandler(
                "https://example.com/a/b?x=1#top");

        assertEquals("/a/b?x=1#top", handler.getPath());
    }

    @Test
    public void testQueryParametersWithExtraAmpersands() {
        URLHandler handler = new URLHandler(
                "https://example.com/?a=1&&b=2&");

        Map<String, String> params = handler.getQueryParams();

        assertEquals(2, params.size());
        assertEquals("1", params.get("a"));
        assertEquals("2", params.get("b"));
    }

    @Test
    public void testRepeatedGetQueryParamsReturnsIndependentMaps() {
        URLHandler handler = new URLHandler(
                "https://example.com/?a=1");

        Map<String, String> first = handler.getQueryParams();
        first.put("a", "changed");

        Map<String, String> second = handler.getQueryParams();

        assertEquals("1", second.get("a"));
        assertEquals(1, second.size());
    }

    @Test
    public void testSchemeAndHostPreserveCase() {
        URLHandler handler = new URLHandler(
                "HTTPS://Example.COM/Path");

        assertEquals("HTTPS", handler.getScheme());
        assertEquals("Example.COM", handler.getHost());
        assertEquals("/Path", handler.getPath());
    }

    @Test
    public void testUrlWithMultipleSchemeDelimiters() {
        URLHandler handler = new URLHandler(
                "http://example.com/path://other");

        assertEquals("http", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/path://other", handler.getPath());
    }

    @Test
    public void testNullUrlThrowsNullPointerException() {
        URLHandler handler = new URLHandler(null);

        try {
            handler.getScheme();
            fail("Expected NullPointerException");
        } catch (NullPointerException expected) {
            assertNotNull(expected);
        }
    }

    @Test
    public void testQueryParsingWhenHashPrecedesQuestionMark() {
        URLHandler handler = new URLHandler(
                "https://example.com/page#section?x=1");

        try {
            handler.getQueryParams();
            fail("Expected StringIndexOutOfBoundsException");
        } catch (StringIndexOutOfBoundsException expected) {
            assertEquals("section?x=1", handler.getFragment());
        }
    }
}
// END GeneratedTest_CE085