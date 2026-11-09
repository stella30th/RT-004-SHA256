package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratedTest_CE042 {

@Test
public void testBookRoomSuccessAndExhaustion() {
    Map<String, Integer> rooms = new HashMap<>();
    rooms.put("single", 2);
    Hotel hotel = new Hotel("Grand Hotel", rooms);

    assertEquals("Success!", hotel.bookRoom("single", 2, "Alice"));
    assertEquals(0, hotel.getAvailableRooms("single"));

    assertEquals("False", hotel.bookRoom("single", 1, "Bob"));
    assertEquals("False", hotel.bookRoom("suite", 1, "Charlie")); // Non-existent room type
}

@Test
public void testCheckInPartialAndFull() {
    Map<String, Integer> rooms = new HashMap<>();
    rooms.put("double", 5);
    Hotel hotel = new Hotel("Grand Hotel", rooms);

    hotel.bookRoom("double", 3, "Alice");

    // Cannot check in more than booked
    assertFalse(hotel.checkIn("double", 4, "Alice"));

    // Partial check-in
    assertTrue(hotel.checkIn("double", 1, "Alice"));
    // Remaining 2
    assertTrue(hotel.checkIn("double", 2, "Alice"));
    // Alice should now be fully checked in / removed from booked
    assertFalse(hotel.checkIn("double", 1, "Alice"));
}

@Test
public void testCheckOutIncreasesAvailableRooms() {
    Map<String, Integer> rooms = new HashMap<>();
    rooms.put("single", 3);
    Hotel hotel = new Hotel("Grand Hotel", rooms);

    hotel.bookRoom("single", 2, "Alice");
    assertEquals(1, hotel.getAvailableRooms("single"));

    hotel.checkOut("single", 1);
    assertEquals(2, hotel.getAvailableRooms("single"));

    hotel.checkOut("suite", 1); // Check out for room type not previously in map
    assertEquals(1, hotel.getAvailableRooms("suite"));
}

@Test
public void testGetAvailableRoomsUnknownReturnsZero() {
    Hotel hotel = new Hotel("Empty Hotel", new HashMap<>());
    assertEquals(0, hotel.getAvailableRooms("deluxe"));
}

}
