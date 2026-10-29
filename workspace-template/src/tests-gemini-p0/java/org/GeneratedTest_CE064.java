package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE064 {

@Test
public void testGetPage() {
    List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    PageUtil util = new PageUtil(data, 3);

    assertEquals(Arrays.asList(1, 2, 3), util.getPage(1));
    assertEquals(Arrays.asList(4, 5, 6), util.getPage(2));
    assertEquals(Arrays.asList(7), util.getPage(3));

    assertTrue(util.getPage(0).isEmpty());
    assertTrue(util.getPage(4).isEmpty());
}

@Test
public void testGetPageInfo() {
    List<Integer> data = Arrays.asList(10, 20, 30, 40, 50);
    PageUtil util = new PageUtil(data, 2); // 3 pages: [10, 20], [30, 40], [50]

    PageUtil.PageInfo infoPage1 = util.getPageInfo(1);
    assertEquals(1, infoPage1.currentPage);
    assertEquals(2, infoPage1.perPage);
    assertEquals(3, infoPage1.totalPages);
    assertEquals(5, infoPage1.totalItems);
    assertFalse(infoPage1.hasPrevious);
    assertTrue(infoPage1.hasNext);
    assertEquals(Arrays.asList(10, 20), infoPage1.data);

    PageUtil.PageInfo infoPage3 = util.getPageInfo(3);
    assertTrue(infoPage3.hasPrevious);
    assertFalse(infoPage3.hasNext);
    assertEquals(Arrays.asList(50), infoPage3.data);

    PageUtil.PageInfo invalidPage = util.getPageInfo(99);
    assertEquals(0, invalidPage.currentPage);
    assertFalse(invalidPage.hasPrevious);
    assertFalse(invalidPage.hasNext);
}

@Test
public void testSearch() {
    List<Integer> data = Arrays.asList(101, 202, 103, 404, 501);
    PageUtil util = new PageUtil(data, 2);

    PageUtil.SearchResult result = util.search("01");
    assertEquals("01", result.keyword);
    assertEquals(2, result.totalResults);
    assertEquals(1, result.totalPages);
    assertEquals(Arrays.asList(101, 501), result.results);
}

@Test
public void testPageInfoAndSearchResultEqualsHashCode() {
    PageUtil.PageInfo info1 = new PageUtil.PageInfo(1, 10, 1, 1, false, false, Arrays.asList(1));
    PageUtil.PageInfo info2 = new PageUtil.PageInfo(1, 10, 1, 1, false, false, Arrays.asList(1));
    assertEquals(info1, info2);
    assertEquals(info1.hashCode(), info2.hashCode());

    PageUtil.SearchResult sr1 = new PageUtil.SearchResult("key", 1, 1, Arrays.asList(1));
    PageUtil.SearchResult sr2 = new PageUtil.SearchResult("key", 1, 1, Arrays.asList(1));
    assertEquals(sr1, sr2);
    assertEquals(sr1.hashCode(), sr2.hashCode());
}

}
