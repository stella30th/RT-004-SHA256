package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GeneratedTest_CE087 {

private File dbFile;
private UserLoginDB db;

@Before
public void setUp() throws IOException {
    dbFile = File.createTempFile("test_user_db_", ".db");
    db = new UserLoginDB(dbFile.getAbsolutePath());
}

@After
public void tearDown() {
    if (db != null) {
        db.close();
    }
    if (dbFile != null && dbFile.exists()) {
        dbFile.delete();
    }
}

@Test
public void testInsertAndSearchUser() {
    db.insertUser("alice", "secret123");
    String result = db.searchUserByUsername("alice");

    assertNotNull(result);
    assertEquals("alice,secret123", result);
}

@Test
public void testValidateUserLogin() {
    db.insertUser("bob", "password456");

    assertTrue(db.validateUserLogin("bob", "password456"));
    assertFalse(db.validateUserLogin("bob", "wrong_password"));
    assertFalse(db.validateUserLogin("non_existent", "any"));
}

@Test
public void testDeleteUserByUsername() {
    db.insertUser("charlie", "pass789");
    assertNotNull(db.searchUserByUsername("charlie"));

    db.deleteUserByUsername("charlie");
    assertNull(db.searchUserByUsername("charlie"));
}

@Test
public void testSearchNonExistentUserReturnsNull() {
    assertNull(db.searchUserByUsername("ghost_user"));
}

}
