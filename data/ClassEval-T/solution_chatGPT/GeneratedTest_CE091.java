package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE091 {
    @Test public void behavioralCase1() throws Exception {
        WeatherSystem w=new WeatherSystem("Hanoi"); assertEquals("Hanoi",w.getCity()); w.setCity("Saigon"); assertEquals("Saigon",w.getCity()); w.setTemperature(0.0); assertEquals(32,w.celsiusToFahrenheit(),1e-9);
    }
}
