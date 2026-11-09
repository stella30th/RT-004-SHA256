
// BEGIN GeneratedTest_CE065
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE065 {

    @Test
    public void testValidInputs() {
        PersonRequest p = new PersonRequest("Alice", "Woman", "01234567890");
        assertEquals("Alice", p.getName());
        assertEquals("Woman", p.getSex());
        assertEquals("01234567890", p.getPhoneNumber());
    }

    @Test
    public void testInvalidName() {
        PersonRequest p = new PersonRequest("", "Man", "01234567890");
        assertNull(p.getName());
    }

    @Test
    public void testInvalidSex() {
        PersonRequest p = new PersonRequest("Bob", "Invalid", "01234567890");
        assertNull(p.getSex());
    }

    @Test
    public void testInvalidPhone() {
        PersonRequest p = new PersonRequest("Bob", "UGM", "12345");
        assertNull(p.getPhoneNumber());
    }
}
// END GeneratedTest_CE065
