// BEGIN GeneratedTest_CE041
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE041 {

    @Test
    public void testInitialBoardAndNoWinner() {
        GomokuGame game = new GomokuGame(5);
        assertNull(game.checkWinner());

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                assertEquals(' ', game.board[row][col]);
            }
        }
    }

    @Test
    public void testMakeMoveAlternatesPlayers() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertEquals('X', game.board[0][0]);

        assertTrue(game.makeMove(0, 1));
        assertEquals('O', game.board[0][1]);

        assertTrue(game.makeMove(1, 0));
        assertEquals('X', game.board[1][0]);

        assertNull(game.checkWinner());
    }

    @Test
    public void testOccupiedCellRejectsMoveWithoutChangingTurn() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertFalse(game.makeMove(0, 0));
        assertEquals('X', game.board[0][0]);

        assertTrue(game.makeMove(0, 1));
        assertEquals('O', game.board[0][1]);
    }

    @Test
    public void testHorizontalWinForX() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 4; col++) {
            assertTrue(game.makeMove(2, col));
            assertTrue(game.makeMove(0, col));
        }

        assertNull(game.checkWinner());
        assertTrue(game.makeMove(2, 4));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testVerticalWinForO() {
        GomokuGame game = new GomokuGame(6);

        for (int row = 0; row < 5; row++) {
            assertTrue(game.makeMove(row, 0));
            assertTrue(game.makeMove(row, 2));
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testVerticalWinForOWithoutXWin() {
        GomokuGame game = new GomokuGame(6);

        int[][] xMoves = {
            {0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}
        };

        for (int i = 0; i < 5; i++) {
            assertTrue(game.makeMove(xMoves[i][0], xMoves[i][1]));
            assertTrue(game.makeMove(i, 4));
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testDiagonalDownRightWin() {
        GomokuGame game = new GomokuGame(6);

        for (int i = 0; i < 4; i++) {
            assertTrue(game.makeMove(i, i));
            assertTrue(game.makeMove(i, 5));
        }

        assertTrue(game.makeMove(4, 4));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testDiagonalDownLeftWin() {
        GomokuGame game = new GomokuGame(6);

        for (int i = 0; i < 4; i++) {
            assertTrue(game.makeMove(i, 4 - i));
            assertTrue(game.makeMove(i, 5));
        }

        assertTrue(game.makeMove(4, 0));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFourInARowIsNotWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 4; i++) {
            assertTrue(game.makeMove(2, i));
            assertTrue(game.makeMove(0, i));
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testSeparatedPiecesDoNotWin() {
        GomokuGame game = new GomokuGame(7);

        game.board[3][0] = 'X';
        game.board[3][1] = 'X';
        game.board[3][2] = 'O';
        game.board[3][3] = 'X';
        game.board[3][4] = 'X';
        game.board[3][5] = 'X';

        assertNull(game.checkWinner());
    }

    @Test
    public void testFiveInARowDetectedFromMiddle() {
        GomokuGame game = new GomokuGame(7);

        for (int col = 1; col <= 5; col++) {
            game.board[3][col] = 'X';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{0, 1}));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testCheckFiveInARowRejectsShortSequence() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 4; col++) {
            game.board[2][col] = 'X';
        }

        assertFalse(game.checkFiveInARow(2, 1, new int[]{0, 1}));
        assertNull(game.checkWinner());
    }

    @Test
    public void testBoardSmallerThanFiveHasNoWinner() {
        GomokuGame game = new GomokuGame(3);

        game.board[0][0] = 'X';
        game.board[0][1] = 'X';
        game.board[0][2] = 'X';
        game.board[1][0] = 'X';
        game.board[2][0] = 'X';

        assertNull(game.checkWinner());
    }

    @Test
    public void testOutOfBoundsMoveThrowsException() {
        GomokuGame game = new GomokuGame(5);

        try {
            game.makeMove(-1, 0);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertNull(game.checkWinner());
        }
    }
}
// END GeneratedTest_CE041