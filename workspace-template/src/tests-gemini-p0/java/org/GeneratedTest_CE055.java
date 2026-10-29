package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE055 {

@Test
public void testAddMovieAndGetMovies() {
    MovieBookingSystem system = new MovieBookingSystem();
    system.addMovie("Inception", 12.5, "14:00", "16:30", 5);

    assertEquals(1, system.getMovies().size());
    assertEquals("Inception", system.getMovies().get(0).get("name"));
    assertEquals(12.5, system.getMovies().get(0).get("price"));
}

@Test
public void testBookTicketSuccessfulAndDuplicate() {
    MovieBookingSystem system = new MovieBookingSystem();
    system.addMovie("Interstellar", 15.0, "18:00", "21:00", 5);

    int[][] seats = {{0, 0}, {0, 1}};
    assertEquals("Booking success.", system.bookTicket("Interstellar", seats));

    // Rebooking already booked seat fails
    int[][] conflictingSeats = {{0, 1}, {0, 2}};
    assertEquals("Booking failed.", system.bookTicket("Interstellar", conflictingSeats));
}

@Test
public void testBookTicketMovieNotFound() {
    MovieBookingSystem system = new MovieBookingSystem();
    int[][] seats = {{0, 0}};
    assertEquals("Movie not found.", system.bookTicket("Avatar", seats));
}

@Test
public void testAvailableMovies() {
    MovieBookingSystem system = new MovieBookingSystem();
    system.addMovie("Morning Show", 10.0, "09:00", "11:00", 4);
    system.addMovie("Afternoon Show", 10.0, "13:00", "15:00", 4);
    system.addMovie("Night Show", 10.0, "20:00", "22:00", 4);

    List<String> available = system.availableMovies("08:00", "16:00");
    assertEquals(2, available.size());
    assertTrue(available.contains("Morning Show"));
    assertTrue(available.contains("Afternoon Show"));
}

}
