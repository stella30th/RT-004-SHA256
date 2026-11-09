package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class GeneratedTest_CE086 {

@Test
public void testFixPath() {
    assertEquals("path/to/resource", UrlPath.fixPath("/path/to/resource/"));
    assertEquals("clean", UrlPath.fixPath("///clean///"));
    assertEquals("", UrlPath.fixPath(""));
    assertEquals("", UrlPath.fixPath(null));
}

@Test
public void testAdd() {
    UrlPath urlPath = new UrlPath();
    urlPath.add("/segment1/");
    urlPath.add("segment2");

    assertEquals(Arrays.asList("segment1", "segment2"), urlPath.getSegments());
}

@Test
public void testParseWithEndTag() {
    UrlPath urlPath = new UrlPath();
    urlPath.parse("/api/v1/users/", "UTF-8");

    assertTrue(urlPath.isWithEndTag());
    assertEquals(Arrays.asList("api", "v1", "users"), urlPath.getSegments());
}

@Test
public void testParseWithoutEndTagAndWithUrlEncoding() {
    UrlPath urlPath = new UrlPath();
    urlPath.parse("search/hello%20world", "UTF-8");

    assertFalse(urlPath.isWithEndTag());
    assertEquals(Arrays.asList("search", "hello world"), urlPath.getSegments());
}

}
