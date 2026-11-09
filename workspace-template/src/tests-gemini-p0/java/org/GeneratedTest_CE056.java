package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE056 {

private File dbFile;
private MovieTicketDB db;

@Before
public void setUp() throws IOException {
    dbFile = File.createTempFile("test_tickets_", ".db");
    db = new MovieTicketDB(dbFile.getAbsolutePath());
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
public void testInsertAndSearchTickets() {
    db.insertTicket("Dune 2", "IMAX Theater", "A12", "Alice");
    List<MovieTicketDB.Ticket> tickets = db.searchTicketsByCustomer("Alice");

    assertEquals(1, tickets.size());
    MovieTicketDB.Ticket ticket = tickets.get(0);
    assertEquals("Dune 2", ticket.getMovieName());
    assertEquals("IMAX Theater", ticket.getTheaterName());
    assertEquals("A12", ticket.getSeatNumber());
    assertEquals("Alice", ticket.getCustomerName());
    assertTrue(ticket.getId() > 0);
}

@Test
public void testDeleteTicket() {
    db.insertTicket("Oppenheimer", "Cinema 1", "B5", "Bob");
    List<MovieTicketDB.Ticket> tickets = db.searchTicketsByCustomer("Bob");
    assertEquals(1, tickets.size());

    int id = tickets.get(0).getId();
    db.deleteTicket(id);

    List<MovieTicketDB.Ticket> remaining = db.searchTicketsByCustomer("Bob");
    assertTrue(remaining.isEmpty());
}

@Test
public void testSearchNonExistentCustomerReturnsEmpty() {
    List<MovieTicketDB.Ticket> tickets = db.searchTicketsByCustomer("Ghost");
    assertTrue(tickets.isEmpty());
}

@Test
public void testMultipleTicketsForSameCustomer() {
    db.insertTicket("Movie A", "T1", "C1", "Charlie");
    db.insertTicket("Movie B", "T2", "C2", "Charlie");

    List<MovieTicketDB.Ticket> tickets = db.searchTicketsByCustomer("Charlie");
    assertEquals(2, tickets.size());
}

}
