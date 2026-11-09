package org.example;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE018 {

@Test
public void testAddAndGetEvents() {
    CalendarUtil calendar = new CalendarUtil();
    LocalDateTime day = LocalDateTime.of(2026, 5, 10, 10, 0);

    Event event1 = new Event(day, day.withHour(10), day.withHour(11), "Meeting 1");
    Event event2 = new Event(day, day.withHour(14), day.withHour(15), "Meeting 2");
    calendar.addEvent(event1);
    calendar.addEvent(event2);

    List<Event> events = calendar.getEvents(day);
    assertEquals(2, events.size());
    assertTrue(events.contains(event1));
}

@Test
public void testRemoveEvent() {
    CalendarUtil calendar = new CalendarUtil();
    LocalDateTime day = LocalDateTime.of(2026, 5, 10, 10, 0);
    Event event = new Event(day, day.withHour(10), day.withHour(11), "Standup");
    calendar.addEvent(event);

    calendar.removeEvent(event);
    assertTrue(calendar.getEvents(day).isEmpty());
}

@Test
public void testIsAvailable() {
    CalendarUtil calendar = new CalendarUtil();
    LocalDateTime day = LocalDateTime.of(2026, 5, 10, 0, 0);
    Event event = new Event(day, day.withHour(10), day.withHour(12), "Busy Slot");
    calendar.addEvent(event);

    assertFalse(calendar.isAvailable(day.withHour(11), day.withHour(13)));
    assertTrue(calendar.isAvailable(day.withHour(8), day.withHour(10)));
    assertTrue(calendar.isAvailable(day.withHour(12), day.withHour(14)));
}

@Test
public void testGetAvailableSlots() {
    CalendarUtil calendar = new CalendarUtil();
    LocalDateTime day = LocalDateTime.of(2026, 5, 10, 0, 0);
    Event event = new Event(day, day.withHour(0), day.withHour(1), "Midnight Job");
    calendar.addEvent(event);

    List<Pair<LocalDateTime, LocalDateTime>> slots = calendar.getAvailableSlots(day);
    assertEquals(23, slots.size());
    assertEquals(day.withHour(1), slots.get(0).getFirst());
}

}
