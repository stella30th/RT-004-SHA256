package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE032 {

private static final double DELTA = 1e-3;

@Test
public void testCorrelationCoefficient() {
    List<Double> data1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    List<Double> data2 = Arrays.asList(2.0, 4.0, 6.0, 8.0, 10.0);

    double corr = DataStatistics4.correlationCoefficient(data1, data2);
    assertEquals(1.0, corr, DELTA);

    List<Double> dataOpposite = Arrays.asList(5.0, 4.0, 3.0, 2.0, 1.0);
    double corrNeg = DataStatistics4.correlationCoefficient(data1, dataOpposite);
    assertEquals(-1.0, corrNeg, DELTA);
}

@Test
public void testSkewnessSymmetricDistribution() {
    // Perfectly symmetric data has skewness = 0
    List<Double> symmetric = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    double skew = DataStatistics4.skewness(symmetric);
    assertEquals(0.0, skew, DELTA);

    // Constant data stdDev = 0 -> skewness returns 0
    List<Double> constant = Arrays.asList(2.0, 2.0, 2.0);
    assertEquals(0.0, DataStatistics4.skewness(constant), DELTA);
}

@Test
public void testKurtosis() {
    List<Double> constant = Arrays.asList(5.0, 5.0, 5.0);
    assertTrue(Double.isNaN(DataStatistics4.kurtosis(constant)));

    List<Double> data = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    double kurt = DataStatistics4.kurtosis(data);
    // Discrete uniform kurtosis should be negative excess kurtosis
    assertTrue(kurt < 0);
}

@Test
public void testPdf() {
    List<Double> points = Arrays.asList(0.0);
    double mu = 0.0;
    double sigma = 1.0;

    List<Double> pdfValues = DataStatistics4.pdf(points, mu, sigma);
    assertEquals(1, pdfValues.size());
    // At x = 0, standard normal pdf = 1 / sqrt(2 * PI) ~ 0.39894
    assertEquals(0.39894, pdfValues.get(0), DELTA);
}

}
