package org.example;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GeneratedTest_CE084 {

@Test
public void testEvaluateExpression() {
    TwentyFourPointGame game = new TwentyFourPointGame();
    assertTrue(game.evaluateExpression("(2 + 2) * 6"));
    assertTrue(game.evaluateExpression("8 * 3"));
    assertTrue(game.evaluateExpression("24"));
    assertFalse(game.evaluateExpression("10 + 10"));
    assertFalse(game.evaluateExpression("invalid_syntax"));
}

@Test
public void testAnswerCorrectExpressionUsingGivenNumbers() throws Exception {
    TwentyFourPointGame game = new TwentyFourPointGame();
    Field numsField = TwentyFourPointGame.class.getDeclaredField("nums");
    numsField.setAccessible(true);
    numsField.set(game, Arrays.asList(4, 6, 2, 2));

    assertTrue(game.answer("(4 + 2) * (6 - 2)"));
}

@Test
public void testAnswerFailsWhenWrongNumbersUsed() throws Exception {
    TwentyFourPointGame game = new TwentyFourPointGame();
    Field numsField = TwentyFourPointGame.class.getDeclaredField("nums");
    numsField.setAccessible(true);
    numsField.set(game, Arrays.asList(1, 2, 3, 4));

    // 8 * 3 = 24, but 8 is not in [1, 2, 3, 4]
    assertFalse(game.answer("8 * 3"));
}

@Test
public void testAnswerPassGeneratesNewCards() {
    TwentyFourPointGame game = new TwentyFourPointGame();
    assertFalse(game.answer("pass"));
    assertEquals(4, game.getNums().size());
}

}
