package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE081 {

@Test
public void testMakeMoveAlternatesPlayer() {
    TicTacToe game = new TicTacToe();
    assertEquals('X', game.getCurrentPlayer());

    assertTrue(game.makeMove(0, 0));
    assertEquals('O', game.getCurrentPlayer());

    // Cannot move to already occupied cell
    assertFalse(game.makeMove(0, 0));
    assertEquals('O', game.getCurrentPlayer());

    assertTrue(game.makeMove(1, 1));
    assertEquals('X', game.getCurrentPlayer());
}

@Test
public void testCheckWinnerRows() {
    TicTacToe game = new TicTacToe();
    game.makeMove(0, 0); // X
    game.makeMove(1, 0); // O
    game.makeMove(0, 1); // X
    game.makeMove(1, 1); // O
    assertNull(game.checkWinner());

    game.makeMove(0, 2); // X completes row 0
    assertEquals(Character.valueOf('X'), game.checkWinner());
}

@Test
public void testCheckWinnerDiagonals() {
    TicTacToe game = new TicTacToe();
    game.makeMove(0, 0); // X
    game.makeMove(0, 1); // O
    game.makeMove(1, 1); // X
    game.makeMove(0, 2); // O
    game.makeMove(2, 2); // X completes main diagonal
    assertEquals(Character.valueOf('X'), game.checkWinner());

    TicTacToe gameAntiDiag = new TicTacToe();
    gameAntiDiag.makeMove(0, 0); // X
    gameAntiDiag.makeMove(0, 2); // O
    gameAntiDiag.makeMove(1, 0); // X
    gameAntiDiag.makeMove(1, 1); // O
    gameAntiDiag.makeMove(2, 1); // X
    gameAntiDiag.makeMove(2, 0); // O completes anti-diagonal
    assertEquals(Character.valueOf('O'), gameAntiDiag.checkWinner());
}

@Test
public void testIsBoardFull() {
    TicTacToe game = new TicTacToe();
    assertFalse(game.isBoardFull());

    // Fill board with a draw pattern
    // X O X
    // X X O
    // O X O
    game.makeMove(0, 0); // X
    game.makeMove(0, 1); // O
    game.makeMove(0, 2); // X
    game.makeMove(1, 2); // O
    game.makeMove(1, 0); // X
    game.makeMove(2, 0); // O
    game.makeMove(1, 1); // X
    game.makeMove(2, 2); // O
    game.makeMove(2, 1); // X

    assertTrue(game.isBoardFull());
    assertNull(game.checkWinner());
}

}
