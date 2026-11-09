package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE080 {
    @Test public void behavioralCase1() throws Exception {
        Thermostat t=new Thermostat(20,25,"heat"); assertEquals(25,t.getTargetTemperature(),0); t.setTargetTemperature(22); assertEquals(22,t.getTargetTemperature(),0);
    }
}
