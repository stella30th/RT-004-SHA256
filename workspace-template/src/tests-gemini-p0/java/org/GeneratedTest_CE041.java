package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE041 {

@Test
public void testMakeMoveAlternatesPlayers() {
    GomokuGame game = new GomokuGame(10);
    assertTrue(game.makeMove(0, 0)); // X moves
    assertFalse(game.makeMove(0, 0)); // already occupied

    assertTrue(game.makeMove(0, 1)); // O moves
    assertEquals('X', game.board[0][0]);
    assertEquals('O', game.board[0][1]);
}

@Test
public void testHorizontalWin() {
    GomokuGame game = new GomokuGame(10);
    // X places row 1: (1,0), (1,1), (1,2), (1,3), (1,4)
    // O places row 2: (2,0), (2,1), (2,2), (2,3)
    game.makeMove(1, 0); // X
    game.makeMove(2, 0); // O
    game.makeMove(1, 1); // X
    game.makeMove(2, 1); // O
    game.makeMove(1, 2); // X
    game.makeMove(2, 2); // O
    game.makeMove(1, 3); // X
    game.makeMove(2, 3); // O
    assertNull(game.checkWinner());

    game.makeMove(1, 4); // X 5th move
    assertEquals(Character.valueOf('X'), game.checkWinner());
}

@Test
public void testVerticalWin() {
    GomokuGame game = new GomokuGame(10);
    // O wins vertically at column 5
    game.makeMove(0, 0); // X
    game.makeMove(1, 5); // O
    game.makeMove(0, 1); // X
    game.makeMove(2, 5); // O
    game.makeMove(0, 2); // X
    game.makeMove(3, 5); // O
    game.makeMove(0, 3); // X
    game.makeMove(4, 5); // O
    game.makeMove(9, 9); // X
    game.makeMove(5, 5); // O 5th in a row
    assertEquals(Character.valueOf('O'), game.checkWinner());
}

@Test
public void testDiagonalWin() {
    GomokuGame game = new GomokuGame(10);
    // X wins main diagonal (0,0) to (4,4)
    for (int i = 0; i < 4; i++) {
        game.makeMove(i, i);       // X
        game.makeMove(i, 9 - i);   // O
    }
    assertNull(game.checkWinner());
    game.makeMove(4, 4); // X completes diagonal
    assertEquals(Character.valueOf('X'), game.checkWinner());
}

}
