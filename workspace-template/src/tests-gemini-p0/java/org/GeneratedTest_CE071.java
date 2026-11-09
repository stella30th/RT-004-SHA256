package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE071 {

@Test
public void testAddUser() {
    SignInSystem system = new SignInSystem();
    assertTrue(system.addUser("Alice"));
    assertFalse(system.addUser("Alice")); // duplicate
}

@Test
public void testSignInAndCheckSignIn() {
    SignInSystem system = new SignInSystem();
    system.addUser("Bob");

    assertFalse(system.checkSignIn("Bob"));
    assertTrue(system.signIn("Bob"));
    assertTrue(system.checkSignIn("Bob"));

    // Unregistered user
    assertFalse(system.signIn("Charlie"));
    assertFalse(system.checkSignIn("Charlie"));
}

@Test
public void testAllSignedIn() {
    SignInSystem system = new SignInSystem();
    system.addUser("Alice");
    system.addUser("Bob");

    assertFalse(system.allSignedIn());

    system.signIn("Alice");
    assertFalse(system.allSignedIn());

    system.signIn("Bob");
    assertTrue(system.allSignedIn());
}

@Test
public void testAllNotSignedIn() {
    SignInSystem system = new SignInSystem();
    system.addUser("Alice");
    system.addUser("Bob");
    system.addUser("Charlie");

    system.signIn("Bob");

    List<String> notSignedIn = system.allNotSignedIn();
    assertEquals(2, notSignedIn.size());
    assertTrue(notSignedIn.contains("Alice"));
    assertTrue(notSignedIn.contains("Charlie"));
    assertFalse(notSignedIn.contains("Bob"));
}

}
