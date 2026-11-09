package org.example;

import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

public class GeneratedTest_CE085 {
    @Test
    public void extractsSchemeHostAndPath() {
        URLHandler handler = new URLHandler("https://example.com/a/b");
        assertEquals("https", handler.getScheme());
        assertEquals("example.com", handler.getHost());
        assertEquals("/a/b", handler.getPath());
    }

    @Test
    public void missingSchemeDelimiterGivesNullComponents() {
        URLHandler handler = new URLHandler("example.com/path");
        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertNull(handler.getPath());
    }

    @Test
    public void schemeAndHostWithoutSlashHaveNoPath() {
        URLHandler handler = new URLHandler("ftp://server.example");
        assertEquals("ftp", handler.getScheme());
        assertEquals("server.example", handler.getHost());
        assertNull(handler.getPath());
    }

    @Test
    public void slashAndSuffixArePartOfPath() {
        URLHandler handler = new URLHandler("http://host.test/?x=1#section");
        assertEquals("host.test", handler.getHost());
        assertEquals("/?x=1#section", handler.getPath());
    }

    @Test
    public void noQueryOrFragmentReturnsNull() {
        URLHandler handler = new URLHandler("https://host.test/path");
        assertNull(handler.getQueryParams());
        assertNull(handler.getFragment());
    }

    @Test
    public void queryValuesAreParsedAndFragmentExcluded() {
        URLHandler handler = new URLHandler("https://host.test/path?a=1&b=two#end");
        Map<String, String> params = handler.getQueryParams();
        assertNotNull(params);
        assertEquals(2, params.size());
        assertEquals("1", params.get("a"));
        assertEquals("two", params.get("b"));
        assertEquals("end", handler.getFragment());
    }

    @Test
    public void emptyQueryReturnsEmptyMap() {
        URLHandler handler = new URLHandler("http://host.test/?#frag");
        assertNotNull(handler.getQueryParams());
        assertTrue(handler.getQueryParams().isEmpty());
        assertEquals("frag", handler.getFragment());
    }

    @Test
    public void duplicateQueryKeysUseLastValue() {
        URLHandler handler = new URLHandler("http://host.test/?a=first&a=second");
        Map<String, String> params = handler.getQueryParams();
        assertEquals(1, params.size());
        assertEquals("second", params.get("a"));
    }

    @Test
    public void malformedQueryPairsAreIgnored() {
        URLHandler handler = new URLHandler("http://host.test/?ok=yes&invalid&extra=a=b");
        Map<String, String> params = handler.getQueryParams();
        assertEquals(1, params.size());
        assertEquals("yes", params.get("ok"));
    }

    @Test
    public void emptyFragmentIsReturnedAsEmptyString() {
        URLHandler handler = new URLHandler("http://host.test/#");
        assertEquals("", handler.getFragment());
    }

    @Test
    public void queryAndFragmentCanExistWithoutScheme() {
        URLHandler handler = new URLHandler("relative/path?mode=fast#part");
        assertNull(handler.getScheme());
        assertNull(handler.getHost());
        assertEquals("fast", handler.getQueryParams().get("mode"));
        assertEquals("part", handler.getFragment());
    }
}
