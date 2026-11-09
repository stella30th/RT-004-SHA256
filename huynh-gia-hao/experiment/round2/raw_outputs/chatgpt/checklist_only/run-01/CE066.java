// BEGIN GeneratedTest_CE066
package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE066 {

    private PushBoxGame game(String... rows) {
        char[][] map = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) map[i] = rows[i].toCharArray();
        return new PushBoxGame(map);
    }
    private boolean at(java.util.List<int[]> positions, int r, int c) {
        for (int[] p : positions) if (p[0] == r && p[1] == c) return true;
        return false;
    }
    @Test public void initialStateAndClonedMap() {
        char[][] original = {"#####".toCharArray(), "#OXG#".toCharArray(), "#####".toCharArray()};
        PushBoxGame g = new PushBoxGame(original);
        original[1][1] = ' ';
        assertEquals('O', g.getMap()[1][1]);
        assertEquals(1, g.getPlayerRow());
        assertEquals(1, g.getPlayerCol());
        assertEquals(1, g.getTargetCount());
        assertTrue(at(g.getTargets(), 1, 3));
        assertTrue(at(g.getBoxes(), 1, 2));
        assertFalse(g.isGameOver());
        assertFalse(g.checkWin());
    }
    @Test public void playerMovesAndWallsBlock() {
        PushBoxGame g = game("#####", "#   #", "# O #", "# XG#", "#####");
        assertFalse(g.move('w'));
        assertEquals(1, g.getPlayerRow());
        assertFalse(g.move('a'));
        assertEquals(1, g.getPlayerCol());
        assertFalse(g.move('w'));
        assertEquals(1, g.getPlayerRow());
        assertFalse(g.move('z'));
        assertEquals(1, g.getPlayerCol());
    }
    @Test public void pushingOntoTargetWins() {
        PushBoxGame g = game("#####", "#OXG#", "#####");
        assertTrue(g.move('d'));
        assertEquals(2, g.getPlayerCol());
        assertTrue(at(g.getBoxes(), 1, 3));
        assertTrue(g.checkWin());
        assertTrue(g.isGameOver());
    }
    @Test public void cannotPushIntoWallOrAnotherBox() {
        PushBoxGame wall = game("######", "#OX#G#", "######");
        assertFalse(wall.move('d'));
        assertEquals(1, wall.getPlayerCol());
        PushBoxGame boxes = game("#######", "#OXX G#", "#######");
        assertFalse(boxes.move('d'));
        assertEquals(1, boxes.getPlayerCol());
    }
    @Test public void gameOverRecomputedAfterBoxMovesOffTarget() {
        PushBoxGame g = game("######", "#OXG #", "######");
        assertTrue(g.move('d'));
        assertFalse(g.move('d'));
        assertFalse(g.isGameOver());
        assertTrue(at(g.getBoxes(), 1, 4));
    }
    @Test public void zeroTargetsWinOnCheckAndListsAreCopies() {
        PushBoxGame g = game("#####", "#O X#", "#####");
        java.util.List<int[]> boxes = g.getBoxes();
        boxes.clear();
        assertEquals(1, g.getBoxes().size());
        assertTrue(g.checkWin());
        assertTrue(g.isGameOver());
    }

    @Test public void blockedMoveWithNoTargetsReportsWin() { PushBoxGame g=game("###","#O#","###"); assertTrue(g.move('w')); assertEquals(1,g.getPlayerRow()); }
}
// END GeneratedTest_CE066
