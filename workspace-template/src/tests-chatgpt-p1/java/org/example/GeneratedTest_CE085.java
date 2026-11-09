package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE085 {

@Test public void extractsLiteralSchemeHostAndPath() {
 URLHandler u=new URLHandler("https://example.com:8080/a?x=1#frag");
 assertEquals("https",u.getScheme());assertEquals("example.com:8080",u.getHost());
 assertEquals("/a?x=1#frag",u.getPath());
 assertEquals("1",u.getQueryParams().get("x"));assertEquals("frag",u.getFragment());
}
@Test public void noSlashMeansQueryIsPartOfHost() {
 URLHandler u=new URLHandler("https://example.org?x=1");
 assertEquals("example.org?x=1",u.getHost());assertNull(u.getPath());
 assertEquals("1",u.getQueryParams().get("x"));
}
@Test public void simpleSplitDropsPairsWithMultipleEquals() {
 URLHandler u=new URLHandler("x://h/a?k=v&bad=a=b&empty=&z=2");
 assertEquals("v",u.getQueryParams().get("k"));
 assertEquals("2",u.getQueryParams().get("z"));
 assertFalse(u.getQueryParams().containsKey("bad"));
 assertFalse(u.getQueryParams().containsKey("empty"));
}
@Test public void absentSchemeAndQueryReturnNull() {
 URLHandler u=new URLHandler("example.com/a");
 assertNull(u.getScheme());assertNull(u.getHost());assertNull(u.getQueryParams());
}

}
