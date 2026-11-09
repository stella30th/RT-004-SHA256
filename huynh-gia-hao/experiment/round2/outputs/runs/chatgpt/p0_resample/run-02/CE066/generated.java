// BEGIN GeneratedTest_CE066
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class GeneratedTest_CE066 {

    private PushBoxGame createGame(String... rows) {
        char[][] map = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            map[i] = rows[i].toCharArray();
        }
        return new PushBoxGame(map);
    }

    private boolean containsPosition(List<int[]> positions, int row, int col) {
        for (int[] position : positions) {
            if (position[0] == row && position[1] == col) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testInitialGameState() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#   #",
                "#####"
        );

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
        assertEquals(1, game.getTargetCount());
        assertEquals(1, game.getTargets().size());
        assertEquals(1, game.getBoxes().size());
        assertTrue(containsPosition(game.getTargets(), 1, 3));
        assertTrue(containsPosition(game.getBoxes(), 1, 2));
        assertFalse(game.isGameOver());
        assertFalse(game.checkWin());
    }

    @Test
    public void testConstructorCopiesInputMap() {
        char[][] original = {
                "#####".toCharArray(),
                "#OXG#".toCharArray(),
                "#####".toCharArray()
        };

        PushBoxGame game = new PushBoxGame(original);
        original[1][1] = ' ';
        original[1][2] = ' ';

        assertEquals('O', game.getMap()[1][1]);
        assertEquals('X', game.getMap()[1][2]);
        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
    }

    @Test
    public void testPlayerMovesInFourDirections() {
        PushBoxGame game = createGame(
                "#####",
                "#   #",
                "# O #",
                "#   #",
                "#####",
                "#G X#"
        );

        assertFalse(game.move('w'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());

        assertFalse(game.move('s'));
        assertEquals(2, game.getPlayerRow());

        assertFalse(game.move('a'));
        assertEquals(1, game.getPlayerCol());

        assertFalse(game.move('d'));
        assertEquals(2, game.getPlayerCol());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testWallBlocksMovement() {
        PushBoxGame game = createGame(
                "#####",
                "#O#G#",
                "# X #",
                "#####"
        );

        assertFalse(game.move('d'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());

        assertFalse(game.move('w'));
        assertEquals(1, game.getPlayerRow());

        assertFalse(game.move('a'));
        assertEquals(1, game.getPlayerCol());
    }

    @Test
    public void testOutOfBoundsMovementIsBlocked() {
        PushBoxGame game = createGame(
                "O  ",
                " XG",
                "   "
        );

        assertFalse(game.move('w'));
        assertFalse(game.move('a'));

        assertEquals(0, game.getPlayerRow());
        assertEquals(0, game.getPlayerCol());
    }

    @Test
    public void testInvalidDirectionDoesNotMovePlayer() {
        PushBoxGame game = createGame(
                "#####",
                "#O  #",
                "# XG#",
                "#####"
        );

        assertFalse(game.move('z'));
        assertFalse(game.move('W'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
    }

    @Test
    public void testPushBoxOntoTargetWins() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        assertFalse(game.isGameOver());
        assertTrue(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 3));
        assertTrue(game.checkWin());
        assertTrue(game.isGameOver());
    }

    @Test
    public void testPushBoxIntoEmptyCell() {
        PushBoxGame game = createGame(
                "#######",
                "#OX  G#",
                "#######"
        );

        assertFalse(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 3));
        assertFalse(game.isGameOver());
    }

    @Test
    public void testCannotPushBoxIntoWall() {
        PushBoxGame game = createGame(
                "#####",
                "#OX##",
                "#  G#",
                "#####"
        );

        assertFalse(game.move('d'));

        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 2));
        assertFalse(game.isGameOver());
    }

    @Test
    public void testCannotPushBoxOutsideMap() {
        PushBoxGame game = createGame(
                "OX",
                " G"
        );

        assertFalse(game.move('d'));

        assertEquals(0, game.getPlayerRow());
        assertEquals(0, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 0, 1));
    }

    @Test
    public void testCannotPushTwoAdjacentBoxes() {
        PushBoxGame game = createGame(
                "#######",
                "#OXX G#",
                "#######"
        );

        assertFalse(game.move('d'));

        assertEquals(1, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 2));
        assertTrue(containsPosition(game.getBoxes(), 1, 3));
    }

    @Test
    public void testPushBoxVertically() {
        PushBoxGame game = createGame(
                "#####",
                "# O #",
                "# X #",
                "# G #",
                "#####"
        );

        assertTrue(game.move('s'));

        assertEquals(2, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 3, 2));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testPushBoxLeft() {
        PushBoxGame game = createGame(
                "#####",
                "#GX O",
                "#####"
        );

        assertFalse(game.move('a'));
        assertEquals(1, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 2));

        assertTrue(game.move('a'));
        assertEquals(2, game.getPlayerCol());
        assertTrue(containsPosition(game.getBoxes(), 1, 1));
    }

    @Test
    public void testMultipleTargetsRequireAllToBeOccupied() {
        PushBoxGame game = createGame(
                "########",
                "#OXG   #",
                "#OXG   #",
                "########"
        );

        assertEquals(2, game.getTargetCount());
        assertEquals(2, game.getBoxes().size());

        assertFalse(game.move('d'));
        assertTrue(containsPosition(game.getBoxes(), 1, 3));
        assertTrue(containsPosition(game.getBoxes(), 2, 2));
        assertFalse(game.checkWin());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameWithoutTargetsWinsOnCheck() {
        PushBoxGame game = createGame(
                "#####",
                "#O X#",
                "#####"
        );

        assertEquals(0, game.getTargetCount());
        assertFalse(game.isGameOver());
        assertTrue(game.checkWin());
        assertTrue(game.isGameOver());
    }

    @Test
    public void testEmptyMapWinsOnCheck() {
        PushBoxGame game = new PushBoxGame(new char[0][]);

        assertEquals(0, game.getPlayerRow());
        assertEquals(0, game.getPlayerCol());
        assertEquals(0, game.getTargetCount());
        assertTrue(game.checkWin());
        assertTrue(game.isGameOver());
    }

    @Test
    public void testMoveReturnsWinStateEvenWhenBlocked() {
        PushBoxGame game = createGame(
                "###",
                "#O#",
                "###"
        );

        assertTrue(game.move('w'));
        assertEquals(1, game.getPlayerRow());
        assertEquals(1, game.getPlayerCol());
        assertTrue(game.isGameOver());
    }

    @Test
    public void testMovingAfterWinCanClearWinState() {
        PushBoxGame game = createGame(
                "######",
                "#OXG #",
                "######"
        );

        assertTrue(game.move('d'));
        assertTrue(game.isGameOver());

        assertFalse(game.move('d'));
        assertFalse(game.isGameOver());
        assertTrue(containsPosition(game.getBoxes(), 1, 4));
    }

    @Test
    public void testGetBoxesReturnsIndependentList() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        List<int[]> boxes = game.getBoxes();
        boxes.clear();

        assertEquals(1, game.getBoxes().size());
        assertTrue(containsPosition(game.getBoxes(), 1, 2));
    }

    @Test
    public void testGetTargetsReturnsIndependentList() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        List<int[]> targets = game.getTargets();
        targets.clear();

        assertEquals(1, game.getTargets().size());
        assertEquals(1, game.getTargetCount());
    }

    @Test
    public void testGetBoxesSharesPositionArrays() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        List<int[]> boxes = game.getBoxes();
        boxes.get(0)[0] = 2;

        assertTrue(containsPosition(game.getBoxes(), 2, 2));
    }

    @Test
    public void testGetTargetsSharesPositionArrays() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        List<int[]> targets = game.getTargets();
        targets.get(0)[0] = 2;

        assertTrue(containsPosition(game.getTargets(), 2, 3));
        assertEquals(1, game.getTargetCount());
    }

    @Test
    public void testGetMapReturnsInternalMap() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        char[][] map = game.getMap();
        map[0][0] = '.';

        assertEquals('.', game.getMap()[0][0]);
    }

    @Test
    public void testMapCharactersRemainUnchangedAfterMove() {
        PushBoxGame game = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        assertTrue(game.move('d'));

        assertEquals('O', game.getMap()[1][1]);
        assertEquals('X', game.getMap()[1][2]);
        assertEquals('G', game.getMap()[1][3]);
    }

    @Test
    public void testLastPlayerMarkerDeterminesInitialPosition() {
        PushBoxGame game = createGame(
                "#####",
                "#O G#",
                "# OX#",
                "#####"
        );

        assertEquals(2, game.getPlayerRow());
        assertEquals(2, game.getPlayerCol());
        assertEquals(1, game.getBoxes().size());
        assertEquals(1, game.getTargetCount());
    }

    @Test
    public void testIndependentGameInstances() {
        PushBoxGame first = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        PushBoxGame second = createGame(
                "#####",
                "#OXG#",
                "#####"
        );

        assertTrue(first.move('d'));
        assertFalse(second.isGameOver());

        assertTrue(containsPosition(first.getBoxes(), 1, 3));
        assertTrue(containsPosition(second.getBoxes(), 1, 2));
        assertEquals(1, second.getPlayerCol());
    }
}
// END GeneratedTest_CE066