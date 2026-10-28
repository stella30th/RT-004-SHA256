package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE075 {
    @Test public void behavioralCase1() throws Exception {
        assertTrue(SQLQueryBuilder.select("users","name",Collections.emptyMap()).contains("users"));
    }
}
