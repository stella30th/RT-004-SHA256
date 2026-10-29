package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE012 {

@Test
public void testAddAndHas() {
    int state = BitStatusUtil.add(2, 4);
    assertEquals(6, state);
    assertTrue(BitStatusUtil.has(state, 2));
    assertTrue(BitStatusUtil.has(state, 4));
    assertFalse(BitStatusUtil.has(state, 8));
}

@Test
public void testRemove() {
    int state = 6; // 2 | 4
    int removed = BitStatusUtil.remove(state, 2);
    assertEquals(4, removed);
    assertFalse(BitStatusUtil.has(removed, 2));
    assertTrue(BitStatusUtil.has(removed, 4));

    int unmodified = BitStatusUtil.remove(state, 8);
    assertEquals(6, unmodified);
}

@Test(expected = IllegalArgumentException.class)
public void testCheckThrowsOnNegative() {
    BitStatusUtil.check(2, -2, 4);
}

@Test(expected = IllegalArgumentException.class)
public void testCheckThrowsOnOddNumber() {
    BitStatusUtil.check(2, 3, 4);
}

}
