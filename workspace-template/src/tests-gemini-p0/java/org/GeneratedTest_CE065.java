package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE065 {

@Test
public void testValidPersonRequest() {
    PersonRequest request = new PersonRequest("John Doe", "Man", "12345678901");
    assertEquals("John Doe", request.getName());
    assertEquals("Man", request.getSex());
    assertEquals("12345678901", request.getPhoneNumber());
}

@Test
public void testNameValidation() {
    PersonRequest emptyName = new PersonRequest("", "Woman", "12345678901");
    assertNull(emptyName.getName());

    PersonRequest nullName = new PersonRequest(null, "Woman", "12345678901");
    assertNull(nullName.getName());

    String longName = "A".repeat(34);
    PersonRequest tooLongName = new PersonRequest(longName, "Woman", "12345678901");
    assertNull(tooLongName.getName());

    String boundaryName = "A".repeat(33);
    PersonRequest validBoundary = new PersonRequest(boundaryName, "Woman", "12345678901");
    assertEquals(boundaryName, validBoundary.getName());
}

@Test
public void testSexValidation() {
    PersonRequest validMan = new PersonRequest("Bob", "Man", "12345678901");
    assertEquals("Man", validMan.getSex());

    PersonRequest validWoman = new PersonRequest("Alice", "Woman", "12345678901");
    assertEquals("Woman", validWoman.getSex());

    PersonRequest validUGM = new PersonRequest("Alex", "UGM", "12345678901");
    assertEquals("UGM", validUGM.getSex());

    PersonRequest invalidSex = new PersonRequest("Alex", "Other", "12345678901");
    assertNull(invalidSex.getSex());

    PersonRequest nullSex = new PersonRequest("Alex", null, "12345678901");
    assertNull(nullSex.getSex());
}

@Test
public void testPhoneNumberValidation() {
    PersonRequest validPhone = new PersonRequest("Charlie", "Man", "09876543210");
    assertEquals("09876543210", validPhone.getPhoneNumber());

    PersonRequest shortPhone = new PersonRequest("Charlie", "Man", "1234567890");
    assertNull(shortPhone.getPhoneNumber());

    PersonRequest longPhone = new PersonRequest("Charlie", "Man", "123456789012");
    assertNull(longPhone.getPhoneNumber());

    PersonRequest nonDigitPhone = new PersonRequest("Charlie", "Man", "1234567890a");
    assertNull(nonDigitPhone.getPhoneNumber());

    PersonRequest nullPhone = new PersonRequest("Charlie", "Man", null);
    assertNull(nullPhone.getPhoneNumber());
}

}
