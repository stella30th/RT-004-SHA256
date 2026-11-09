package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE056 {
    @Test public void behavioralCase1() throws Exception {
        MovieTicketDB.Ticket t=new MovieTicketDB.Ticket(2,"Film","Hall","A1","Ann"); assertEquals(2,t.getId()); assertEquals("Film",t.getMovieName()); assertEquals("Ann",t.getCustomerName());
    }
}
