package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE088 {

private static final double DELTA = 1e-4;

@Test
public void testSimilarity() {
    double[] v1 = {1.0, 0.0};
    double[] v2 = {1.0, 0.0};
    assertEquals(1.0, VectorUtil.similarity(v1, v2), DELTA);

    double[] vOrthogonal = {0.0, 1.0};
    assertEquals(0.0, VectorUtil.similarity(v1, vOrthogonal), DELTA);
}

@Test
public void testCosineSimilarities() {
    double[] query = {1.0, 1.0};
    List<double[]> targets = Arrays.asList(
            new double[]{1.0, 1.0},
            new double[]{-1.0, -1.0}
    );

    List<Double> similarities = VectorUtil.cosineSimilarities(query, targets);
    assertEquals(2, similarities.size());
    assertEquals(1.0, similarities.get(0), DELTA);
    assertEquals(-1.0, similarities.get(1), DELTA);
}

@Test
public void testNSimilarity() {
    List<double[]> list1 = Arrays.asList(
            new double[]{1.0, 2.0},
            new double[]{3.0, 4.0}
    ); // avg = [2.0, 3.0]

    List<double[]> list2 = Arrays.asList(
            new double[]{2.0, 3.0}
    ); // avg = [2.0, 3.0]

    double nSim = VectorUtil.nSimilarity(list1, list2);
    assertEquals(1.0, nSim, DELTA);
}

@Test
public void testComputeIdfWeightDict() {
    Map<String, Double> counts = new HashMap<>();
    counts.put("term", 1.0);

    Map<String, Double> idf = VectorUtil.computeIdfWeightDict(9, counts);
    // weight = log((9 + 1) / (1 + 1)) = log(5)
    assertEquals(Math.log(5.0), idf.get("term"), DELTA);
}

}
