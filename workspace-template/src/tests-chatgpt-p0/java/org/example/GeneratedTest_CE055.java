package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE055 {
    @Test public void behavioralCase1() throws Exception {
        MovieBookingSystem m=new MovieBookingSystem(); m.addMovie("Film",10,"10:00","12:00",3); assertEquals(1,m.getMovies().size());
    }
}
