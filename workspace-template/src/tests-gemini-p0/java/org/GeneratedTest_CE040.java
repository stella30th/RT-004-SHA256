package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE040 {

private static final double DELTA = 1e-2;

@Test
public void testGetBMI() {
    // Height: 1.75m, Weight: 70kg -> BMI = 70 / (1.75^2) = 22.857
    FitnessTracker tracker = new FitnessTracker(1.75, 70.0, 25, "male");
    assertEquals(22.86, tracker.getBMI(), DELTA);
}

@Test
public void testConditionJudgeMale() {
    // Male standards: [20, 25]
    FitnessTracker normalMale = new FitnessTracker(1.75, 70.0, 25, "male"); // BMI ~ 22.86
    assertEquals(0, normalMale.conditionJudge());

    FitnessTracker overweightMale = new FitnessTracker(1.75, 90.0, 25, "male"); // BMI ~ 29.38 > 25
    assertEquals(1, overweightMale.conditionJudge());

    FitnessTracker underweightMale = new FitnessTracker(1.75, 55.0, 25, "male"); // BMI ~ 17.96 < 20
    assertEquals(-1, underweightMale.conditionJudge());
}

@Test
public void testConditionJudgeFemale() {
    // Female standards: [19, 24]
    FitnessTracker normalFemale = new FitnessTracker(1.65, 58.0, 25, "female"); // BMI ~ 21.30
    assertEquals(0, normalFemale.conditionJudge());

    FitnessTracker overweightFemale = new FitnessTracker(1.65, 75.0, 25, "female"); // BMI ~ 27.55 > 24
    assertEquals(1, overweightFemale.conditionJudge());

    FitnessTracker underweightFemale = new FitnessTracker(1.65, 45.0, 25, "female"); // BMI ~ 16.53 < 19
    assertEquals(-1, underweightFemale.conditionJudge());
}

@Test
public void testCalculateCalorieIntake() {
    FitnessTracker normalMale = new FitnessTracker(175.0, 70.0, 25, "male");
    // BMR = 10*70 + 6.25*175 - 5*25 + 5 = 700 + 1093.75 - 125 + 5 = 1673.75
    // conditionJudge for height 175m -> BMI << 20 -> condition -1 -> multiplier 1.6
    double intake = normalMale.calculateCalorieIntake();
    assertEquals(1673.75 * 1.6, intake, DELTA);
}

}
