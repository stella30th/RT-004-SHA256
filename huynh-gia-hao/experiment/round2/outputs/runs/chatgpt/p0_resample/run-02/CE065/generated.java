// BEGIN GeneratedTest_CE065
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE065 {

    @Test
    public void testValidPersonRequest() {
        PersonRequest person = new PersonRequest(
                "John Doe", "Man", "01234567890");

        assertEquals("John Doe", person.getName());
        assertEquals("Man", person.getSex());
        assertEquals("01234567890", person.getPhoneNumber());
    }

    @Test
    public void testValidSexValues() {
        String[] validSexes = {"Man", "Woman", "UGM"};

        for (String sex : validSexes) {
            PersonRequest person = new PersonRequest(
                    "Alice", sex, "01234567890");
            assertEquals(sex, person.getSex());
        }
    }

    @Test
    public void testInvalidSexValues() {
        String[] invalidSexes = {
                "", "Male", "Female", "man", "woman",
                "ugm", "MAN", "Other", " Man", "Woman "
        };

        for (String sex : invalidSexes) {
            PersonRequest person = new PersonRequest(
                    "John", sex, "01234567890");
            assertNull(person.getSex());
        }

        assertNull(new PersonRequest(
                "John", null, "01234567890").getSex());
    }

    @Test
    public void testValidNameBoundaryLengths() {
        String oneCharacter = "A";
        String maxLength = "A".repeat(33);

        PersonRequest first = new PersonRequest(
                oneCharacter, "Man", "01234567890");
        PersonRequest second = new PersonRequest(
                maxLength, "Woman", "01234567890");

        assertEquals(oneCharacter, first.getName());
        assertEquals(maxLength, second.getName());
    }

    @Test
    public void testInvalidNameLengths() {
        PersonRequest empty = new PersonRequest(
                "", "Man", "01234567890");
        PersonRequest tooLong = new PersonRequest(
                "A".repeat(34), "Man", "01234567890");

        assertNull(empty.getName());
        assertNull(tooLong.getName());
    }

    @Test
    public void testNullName() {
        PersonRequest person = new PersonRequest(
                null, "Man", "01234567890");

        assertNull(person.getName());
        assertEquals("Man", person.getSex());
        assertEquals("01234567890", person.getPhoneNumber());
    }

    @Test
    public void testNamePreservesCaseAndWhitespace() {
        PersonRequest person = new PersonRequest(
                "  Alice Smith  ", "Woman", "01234567890");

        assertEquals("  Alice Smith  ", person.getName());

        PersonRequest spaces = new PersonRequest(
                "   ", "Woman", "01234567890");

        assertEquals("   ", spaces.getName());
    }

    @Test
    public void testNameAllowsSpecialCharacters() {
        PersonRequest person = new PersonRequest(
                "John-123_!", "Man", "01234567890");

        assertEquals("John-123_!", person.getName());
    }

    @Test
    public void testValidPhoneNumber() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "01234567890");

        assertEquals("01234567890", person.getPhoneNumber());
    }

    @Test
    public void testPhoneNumberPreservesLeadingZeros() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "00000000001");

        assertEquals("00000000001", person.getPhoneNumber());
    }

    @Test
    public void testPhoneNumberAllZeros() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "00000000000");

        assertEquals("00000000000", person.getPhoneNumber());
    }

    @Test
    public void testInvalidPhoneNumberLengths() {
        String[] invalidPhones = {
                "", "1", "1234567890",
                "123456789012", "123456789012345"
        };

        for (String phone : invalidPhones) {
            PersonRequest person = new PersonRequest(
                    "John", "Man", phone);
            assertNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testInvalidPhoneNumberCharacters() {
        String[] invalidPhones = {
                "1234567890A",
                "A1234567890",
                "12345-78901",
                "12345 78901",
                "+1234567890",
                "1234567890!",
                "abcdefghijk"
        };

        for (String phone : invalidPhones) {
            PersonRequest person = new PersonRequest(
                    "John", "Man", phone);
            assertNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testNullPhoneNumber() {
        PersonRequest person = new PersonRequest(
                "John", "Man", null);

        assertEquals("John", person.getName());
        assertEquals("Man", person.getSex());
        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testAllNullInputs() {
        PersonRequest person = new PersonRequest(
                null, null, null);

        assertNull(person.getName());
        assertNull(person.getSex());
        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testAllInvalidInputs() {
        PersonRequest person = new PersonRequest(
                "", "Unknown", "123");

        assertNull(person.getName());
        assertNull(person.getSex());
        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testInvalidNameDoesNotAffectOtherFields() {
        PersonRequest person = new PersonRequest(
                "A".repeat(34), "Woman", "98765432109");

        assertNull(person.getName());
        assertEquals("Woman", person.getSex());
        assertEquals("98765432109", person.getPhoneNumber());
    }

    @Test
    public void testInvalidSexDoesNotAffectOtherFields() {
        PersonRequest person = new PersonRequest(
                "Alice", "Unknown", "12345678901");

        assertEquals("Alice", person.getName());
        assertNull(person.getSex());
        assertEquals("12345678901", person.getPhoneNumber());
    }

    @Test
    public void testInvalidPhoneDoesNotAffectOtherFields() {
        PersonRequest person = new PersonRequest(
                "Alice", "Woman", "invalid");

        assertEquals("Alice", person.getName());
        assertEquals("Woman", person.getSex());
        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testIndependentInstances() {
        PersonRequest first = new PersonRequest(
                "Alice", "Woman", "01234567890");
        PersonRequest second = new PersonRequest(
                "Bob", "Man", "98765432109");

        assertEquals("Alice", first.getName());
        assertEquals("Woman", first.getSex());
        assertEquals("01234567890", first.getPhoneNumber());

        assertEquals("Bob", second.getName());
        assertEquals("Man", second.getSex());
        assertEquals("98765432109", second.getPhoneNumber());
    }
}
// END GeneratedTest_CE065