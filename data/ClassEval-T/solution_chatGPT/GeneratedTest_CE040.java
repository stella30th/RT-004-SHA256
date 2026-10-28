package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE040 {
    @Test public void behavioralCase1() throws Exception {
        FitnessTracker f=new FitnessTracker(1.8,81,25,"male"); assertEquals(25,f.getBMI(),1e-9);
    }
    @Test public void behavioralCase2() throws Exception {
        FitnessTracker f=new FitnessTracker(1.7,68,25,"male"); assertTrue(f.calculateCalorieIntake()>0);
    }
}
