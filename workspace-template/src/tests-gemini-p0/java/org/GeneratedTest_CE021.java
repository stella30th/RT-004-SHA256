package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE021 {

@Test
public void testAddAndRemoveUser() {
    Chat chat = new Chat();
    assertTrue(chat.addUser("Alice"));
    assertFalse(chat.addUser("Alice")); // Duplicate

    assertTrue(chat.removeUser("Alice"));
    assertFalse(chat.removeUser("Alice")); // Already removed
    assertFalse(chat.removeUser("Bob"));   // Never added
}

@Test
public void testSendMessageBetweenRegisteredUsers() {
    Chat chat = new Chat();
    chat.addUser("Alice");
    chat.addUser("Bob");

    boolean sent = chat.sendMessage("Alice", "Bob", "Hello Bob!");
    assertTrue(sent);

    List<Chat.Message> aliceMessages = chat.getMessages("Alice");
    List<Chat.Message> bobMessages = chat.getMessages("Bob");

    assertEquals(1, aliceMessages.size());
    assertEquals(1, bobMessages.size());

    Chat.Message msg = aliceMessages.get(0);
    assertEquals("Alice", msg.getSender());
    assertEquals("Bob", msg.getReceiver());
    assertEquals("Hello Bob!", msg.getMessage());
    assertNotNull(msg.getTimestamp());
    assertEquals(msg, bobMessages.get(0));
}

@Test
public void testSendMessageFailsWhenUserNotRegistered() {
    Chat chat = new Chat();
    chat.addUser("Alice");

    assertFalse(chat.sendMessage("Alice", "Unknown", "Hi"));
    assertFalse(chat.sendMessage("Unknown", "Alice", "Hi"));
}

@Test
public void testGetMessagesForUnregisteredUserReturnsEmpty() {
    Chat chat = new Chat();
    List<Chat.Message> messages = chat.getMessages("NonExistent");
    assertNotNull(messages);
    assertTrue(messages.isEmpty());
}

@Test
public void testMessageEqualsHashCodeAndToString() {
    Chat.Message msg1 = new Chat().new Message("Alice", "Bob", "Hey", "2026-01-01 10:00:00");
    Chat.Message msg2 = new Chat().new Message("Alice", "Bob", "Hey", "2026-01-01 10:00:00");
    Chat.Message msg3 = new Chat().new Message("Alice", "Charlie", "Hey", "2026-01-01 10:00:00");

    assertEquals(msg1, msg2);
    assertNotEquals(msg1, msg3);
    assertEquals(msg1.hashCode(), msg2.hashCode());
    assertTrue(msg1.toString().contains("Alice"));
}

}
