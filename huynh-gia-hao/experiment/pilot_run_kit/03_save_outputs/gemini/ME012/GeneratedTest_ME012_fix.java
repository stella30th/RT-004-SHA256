package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeneratedTest_ME012 {

@Test
public void testEmptyList() {
    List<String> input = Collections.emptyList();
    List<String> result = humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(input, "abc");
    assertTrue(result.isEmpty());
}

@Test
public void testFilteringWithMatches() {
    List<String> input = Arrays.asList("apple", "banana", "grape", "pineapple");
    List<String> result = humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(input, "app");
    assertEquals(2, result.size());
    assertEquals("apple", result.get(0));
    assertEquals("pineapple", result.get(1));
}

@Test
public void testNoMatches() {
    List<String> input = Arrays.asList("dog", "cat", "bird");
    List<String> result = humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(input, "fish");
    assertTrue(result.isEmpty());
}

@Test
public void testEmptySubstring() {
    List<String> input = Arrays.asList("a", "b", "c");
    List<String> result = humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(input, "");
    assertEquals(3, result.size());
    assertEquals(input, result);
}

@Test
public void testCaseSensitivity() {
    List<String> input = Arrays.asList("Apple", "apple", "APPLE");
    List<String> result = humaneval.correct.FILTER_BY_SUBSTRING.filter_by_substring(input, "apple");
    assertEquals(1, result.size());
    assertEquals("apple", result.get(0));
}
}