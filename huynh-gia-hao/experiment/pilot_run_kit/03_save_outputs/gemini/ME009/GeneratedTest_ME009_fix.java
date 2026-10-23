package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeneratedTest_ME009 {

@Test
public void testEmptyString() {
    assertEquals(0, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters(""));
}

@Test
public void testSingleLowercase() {
    assertEquals(1, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("a"));
}

@Test
public void testSingleUppercase() {
    assertEquals(1, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("A"));
}

@Test
public void testMixedCaseSameChar() {
    assertEquals(1, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("aA"));
}

@Test
public void testMultipleDistinctCharacters() {
    assertEquals(3, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("xyzXYZ"));
}

@Test
public void testStringWithDuplicatesAndCase() {
    assertEquals(4, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("Jerry"));
}

@Test
public void testComplexStringWithSpacesAndPunctuation() {
    assertEquals(9, humaneval.correct.COUNT_DISTINCT_CHARACTERS.count_distinct_characters("Hello World!"));
}
}