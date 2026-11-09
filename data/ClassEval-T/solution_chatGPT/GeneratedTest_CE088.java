package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE088 {
    @Test public void behavioralCase1() throws Exception {
        assertEquals(1,VectorUtil.similarity(new double[]{1,0},new double[]{2,0}),1e-9); assertEquals(0,VectorUtil.similarity(new double[]{1,0},new double[]{0,1}),1e-9);
    }
}
