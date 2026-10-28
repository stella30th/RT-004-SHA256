package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE025 {
    @Test public void behavioralCase1() throws Exception {
        ComplexCalculator c=new ComplexCalculator(); ComplexCalculator.ComplexNumber a=new ComplexCalculator.ComplexNumber(2,3),b=new ComplexCalculator.ComplexNumber(1,-1); assertEquals(new ComplexCalculator.ComplexNumber(3,2),c.add(a,b)); assertEquals(new ComplexCalculator.ComplexNumber(1,4),c.subtract(a,b));
    }
    @Test public void behavioralCase2() throws Exception {
        ComplexCalculator c=new ComplexCalculator(); assertEquals(new ComplexCalculator.ComplexNumber(5,1),c.multiply(new ComplexCalculator.ComplexNumber(2,3),new ComplexCalculator.ComplexNumber(1,-1)));
    }
}
