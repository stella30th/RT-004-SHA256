// BEGIN GeneratedTest_CE065
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE065 {

    @Test public void validFieldsArePreserved() {
        PersonRequest p = new PersonRequest("Alice", "Woman", "01234567890");
        assertEquals("Alice", p.getName());
        assertEquals("Woman", p.getSex());
        assertEquals("01234567890", p.getPhoneNumber());
    }
    @Test public void nameBoundaryAndNullValidation() {
        assertEquals("A", new PersonRequest("A", "Man", "12345678901").getName());
        String max = "A".repeat(33);
        assertEquals(max, new PersonRequest(max, "Man", "12345678901").getName());
        assertNull(new PersonRequest("A".repeat(34), "Man", "12345678901").getName());
        assertNull(new PersonRequest("", "Man", "12345678901").getName());
        assertNull(new PersonRequest(null, "Man", "12345678901").getName());
        assertEquals("   ", new PersonRequest("   ", "Man", "12345678901").getName());
    }
    @Test public void sexAcceptsOnlyThreeExactValues() {
        for (String valid : new String[]{"Man", "Woman", "UGM"}) {
            assertEquals(valid, new PersonRequest("A", valid, "12345678901").getSex());
        }
        for (String invalid : new String[]{"man", "woman", "Male", "", " UGM"}) {
            assertNull(new PersonRequest("A", invalid, "12345678901").getSex());
        }
        assertNull(new PersonRequest("A", null, "12345678901").getSex());
    }
    @Test public void phoneRequiresExactlyElevenAsciiDigits() {
        assertEquals("00000000000", new PersonRequest("A", "Man", "00000000000").getPhoneNumber());
        for (String invalid : new String[]{"", "1234567890", "123456789012", "12345a78901", "12345 78901", "+1234567890"}) {
            assertNull(new PersonRequest("A", "Man", invalid).getPhoneNumber());
        }
        assertNull(new PersonRequest("A", "Man", null).getPhoneNumber());
        assertNull(new PersonRequest("A", "Man", "\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669\u0660\u0661").getPhoneNumber());
    }
    @Test public void invalidFieldsDoNotInvalidateOtherFields() {
        PersonRequest p = new PersonRequest("", "Woman", "01234567890");
        assertNull(p.getName());
        assertEquals("Woman", p.getSex());
        assertEquals("01234567890", p.getPhoneNumber());
        PersonRequest q = new PersonRequest("Bob", "invalid", "abc");
        assertEquals("Bob", q.getName());
        assertNull(q.getSex());
        assertNull(q.getPhoneNumber());
    }

    @Test public void allNullFieldsRemainNull() { PersonRequest p=new PersonRequest(null,null,null); assertNull(p.getName()); assertNull(p.getSex()); assertNull(p.getPhoneNumber()); }
}
// END GeneratedTest_CE065
