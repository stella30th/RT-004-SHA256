package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneratedTest_CE013 {

@Test
public void testCalculateHandValueRegularAndFaceCards() {
    BlackjackGame game = new BlackjackGame();
    List<String> hand = Arrays.asList("2H", "7D", "KC");
    assertEquals(19, game.calculateHandValue(hand));
}

@Test
public void testCalculateHandValueWithAceAdjustment() {
    BlackjackGame game = new BlackjackGame();
    List<String> handBlackjack = Arrays.asList("AS", "KD");
    assertEquals(21, game.calculateHandValue(handBlackjack));

    List<String> handMultipleAces = Arrays.asList("AS", "AH", "9D");
    assertEquals(21, game.calculateHandValue(handMultipleAces));
}

@Test
public void testCheckWinnerNormalScores() {
    BlackjackGame game = new BlackjackGame();
    List<String> playerWinning = Arrays.asList("10S", "9D");
    List<String> dealerLosing = Arrays.asList("10H", "8C");
    assertEquals("Player wins", game.checkWinner(playerWinning, dealerLosing));

    List<String> playerLosing = Arrays.asList("7S", "8D");
    List<String> dealerWinning = Arrays.asList("10H", "9C");
    assertEquals("Dealer wins", game.checkWinner(playerLosing, dealerWinning));
}

@Test
public void testCheckWinnerWithBusts() {
    BlackjackGame game = new BlackjackGame();

    List<String> playerBust = Arrays.asList("10S", "9D", "5C"); // 24
    List<String> dealerUnder = Arrays.asList("10H", "8C");      // 18
    assertEquals("Dealer wins", game.checkWinner(playerBust, dealerUnder));

    List<String> playerUnder = Arrays.asList("10S", "8D");      // 18
    List<String> dealerBust = Arrays.asList("10H", "9C", "7D"); // 26
    assertEquals("Player wins", game.checkWinner(playerUnder, dealerBust));

    List<String> dealerBustHigher = Arrays.asList("10H", "9C", "6D"); // 25
    assertEquals("Player wins", game.checkWinner(playerBust, dealerBustHigher));
}

}
