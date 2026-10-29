package org.example;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE036 {

private static final double DELTA = 1e-4;

@Test
public void testSendToSuccessful() {
    EmailClient sender = new EmailClient("alice@test.com", 100.0);
    EmailClient receiver = new EmailClient("bob@test.com", 50.0);

    boolean sent = sender.sendTo(receiver, "Hello Bob", 10.0);
    assertTrue(sent);
    assertEquals(1, receiver.getInbox().size());
    assertEquals(10.0, receiver.getOccupiedSize(), DELTA);
}

@Test
public void testFetchMarksEmailRead() {
    EmailClient client = new EmailClient("charlie@test.com", 100.0);
    EmailClient sender = new EmailClient("alice@test.com", 100.0);
    sender.sendTo(client, "Work memo", 5.0);

    Map<String, Object> email = client.fetch();
    assertNotNull(email);
    assertEquals("read", email.get("state"));
    assertEquals("Work memo", email.get("content"));

    // Second fetch has no more unread emails
    assertNull(client.fetch());
}

@Test
public void testIsFullWithOneMoreEmail() {
    EmailClient client = new EmailClient("dan@test.com", 20.0);
    assertFalse(client.isFullWithOneMoreEmail(15.0));

    EmailClient sender = new EmailClient("alice@test.com", 100.0);
    sender.sendTo(client, "File 1", 15.0);

    assertTrue(client.isFullWithOneMoreEmail(10.0));
    assertFalse(client.isFullWithOneMoreEmail(5.0));
}

@Test
public void testClearInbox() {
    EmailClient client = new EmailClient("eve@test.com", 50.0);
    EmailClient sender = new EmailClient("alice@test.com", 100.0);

    sender.sendTo(client, "Mail 1", 10.0);
    sender.sendTo(client, "Mail 2", 15.0);
    assertEquals(25.0, client.getOccupiedSize(), DELTA);

    client.clearInbox(12.0); // Should remove Mail 1 (10.0) and Mail 2 (15.0) to satisfy >= 12.0
    assertEquals(0.0, client.getOccupiedSize(), DELTA);
    assertTrue(client.getInbox().isEmpty());
}

}
