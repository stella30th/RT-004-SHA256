package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE049 {

private static final double DELTA = 1e-3;

@Test
public void testKappaPerfectAgreement() {
    // Complete agreement diagonal
    int[][] perfect = {
            {10, 0},
            {0, 10}
    };
    double k = KappaCalculator.kappa(perfect, 2);
    assertEquals(1.0, k, DELTA);
}

@Test
public void testKappaStandardExample() {
    int[][] testData = {
            {2, 1, 1},
            {1, 2, 1},
            {1, 1, 2}
    };
    double k = KappaCalculator.kappa(testData, 3);
    assertEquals(0.25, k, DELTA);
}

@Test
public void testFleissKappaPerfectAgreement() {
    // 5 raters, 3 subjects, 2 categories, all agree on subject rating
    int[][] data = {
            {5, 0},
            {5, 0},
            {0, 5}
    };
    double fk = KappaCalculator.fleissKappa(data, 3, 2, 5);
    assertEquals(1.0, fk, DELTA);
}

@Test
public void testFleissKappaExample() {
    int[][] testData = {
            {0, 0, 0, 0, 14},
            {0, 2, 6, 4, 2},
            {0, 0, 3, 5, 6},
            {0, 3, 9, 2, 0},
            {2, 2, 8, 1, 1},
            {7, 7, 0, 0, 0},
            {3, 2, 6, 3, 0},
            {2, 5, 3, 2, 2},
            {6, 5, 2, 1, 0},
            {0, 2, 2, 3, 7}
    };
    double fk = KappaCalculator.fleissKappa(testData, 10, 5, 14);
    // Known benchmark Fleiss' kappa for this dataset is ~0.2099
    assertEquals(0.2099, fk, 1e-2);
}

}
