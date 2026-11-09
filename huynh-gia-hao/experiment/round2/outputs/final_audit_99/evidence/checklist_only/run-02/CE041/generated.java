// BEGIN GeneratedTest_CE041
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE041 {

    @Test public void emptyBoardAndAlternatingMoves() {
        GomokuGame g = new GomokuGame(5);
        assertNull(g.checkWinner());
        assertEquals(' ', g.board[0][0]);
        assertTrue(g.makeMove(1, 1));
        assertTrue(g.makeMove(1, 2));
        assertEquals('X', g.board[1][1]);
        assertEquals('O', g.board[1][2]);
    }
    @Test public void occupiedMoveDoesNotChangeTurn() {
        GomokuGame g = new GomokuGame(5);
        assertTrue(g.makeMove(0, 0));
        assertFalse(g.makeMove(0, 0));
        assertTrue(g.makeMove(0, 1));
        assertEquals('O', g.board[0][1]);
    }
    @Test public void horizontalFiveWinsButFourDoesNot() {
        GomokuGame g = new GomokuGame(6);
        for (int c = 0; c < 4; c++) {
            assertTrue(g.makeMove(2, c));
            assertTrue(g.makeMove(0, c));
        }
        assertNull(g.checkWinner());
        assertTrue(g.makeMove(2, 4));
        assertEquals(Character.valueOf('X'), g.checkWinner());
    }
    @Test public void verticalAndDiagonalWins() {
        GomokuGame vertical = new GomokuGame(6);
        GomokuGame diagonal = new GomokuGame(6);
        GomokuGame backwards = new GomokuGame(6);
        for (int i = 0; i < 5; i++) {
            vertical.board[i][2] = 'O';
            diagonal.board[i][i] = 'X';
            backwards.board[i][4 - i] = 'O';
        }
        assertEquals(Character.valueOf('O'), vertical.checkWinner());
        assertEquals(Character.valueOf('X'), diagonal.checkWinner());
        assertEquals(Character.valueOf('O'), backwards.checkWinner());
    }
    @Test public void sequenceCanBeFoundFromMiddle() {
        GomokuGame g = new GomokuGame(7);
        for (int c = 1; c <= 5; c++) g.board[3][c] = 'X';
        assertTrue(g.checkFiveInARow(3, 3, new int[]{0, 1}));
        g.board[3][4] = 'O';
        assertFalse(g.checkFiveInARow(3, 3, new int[]{0, 1}));
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class) public void invalidMoveThrows() {
        new GomokuGame(5).makeMove(-1, 0);
    }

    @Test public void sixConsecutivePiecesWin() { GomokuGame g = new GomokuGame(7); for(int i=0;i<6;i++) g.board[2][i]='X'; assertEquals(Character.valueOf('X'),g.checkWinner()); }
}
// END GeneratedTest_CE041
