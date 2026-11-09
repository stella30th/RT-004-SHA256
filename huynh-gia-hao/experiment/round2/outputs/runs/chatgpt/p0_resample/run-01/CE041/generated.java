// BEGIN GeneratedTest_CE041
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE041 {

    @Test
    public void testBoardInitializedWithEmptyCells() {
        GomokuGame game = new GomokuGame(5);

        assertEquals(5, game.board.length);
        for (int row = 0; row < 5; row++) {
            assertEquals(5, game.board[row].length);
            for (int col = 0; col < 5; col++) {
                assertEquals(' ', game.board[row][col]);
            }
        }
        assertNull(game.checkWinner());
    }

    @Test
    public void testFirstMoveUsesX() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(2, 3));
        assertEquals('X', game.board[2][3]);
        assertNull(game.checkWinner());
    }

    @Test
    public void testPlayersAlternateAfterSuccessfulMoves() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertTrue(game.makeMove(0, 1));
        assertTrue(game.makeMove(0, 2));
        assertTrue(game.makeMove(0, 3));

        assertEquals('X', game.board[0][0]);
        assertEquals('O', game.board[0][1]);
        assertEquals('X', game.board[0][2]);
        assertEquals('O', game.board[0][3]);
        assertNull(game.checkWinner());
    }

    @Test
    public void testOccupiedCellRejectsMoveWithoutChangingPlayer() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(1, 1));
        assertFalse(game.makeMove(1, 1));
        assertEquals('X', game.board[1][1]);

        assertTrue(game.makeMove(1, 2));
        assertEquals('O', game.board[1][2]);

        assertFalse(game.makeMove(1, 2));
        assertTrue(game.makeMove(1, 3));
        assertEquals('X', game.board[1][3]);
    }

    @Test
    public void testMovesAtBoardCorners() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));
        assertTrue(game.makeMove(0, 4));
        assertTrue(game.makeMove(4, 0));
        assertTrue(game.makeMove(4, 4));

        assertEquals('X', game.board[0][0]);
        assertEquals('O', game.board[0][4]);
        assertEquals('X', game.board[4][0]);
        assertEquals('O', game.board[4][4]);
        assertNull(game.checkWinner());
    }

    @Test
    public void testSingleCellBoard() {
        GomokuGame game = new GomokuGame(1);

        assertNull(game.checkWinner());
        assertTrue(game.makeMove(0, 0));
        assertEquals('X', game.board[0][0]);
        assertFalse(game.makeMove(0, 0));
        assertNull(game.checkWinner());
    }

    @Test
    public void testBoardSmallerThanFiveCannotHaveWinner() {
        GomokuGame game = new GomokuGame(4);

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                assertTrue(game.makeMove(row, col));
            }
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testEmptyBoardHasNoWinner() {
        GomokuGame game = new GomokuGame(10);

        assertNull(game.checkWinner());
    }

    @Test
    public void testFourConsecutiveHorizontalMarksDoNotWin() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 4; col++) {
            game.board[2][col] = 'X';
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testFourConsecutiveVerticalMarksDoNotWin() {
        GomokuGame game = new GomokuGame(5);

        for (int row = 0; row < 4; row++) {
            game.board[row][2] = 'O';
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testFourConsecutiveDiagonalMarksDoNotWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 4; i++) {
            game.board[i][i] = 'X';
        }

        assertNull(game.checkWinner());
    }

    @Test
    public void testFiveHorizontalXMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 5; col++) {
            game.board[2][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFiveHorizontalOMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 5; col++) {
            game.board[3][col] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testFiveVerticalXMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int row = 0; row < 5; row++) {
            game.board[row][1] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFiveVerticalOMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int row = 0; row < 5; row++) {
            game.board[row][4] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testFiveDownRightDiagonalXMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 5; i++) {
            game.board[i][i] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFiveDownRightDiagonalOMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 5; i++) {
            game.board[i][i] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testFiveDownLeftDiagonalXMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 5; i++) {
            game.board[i][4 - i] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFiveDownLeftDiagonalOMarksWin() {
        GomokuGame game = new GomokuGame(5);

        for (int i = 0; i < 5; i++) {
            game.board[i][4 - i] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testHorizontalWinInMiddleOfLargerBoard() {
        GomokuGame game = new GomokuGame(10);

        for (int col = 3; col <= 7; col++) {
            game.board[6][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testVerticalWinAtRightEdge() {
        GomokuGame game = new GomokuGame(10);

        for (int row = 5; row < 10; row++) {
            game.board[row][9] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testDiagonalWinOffsetFromOrigin() {
        GomokuGame game = new GomokuGame(10);

        for (int i = 0; i < 5; i++) {
            game.board[3 + i][2 + i] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testDownLeftDiagonalWinOffsetFromOrigin() {
        GomokuGame game = new GomokuGame(10);

        for (int i = 0; i < 5; i++) {
            game.board[2 + i][8 - i] = 'O';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testSixConsecutiveMarksStillWin() {
        GomokuGame game = new GomokuGame(10);

        for (int col = 1; col <= 6; col++) {
            game.board[4][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testSeparatedMarksDoNotFormWinningLine() {
        GomokuGame game = new GomokuGame(7);

        game.board[3][0] = 'X';
        game.board[3][1] = 'X';
        game.board[3][2] = ' ';
        game.board[3][3] = 'X';
        game.board[3][4] = 'X';
        game.board[3][5] = 'X';

        assertNull(game.checkWinner());
    }

    @Test
    public void testMixedPlayerMarksDoNotFormWinningLine() {
        GomokuGame game = new GomokuGame(5);

        game.board[2][0] = 'X';
        game.board[2][1] = 'X';
        game.board[2][2] = 'O';
        game.board[2][3] = 'X';
        game.board[2][4] = 'X';

        assertNull(game.checkWinner());
    }

    @Test
    public void testScatteredMarksDoNotProduceWinner() {
        GomokuGame game = new GomokuGame(7);

        game.board[0][0] = 'X';
        game.board[0][2] = 'X';
        game.board[1][4] = 'O';
        game.board[2][1] = 'O';
        game.board[3][3] = 'X';
        game.board[4][0] = 'O';
        game.board[5][5] = 'X';
        game.board[6][2] = 'O';

        assertNull(game.checkWinner());
    }

    @Test
    public void testCheckFiveInARowFromMiddleOfHorizontalLine() {
        GomokuGame game = new GomokuGame(7);

        for (int col = 1; col <= 5; col++) {
            game.board[3][col] = 'X';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{0, 1}));
        assertTrue(game.checkFiveInARow(3, 3, new int[]{0, -1}));
        assertFalse(game.checkFiveInARow(3, 3, new int[]{1, 0}));
    }

    @Test
    public void testCheckFiveInARowFromMiddleOfVerticalLine() {
        GomokuGame game = new GomokuGame(7);

        for (int row = 1; row <= 5; row++) {
            game.board[row][3] = 'O';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{1, 0}));
        assertTrue(game.checkFiveInARow(3, 3, new int[]{-1, 0}));
        assertFalse(game.checkFiveInARow(3, 3, new int[]{0, 1}));
    }

    @Test
    public void testCheckFiveInARowFromMiddleOfDiagonalLine() {
        GomokuGame game = new GomokuGame(7);

        for (int i = 1; i <= 5; i++) {
            game.board[i][i] = 'X';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{1, 1}));
        assertTrue(game.checkFiveInARow(3, 3, new int[]{-1, -1}));
        assertFalse(game.checkFiveInARow(3, 3, new int[]{1, -1}));
    }

    @Test
    public void testCheckFiveInARowFromMiddleOfReverseDiagonal() {
        GomokuGame game = new GomokuGame(7);

        for (int i = 1; i <= 5; i++) {
            game.board[i][6 - i] = 'O';
        }

        assertTrue(game.checkFiveInARow(3, 3, new int[]{1, -1}));
        assertTrue(game.checkFiveInARow(3, 3, new int[]{-1, 1}));
        assertFalse(game.checkFiveInARow(3, 3, new int[]{1, 1}));
    }

    @Test
    public void testCheckFiveInARowWithOnlyFourMatchingCells() {
        GomokuGame game = new GomokuGame(7);

        for (int col = 1; col <= 4; col++) {
            game.board[3][col] = 'X';
        }

        assertFalse(game.checkFiveInARow(3, 2, new int[]{0, 1}));
    }

    @Test
    public void testCheckFiveInARowStopsAtDifferentSymbol() {
        GomokuGame game = new GomokuGame(7);

        game.board[3][0] = 'X';
        game.board[3][1] = 'X';
        game.board[3][2] = 'O';
        game.board[3][3] = 'X';
        game.board[3][4] = 'X';
        game.board[3][5] = 'X';

        assertFalse(game.checkFiveInARow(3, 3, new int[]{0, 1}));
    }

    @Test
    public void testCheckFiveInARowAtBoardBoundary() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 5; col++) {
            game.board[0][col] = 'X';
        }

        assertTrue(game.checkFiveInARow(0, 0, new int[]{0, 1}));
        assertTrue(game.checkFiveInARow(0, 4, new int[]{0, 1}));
        assertFalse(game.checkFiveInARow(0, 0, new int[]{1, 0}));
    }

    @Test
    public void testCheckFiveInARowWithEmptyStartingCell() {
        GomokuGame game = new GomokuGame(5);

        assertFalse(game.checkFiveInARow(2, 2, new int[]{0, 1}));
    }

    @Test
    public void testWinnerDetectedThroughAlternatingMoves() {
        GomokuGame game = new GomokuGame(10);

        int[][] moves = {
            {5, 5}, {0, 0},
            {5, 4}, {0, 1},
            {5, 3}, {0, 2},
            {5, 2}, {0, 3}
        };

        for (int[] move : moves) {
            assertTrue(game.makeMove(move[0], move[1]));
        }

        assertNull(game.checkWinner());

        assertTrue(game.makeMove(5, 1));
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testOWinnerDetectedThroughAlternatingMoves() {
        GomokuGame game = new GomokuGame(10);

        int[][] moves = {
            {9, 9}, {0, 0},
            {9, 7}, {0, 1},
            {7, 9}, {0, 2},
            {7, 7}, {0, 3},
            {5, 9}, {0, 4}
        };

        for (int[] move : moves) {
            assertTrue(game.makeMove(move[0], move[1]));
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testCheckWinnerDoesNotModifyBoard() {
        GomokuGame game = new GomokuGame(5);

        for (int col = 0; col < 5; col++) {
            game.board[1][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
        assertEquals(Character.valueOf('X'), game.checkWinner());

        for (int col = 0; col < 5; col++) {
            assertEquals('X', game.board[1][col]);
        }
    }

    @Test
    public void testMovesAllowedAfterWinnerExists() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 5; col++) {
            game.board[0][col] = 'X';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());

        assertTrue(game.makeMove(5, 5));
        assertEquals('X', game.board[5][5]);
        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testFirstWinnerInBoardTraversalOrderIsReturned() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 5; col++) {
            game.board[0][col] = 'O';
            game.board[5][col] = 'X';
        }

        assertEquals(Character.valueOf('O'), game.checkWinner());
    }

    @Test
    public void testFirstWinnerCanBeXWhenMultipleWinnersExist() {
        GomokuGame game = new GomokuGame(6);

        for (int col = 0; col < 5; col++) {
            game.board[0][col] = 'X';
            game.board[5][col] = 'O';
        }

        assertEquals(Character.valueOf('X'), game.checkWinner());
    }

    @Test
    public void testInvalidNegativeRowThrowsArrayIndexOutOfBounds() {
        GomokuGame game = new GomokuGame(5);

        try {
            game.makeMove(-1, 0);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertNull(game.checkWinner());
        }
    }

    @Test
    public void testInvalidColumnThrowsArrayIndexOutOfBounds() {
        GomokuGame game = new GomokuGame(5);

        try {
            game.makeMove(0, 5);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertNull(game.checkWinner());
        }
    }

    @Test
    public void testInvalidRowThrowsArrayIndexOutOfBounds() {
        GomokuGame game = new GomokuGame(5);

        try {
            game.makeMove(5, 0);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertNull(game.checkWinner());
        }
    }

    @Test
    public void testInvalidNegativeColumnThrowsArrayIndexOutOfBounds() {
        GomokuGame game = new GomokuGame(5);

        try {
            game.makeMove(0, -1);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertNull(game.checkWinner());
        }
    }

    @Test
    public void testInvalidMoveDoesNotAdvancePlayer() {
        GomokuGame game = new GomokuGame(5);

        assertTrue(game.makeMove(0, 0));

        try {
            game.makeMove(-1, 0);
            fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException expected) {
            assertTrue(game.makeMove(0, 1));
            assertEquals('O', game.board[0][1]);
        }
    }

    @Test
    public void testZeroSizedBoardHasNoWinner() {
        GomokuGame game = new GomokuGame(0);

        assertEquals(0, game.board.length);
        assertNull(game.checkWinner());
    }

    @Test
    public void testNegativeBoardSizeThrowsException() {
        try {
            new GomokuGame(-1);
            fail("Expected NegativeArraySizeException");
        } catch (NegativeArraySizeException expected) {
            // Expected from array allocation.
        }
    }
}
// END GeneratedTest_CE041