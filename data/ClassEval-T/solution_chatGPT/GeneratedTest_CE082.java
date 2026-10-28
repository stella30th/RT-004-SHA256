package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE082 {
    @Test public void behavioralCase1() throws Exception {
        TimeUtils t=new TimeUtils(); java.time.LocalDateTime d=java.time.LocalDateTime.of(2025,1,2,3,4,5); assertEquals(d,t.stringToDatetime(t.datetimeToString(d)));
    }
}
