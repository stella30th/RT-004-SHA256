package humaneval.generated;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class GeneratedTest_HE001 {
@Test
public void testSingleSimpleGroup() {
    List<String> expected = Collections.singletonList("()");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("()")
    );
}

@Test
public void testMultipleSimpleGroups() {
    List<String> expected = Arrays.asList("()", "()", "()");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("()()()")
    );
}

@Test
public void testNestedGroups() {
    List<String> expected = Arrays.asList("(())", "((()))", "()");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("(())((()))()")
    );
}

@Test
public void testSpacesBetweenGroupsAreIgnored() {
    List<String> expected = Arrays.asList("()", "(())", "((()))");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("() (()) ((()))")
    );
}

@Test
public void testSpacesInsideGroupsAreIgnored() {
    List<String> expected = Arrays.asList("(())", "(()())");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("( ( ) ) ( () () )")
    );
}

@Test
public void testDeeplyNestedParentheses() {
    List<String> expected = Collections.singletonList("((((()))))");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("((((()))))")
    );
}

@Test
public void testMixedNestedAndFlatGroups() {
    List<String> expected = Arrays.asList("(()())", "()", "((())())", "(())");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(
                    "(()()) () ((())()) (())"
            )
    );
}

@Test
public void testEmptyInput() {
    assertEquals(
            Collections.emptyList(),
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("")
    );
}

@Test
public void testOnlySpaces() {
    assertEquals(
            Collections.emptyList(),
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups("     ")
    );
}

@Test
public void testHumanEvalExamplePattern() {
    List<String> expected = Arrays.asList("(()())", "((()))", "()", "((())()())");
    assertEquals(
            expected,
            humaneval.correct.SEPARATE_PAREN_GROUPS.separate_paren_groups(
                    "(()()) ((())) () ((())()())"
            )
    );
}

}