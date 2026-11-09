
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE066 {

    @Test
    public void initialStateIsParsedCorrectly() {
        char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', ' ', 'G', '#'},
            {'#', ' ', 'X', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
        assertEquals(1, game.getTargetCount());
        assertEquals(1, game.getBoxes().size());
        assertArrayEquals(
            new int[]{2, 2},
            game.getBoxes().get(0)
        );
        assertFalse(game.checkWin());
    }

    @Test
    public void validMoveUpdatesPlayerPosition() {
        char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', ' ', 'G', '#'},
            {'#', ' ', 'X', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertFalse(game.move('d'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());

        assertFalse(game.isGameOver());
    }

    @Test
    public void pushingBoxOntoGoalWinsGame() {
        char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', 'X', 'G', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertFalse(game.checkWin());

        assertTrue(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());

        assertArrayEquals(
            new int[]{1, 3},
            game.getBoxes().get(0)
        );

        assertTrue(game.isGameOver());
    }

    @Test
    public void blockedPushDoesNotMovePlayerOrBox() {
        char[][] map = {
            {'#', '#', '#', '#'},
            {'#', 'O', 'X', '#'},
            {'#', 'G', ' ', '#'},
            {'#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertFalse(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());

        assertArrayEquals(
            new int[]{1, 2},
            game.getBoxes().get(0)
        );

        assertFalse(game.isGameOver());
    }

    @Test
    public void zeroTargetMapReturnsWinEvenOnBlockedPush() {
        char[][] map = {
            {'#', '#', '#', '#'},
            {'#', 'O', 'X', '#'},
            {'#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertEquals(0, game.getTargetCount());

        assertTrue(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());

        assertArrayEquals(
            new int[]{1, 2},
            game.getBoxes().get(0)
        );

        assertTrue(game.isGameOver());
    }
}
