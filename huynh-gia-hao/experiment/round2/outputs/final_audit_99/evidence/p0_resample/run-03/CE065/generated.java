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
    public void testAllValidSexValues() {
        String[] validSexes = {"Man", "Woman", "UGM"};

        for (String sex : validSexes) {
            PersonRequest person = new PersonRequest(
                    "Alice", sex, "01234567890");

            assertEquals(sex, person.getSex());
            assertEquals("Alice", person.getName());
            assertEquals("01234567890", person.getPhoneNumber());
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
    }

    @Test
    public void testNullSex() {
        PersonRequest person = new PersonRequest(
                "John", null, "01234567890");

        assertEquals("John", person.getName());
        assertNull(person.getSex());
        assertEquals("01234567890", person.getPhoneNumber());
    }

    @Test
    public void testNameMinimumValidLength() {
        PersonRequest person = new PersonRequest(
                "A", "Man", "01234567890");

        assertEquals("A", person.getName());
    }

    @Test
    public void testNameMaximumValidLength() {
        String name = "A".repeat(33);
        PersonRequest person = new PersonRequest(
                name, "Woman", "01234567890");

        assertEquals(name, person.getName());
        assertEquals(33, person.getName().length());
    }

    @Test
    public void testNameExceedsMaximumLength() {
        PersonRequest person = new PersonRequest(
                "A".repeat(34), "Man", "01234567890");

        assertNull(person.getName());
    }

    @Test
    public void testEmptyName() {
        PersonRequest person = new PersonRequest(
                "", "Man", "01234567890");

        assertNull(person.getName());
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
    public void testNamePreservesWhitespace() {
        PersonRequest person = new PersonRequest(
                "  Alice Smith  ", "Woman", "01234567890");

        assertEquals("  Alice Smith  ", person.getName());
    }

    @Test
    public void testWhitespaceOnlyNameIsAccepted() {
        PersonRequest person = new PersonRequest(
                "   ", "Woman", "01234567890");

        assertEquals("   ", person.getName());
    }

    @Test
    public void testNamePreservesCaseAndSpecialCharacters() {
        PersonRequest person = new PersonRequest(
                "JoHn-123_!", "Man", "01234567890");

        assertEquals("JoHn-123_!", person.getName());
    }

    @Test
    public void testValidPhoneNumber() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "12345678901");

        assertEquals("12345678901", person.getPhoneNumber());
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
    public void testPhoneNumberAllNines() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "99999999999");

        assertEquals("99999999999", person.getPhoneNumber());
    }

    @Test
    public void testPhoneNumberTooShort() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "1234567890");

        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testPhoneNumberTooLong() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "123456789012");

        assertNull(person.getPhoneNumber());
    }

    @Test
    public void testEmptyPhoneNumber() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "");

        assertNull(person.getPhoneNumber());
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
    public void testPhoneNumberRejectsLetters() {
        String[] invalidPhones = {
                "1234567890A",
                "A1234567890",
                "12345A78901",
                "abcdefghijk"
        };

        for (String phone : invalidPhones) {
            PersonRequest person = new PersonRequest(
                    "John", "Man", phone);

            assertNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testPhoneNumberRejectsSpecialCharacters() {
        String[] invalidPhones = {
                "12345-78901",
                "12345 78901",
                "+1234567890",
                "1234567890!",
                "123.4567890"
        };

        for (String phone : invalidPhones) {
            PersonRequest person = new PersonRequest(
                    "John", "Man", phone);

            assertNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testPhoneNumberRejectsWhitespace() {
        String[] invalidPhones = {
                " 1234567890",
                "1234567890 ",
                "12345 78901",
                "           "
        };

        for (String phone : invalidPhones) {
            PersonRequest person = new PersonRequest(
                    "John", "Man", phone);

            assertNull(person.getPhoneNumber());
        }
    }

    @Test
    public void testPhoneNumberRejectsUnicodeDigits() {
        PersonRequest person = new PersonRequest(
                "John", "Man", "\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669\u0660\u0661");

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