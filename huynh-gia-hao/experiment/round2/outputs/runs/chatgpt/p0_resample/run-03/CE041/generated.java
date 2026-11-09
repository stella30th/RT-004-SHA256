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
    public void testFirstMoveUsesX() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(2, 2));
        assertEquals('X', game.board[2][2]);
        assertNull(game.checkWinner());
    }

    @Test
    public void testPlayersAlternateTurns() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertTrue(game.makeMove(0, 1));
        assertTrue(game.makeMove(1, 0));
        assertTrue(game.makeMove(1, 1));

        assertEquals('X', game.board[0][0]);
        assertEquals('O', game.board[0][1]);
        assertEquals('X', game.board[1][0]);
        assertEquals('O', game.board[1][1]);
    }

    @Test
    public void testOccupiedCellRejectsMove() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertFalse(game.makeMove(0, 0));

        assertEquals('X', game.board[0][0]);
    }

    @Test
    public void testRejectedMoveDoesNotChangeTurn() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertFalse(game.makeMove(0, 0));
        assertTrue(game.makeMove(0, 1));

        assertEquals('O', game.board[0][1]);
    }

    @Test
    public void testFourHorizontalPiecesDoNotWin() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 4; col++) {
            assertTrue(game.makeMove(2, col));
            assertTrue(game.makeMove(0, col));
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testHorizontalWinForX() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 4; col++) {
            assertTrue(game.makeMove(2, col));
            assertTrue(game.makeMove(0, col));
        }

        assertTrue(game.makeMove(2, 4));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testHorizontalWinForO() {
        GomokuGame game = new GomokuGame(7);

        int[][] xMoves = {
                {0, 0}, {0, 2}, {0, 4}, {2, 0}, {2, 2}
        };

        for (int i = 0; i < 5; i++) {
            assertTrue(game.makeMove(xMoves[i][0], xMoves[i][1]));
            assertTrue(game.makeMove(5, i));
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testVerticalWinForX() {
        GomokuGame game = new GomokuGame(6);

        for (int row = 0; row < 4; row++) {
            assertTrue(game.makeMove(row, 2));
            assertTrue(game.makeMove(row, 0));
        }

        assertTrue(game.makeMove(4, 2));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testVerticalWinForO() {
        GomokuGame game = new GomokuGame(7);

        int[][] xMoves = {
                {0, 0}, {0, 2}, {0, 4}, {2, 0}, {2, 2}
        };

        for (int row = 0; row < 5; row++) {
            assertTrue(game.makeMove(xMoves[row][0], xMoves[row][1]));
            assertTrue(game.makeMove(row, 5));
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
    public void testDiagonalWinForO() {
        GomokuGame game = new GomokuGame(7);

        int[][] xMoves = {
                {0, 6}, {1, 6}, {2, 6}, {3, 6}, {5, 6}
        };

        for (int i = 0; i < 5; i++) {
            assertTrue(game.makeMove(xMoves[i][0], xMoves[i][1]));
            assertTrue(game.makeMove(i, i));
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testFiveInARowNearBoardEdge() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 5; col++) {
            game.board[4][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
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
    public void testCheckFiveInARowWithVerticalDirection() {
        GomokuGame game = new GomokuGame(7);

        for (int row = 1; row <= 5; row++) {
            game.board[row][3] = 'O';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{1, 0}));
    }

    @Test
    public void testCheckFiveInARowRejectsFourPieces() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 4; col++) {
            game.board[2][col] = 'X';
        }

        assertFalse(game.checkFiveInARow(2, 1, new int[]{0, 1}));
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
    public void testMixedSymbolsDoNotFormWinningLine() {
        GomokuGame game = new GomokuGame(5);

        game.board[2][0] = 'X';
        game.board[2][1] = 'X';
        game.board[2][2] = 'O';
        game.board[2][3] = 'X';
        game.board[2][4] = 'X';

        assertNull(game.checkWinner());
    }

    @Test
    public void testSixConsecutivePiecesStillWin() {
        GomokuGame game = new GomokuGame(7);

        for (int col = 0; col < 6; col++) {
            game.board[3][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testBoardSmallerThanFiveHasNoWinner() {
        GomokuGame game = new GomokuGame(3);

        for (int col = 0; col < 3; col++) {
            game.board[0][col] = 'X';
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testEmptyBoardHasNoWinner() {
        GomokuGame game = new GomokuGame(0);

        assertNull(game.checkWinner());
        assertEquals(0, game.board.length);
    }

    @Test
    public void testMultipleWinningLinesReturnFirstScannedWinner() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 5; col++) {
            game.board[0][col] = 'O';
            game.board[5][col] = 'X';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testWinnerRemainsAfterAdditionalMove() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 5; col++) {
            game.board[2][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());

        assertTrue(game.makeMove(5, 5));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNegativeRowThrowsException() {
        GomokuGame game = new GomokuGame(5);
        game.makeMove(-1, 0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testColumnBeyondBoardThrowsException() {
        GomokuGame game = new GomokuGame(5);
        game.makeMove(0, 5);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testNegativeBoardSizeThrowsException() {
        new GomokuGame(-1);
    }

    @Test
    public void testIndependentGameInstances() {
        GomokuGame first = new GomokuGame(5);
        GomokuGame second = new GomokuGame(5);

        assertTrue(first.makeMove(0, 0));

        assertEquals('X', first.board[0][0]);
        assertEquals(' ', second.board[0][0]);

        assertTrue(second.makeMove(1, 1));
        assertEquals('X', second.board[1][1]);

        assertNull(first.checkWinner());
        assertNull(second.checkWinner());
    }
}
// END GeneratedTest_CE041