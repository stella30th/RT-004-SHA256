package org.example;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GeneratedTest_CE082 {

@Test
public void testGetCurrentTimeAndDate() {
    TimeUtils utils = new TimeUtils();
    assertNotNull(utils.getCurrentTime());
    assertNotNull(utils.getCurrentDate());
}

@Test
public void testAddSeconds() {
    TimeUtils utils = new TimeUtils();
    utils.datetime = LocalDateTime.of(2026, 1, 1, 12, 0, 0);

    String result = utils.addSeconds(90);
    assertEquals("12:01:30", result);
}

@Test
public void testStringToDatetimeAndDatetimeToString() {
    TimeUtils utils = new TimeUtils();
    LocalDateTime dt = utils.stringToDatetime("2026-5-10 14:30:0");
    assertEquals(2026, dt.getYear());
    assertEquals(5, dt.getMonthValue());
    assertEquals(10, dt.getDayOfMonth());
    assertEquals(14, dt.getHour());
    assertEquals(30, dt.getMinute());

    String str = utils.datetimeToString(dt);
    assertEquals("2026-05-10 14:30:00", str);
}

@Test
public void testGetMinutesAndGetFormatTime() {
    TimeUtils utils = new TimeUtils();
    int minutes = utils.getMinutes("2026-1-1 10:0:0", "2026-1-1 11:30:0");
    assertEquals(90, minutes);

    String formatted = utils.getFormatTime(2026, 12, 25, 9, 5, 1);
    assertEquals("2026-12-25 09:05:01", formatted);
}

}
