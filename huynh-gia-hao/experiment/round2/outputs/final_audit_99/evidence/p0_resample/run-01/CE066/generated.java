
// BEGIN GeneratedTest_CE066
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE066 {

    @Test
    public void testInitializationAndMapCopy() {
        char[][] original = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', '.', 'G', '#'},
            {'#', '.', 'X', '.', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(original);

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
        assertEquals(1, game.getTargetCount());
        assertEquals(1, game.getTargets().size());
        assertArrayEquals(new int[]{1, 3}, game.getTargets().get(0));
        assertEquals(1, game.getBoxes().size());
        assertArrayEquals(new int[]{2, 2}, game.getBoxes().get(0));
        assertFalse(game.isGameOver());

        original[1][1] = '.';
        assertEquals('O', game.getMap()[1][1]);
    }

    @Test
    public void testMovementAndWalls() {
        char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', '.', 'G', '#'},
            {'#', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertFalse(game.move('w'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());

        assertFalse(game.move('a'));
        assertEquals(1, game.getPlayerCol());

        assertFalse(game.move('d'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());

        assertFalse(game.move('s'));
        assertEquals(2, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());

        assertFalse(game.move('x'));
        assertEquals(2, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
    }

    @Test
    public void testPushBoxAndBlockedPush() {
        char[][] map = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', 'O', 'X', '.', 'G', '#'},
            {'#', '#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertFalse(game.move('d'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
        assertArrayEquals(new int[]{1, 3}, game.getBoxes().get(0));

        assertFalse(game.move('d'));
        assertEquals(3, game.getPlayerCol());
        assertArrayEquals(new int[]{1, 4}, game.getBoxes().get(0));

        assertFalse(game.move('d'));
        assertEquals(3, game.getPlayerCol());
        assertArrayEquals(new int[]{1, 4}, game.getBoxes().get(0));

        assertFalse(game.checkWin());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testWinAndGameOverBehavior() {
        char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', 'X', '.', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame game = new PushBoxGame(map);

        assertEquals(0, game.getTargetCount());
        assertTrue(game.checkWin());
        assertTrue(game.isGameOver());

        assertTrue(game.move('d'));
        assertEquals(2, game.getPlayerCol());
        assertArrayEquals(new int[]{1, 3}, game.getBoxes().get(0));
        assertTrue(game.isGameOver());

        char[][] otherMap = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', 'G', 'X', '#'},
            {'#', '#', '#', '#', '#'}
        };

        PushBoxGame other = new PushBoxGame(otherMap);

        assertFalse(other.checkWin());
        assertFalse(other.isGameOver());
        assertFalse(other.move('d'));
        assertEquals(2, other.getPlayerCol());
        assertFalse(other.isGameOver());
    }
}
// END GeneratedTest_CE066
