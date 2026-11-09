package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE069 {

@Test
public void testAddAndDelWhiteList() {
    Server server = new Server();
    List<Integer> list = server.addWhiteList(8080);
    assertNotNull(list);
    assertTrue(list.contains(8080));

    assertNull(server.addWhiteList(8080)); // duplicate

    List<Integer> afterDel = server.delWhiteList(8080);
    assertNotNull(afterDel);
    assertFalse(afterDel.contains(8080));

    assertNull(server.delWhiteList(9999)); // non-existent
}

@Test
public void testRecvFromWhitelistedAddress() {
    Server server = new Server();
    server.addWhiteList(192);

    Map<String, Object> info = new HashMap<>();
    info.put("addr", 192);
    info.put("content", "Ping");

    Object response = server.recv(info);
    assertEquals("Ping", response);

    Map<String, Object> receiveStruct = server.show("receive");
    assertNotNull(receiveStruct);
    assertEquals(192, receiveStruct.get("addr"));
    assertEquals("Ping", receiveStruct.get("content"));
}

@Test
public void testRecvFromNonWhitelistedAddressReturnsFalse() {
    Server server = new Server();
    Map<String, Object> info = new HashMap<>();
    info.put("addr", 404);
    info.put("content", "Hello");

    assertEquals(false, server.recv(info));
}

@Test
public void testSendAndShow() {
    Server server = new Server();
    Map<String, Object> info = new HashMap<>();
    info.put("addr", 80);
    info.put("content", "GET /");

    String sendResult = server.send(info);
    assertNull(sendResult); // Success returns null

    Map<String, Object> sendStruct = server.show("send");
    assertEquals(80, sendStruct.get("addr"));
    assertEquals("GET /", sendStruct.get("content"));

    assertNull(server.show("unknown_type"));

    // Malformed send info
    Map<String, Object> badInfo = new HashMap<>();
    badInfo.put("addr", 80);
    assertEquals("info structure is not correct", server.send(badInfo));
}

}
