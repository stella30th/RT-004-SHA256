package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE039 {
    @Test public void behavioralCase1() throws Exception {
        ExpressionCalculator e=new ExpressionCalculator(); assertTrue(e.isOperator('+')); assertFalse(e.isOperator('x'));
    }
    @Test public void behavioralCase2() throws Exception {
        ExpressionCalculator e=new ExpressionCalculator(); assertEquals(7.0,e.calculate("1+2*3"),1e-9);
    }
}
