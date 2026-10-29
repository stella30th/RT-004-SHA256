package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE067 {

@Test
public void testMatchAndFindall() {
    RegexUtils utils = new RegexUtils();
    assertTrue(utils.match("\\d+", "12345"));
    assertFalse(utils.match("\\d+", "123a45"));

    List<String> found = utils.findall("\\d+", "abc 12 def 345");
    assertEquals(Arrays.asList("12", "345"), found);
}

@Test
public void testSplitAndSub() {
    RegexUtils utils = new RegexUtils();
    List<String> splits = utils.split("[,;]", "a,b;c,d");
    assertEquals(Arrays.asList("a", "b", "c", "d"), splits);

    String replaced = utils.sub("\\s+", "-", "hello   world test");
    assertEquals("hello-world-test", replaced);
}

@Test
public void testEmailPatternAndExtraction() {
    RegexUtils utils = new RegexUtils();
    String text = "Contact us at support@example.com or sales@test.org!";
    List<String> emails = utils.extractEmail(text);

    assertEquals(2, emails.size());
    assertTrue(emails.contains("support@example.com"));
    assertTrue(emails.contains("sales@test.org"));
}

@Test
public void testPhoneNumberValidation() {
    RegexUtils utils = new RegexUtils();
    assertTrue(utils.validatePhoneNumber("123-456-7890"));
    assertFalse(utils.validatePhoneNumber("1234567890"));
    assertFalse(utils.validatePhoneNumber("12-345-67890"));
}

}
