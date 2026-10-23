package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GeneratedTest_HE001 {

@Test
public void testSeparateParenGroupsBasic() {
    String input = "(()) ((()))";
    List<String> result = humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(input);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("(())", result.get(0));
    assertEquals("((()))", result.get(1));
}

@Test
public void testSeparateParenGroupsWithSpaces() {
    String input = " ( ) (( )) ";
    List<String> result = humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(input);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("()", result.get(0));
    assertEquals("(())", result.get(1));
}

@Test
public void testSeparateParenGroupsEmpty() {
    String input = "";
    List<String> result = humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(input);
    assertNotNull(result);
    assertTrue(result.isEmpty());
}

@Test
public void testSeparateParenGroupsMultiple() {
    String input = "() (()) (()())";
    List<String> result = humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(input);
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals("()", result.get(0));
    assertEquals("(())", result.get(1));
    assertEquals("(()())", result.get(2));
}
}