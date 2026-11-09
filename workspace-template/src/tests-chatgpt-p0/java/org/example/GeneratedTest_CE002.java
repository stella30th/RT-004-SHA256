package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE002 {
    @Test public void behavioralCase1() throws Exception {
        AreaCalculator a=new AreaCalculator(2); assertEquals(4*Math.PI,a.calculateCircleArea(),1e-9); assertEquals(16*Math.PI,a.calculateSphereArea(),1e-9);
    }
    @Test public void behavioralCase2() throws Exception {
        AreaCalculator a=new AreaCalculator(2); assertEquals(20*Math.PI,a.calculateCylinderArea(3),1e-9); assertEquals(2*Math.PI,a.calculateSectorArea(Math.PI),1e-9);
    }
    @Test public void behavioralCase3() throws Exception {
        assertEquals(5*Math.PI,new AreaCalculator(1).calculateAnnulusArea(2,3),1e-9);
    }
}
