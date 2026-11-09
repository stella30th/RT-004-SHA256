package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE008 {

@Test
public void testBalancedExpressions() {
    BalancedBrackets bb1 = new BalancedBrackets("a(b)c");
    assertTrue(bb1.checkBalancedBrackets());

    BalancedBrackets bb2 = new BalancedBrackets("{ [ a + (b * c) ] }");
    assertTrue(bb2.checkBalancedBrackets());
}

@Test
public void testUnbalancedMismatchedTypes() {
    BalancedBrackets bb = new BalancedBrackets("( [ ) ]");
    assertFalse(bb.checkBalancedBrackets());
}

@Test
public void testUnbalancedPrematureCloseOrIncompleteOpen() {
    BalancedBrackets bbExtraClose = new BalancedBrackets("())");
    assertFalse(bbExtraClose.checkBalancedBrackets());

    BalancedBrackets bbIncomplete = new BalancedBrackets("(()");
    assertFalse(bbIncomplete.checkBalancedBrackets());
}

@Test
public void testClearExprRetainsOnlyBrackets() {
    BalancedBrackets bb = new BalancedBrackets("x + (y * [z])");
    bb.clearExpr();
    assertEquals("([])", bb.getExpr());
}

}
