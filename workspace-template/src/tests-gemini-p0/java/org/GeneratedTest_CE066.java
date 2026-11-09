package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE066 {

@Test
public void testInitialization() {
    char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', 'X', 'G', '#'},
            {'#', '#', '#', '#', '#'}
    };
    PushBoxGame game = new PushBoxGame(map);

    assertEquals(1, game.getPlayerRow());
    assertEquals(1, game.getPlayerCol());
    assertEquals(1, game.getTargetCount());
    assertEquals(1, game.getTargets().size());
    assertEquals(1, game.getBoxes().size());
    assertFalse(game.isGameOver());
}

@Test
public void testMoveAndPushBoxToWin() {
    char[][] map = {
            {'#', '#', '#', '#', '#'},
            {'#', 'O', 'X', 'G', '#'},
            {'#', '#', '#', '#', '#'}
    };
    PushBoxGame game = new PushBoxGame(map);

    // Player moves right 'd', pushes box 'X' onto target 'G'
    boolean won = game.move('d');
    assertTrue(won);
    assertTrue(game.isGameOver());
    assertEquals(1, game.getPlayerRow());
    assertEquals(2, game.getPlayerCol());
}

@Test
public void testMoveBlockedByWall() {
    char[][] map = {
            {'#', '#', '#'},
            {'#', 'O', '#'},
            {'#', 'G', '#'}
    };
    PushBoxGame game = new PushBoxGame(map);

    // Moving into wall 'w' or 'a' should not change position
    assertFalse(game.move('w'));
    assertEquals(1, game.getPlayerRow());
    assertEquals(1, game.getPlayerCol());

    assertFalse(game.move('a'));
    assertEquals(1, game.getPlayerRow());
    assertEquals(1, game.getPlayerCol());
}

@Test
public void testMoveBoxBlockedByWallCannotPush() {
    char[][] map = {
            {'#', '#', '#', '#'},
            {'#', 'O', 'X', '#'},
            {'#', '#', '#', '#'}
    };
    PushBoxGame game = new PushBoxGame(map);

    // Pushing box to the right into wall '#' fails
    game.move('d');
    assertEquals(1, game.getPlayerRow());
    assertEquals(1, game.getPlayerCol()); // Player didn't move
}

}
