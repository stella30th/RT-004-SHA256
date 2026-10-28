package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE018 {
    @Test public void behavioralCase1() throws Exception {
        CalendarUtil c=new CalendarUtil(); java.time.LocalDateTime a=java.time.LocalDateTime.of(2030,1,1,10,0); Event e=new Event(a,a,a.plusHours(1),"meet"); c.addEvent(e); assertEquals(1,c.getEvents(a).size()); assertFalse(c.isAvailable(a,a.plusMinutes(20))); c.removeEvent(e); assertTrue(c.isAvailable(a,a.plusMinutes(20)));
    }
}
