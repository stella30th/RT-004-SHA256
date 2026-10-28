package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE053 {

@Test public void metricsAccumulateOnEachCall() {
 MetricsCalculator m=new MetricsCalculator();
 int[] p={1,1,0,0},t={1,0,1,0};
 assertEquals(0.5,m.precision(p,t),1e-12);
 assertEquals(0.5,m.recall(p,t),1e-12);
 assertEquals(0.5,m.f1Score(p,t),1e-12);
 assertEquals(0.5,m.accuracy(p,t),1e-12);
}
@Test public void repeatedCallsRetainPreviousCounters() {
 MetricsCalculator m=new MetricsCalculator();
 assertEquals(1.0,m.precision(new int[]{1},new int[]{1}),1e-12);
 assertEquals(0.5,m.precision(new int[]{1},new int[]{0}),1e-12);
 assertEquals(0.5,m.recall(new int[]{0},new int[]{1}),1e-12);
}
@Test public void noPositiveObservationsReturnZero() {
 MetricsCalculator m=new MetricsCalculator();
 assertEquals(0.0,m.precision(new int[]{0},new int[]{0}),0.0);
 assertEquals(0.0,m.recall(new int[]{0},new int[]{0}),0.0);
}

}
