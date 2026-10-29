package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE051 {

@Test
public void testCreateBoardInitializesDimensionsAndIcons() {
    int[] size = {4, 4};
    List<String> icons = Arrays.asList("A", "B");
    MahjongConnect game = new MahjongConnect(size, icons);

    assertNotNull(game.board);
    assertEquals(4, game.board.length);
    assertEquals(4, game.board[0].length);

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            assertTrue(icons.contains(game.board[i][j]));
        }
    }
}

@Test
public void testIsValidMoveAndHasPath() {
    int[] size = {2, 2};
    List<String> icons = Arrays.asList("A");
    MahjongConnect game = new MahjongConnect(size, icons);

    // Same icon, adjacent cells -> has path
    assertTrue(game.isValidMove(new int[]{0, 0}, new int[]{0, 1}));
    assertTrue(game.hasPath(new int[]{0, 0}, new int[]{0, 1}));

    // Same position should be invalid move
    assertFalse(game.isValidMove(new int[]{0, 0}, new int[]{0, 0}));

    // Out of bounds position
    assertFalse(game.isValidMove(new int[]{-1, 0}, new int[]{0, 1}));
}

@Test
public void testRemoveIcons() {
    int[] size = {2, 2};
    List<String> icons = Arrays.asList("A");
    MahjongConnect game = new MahjongConnect(size, icons);

    game.removeIcons(new int[]{0, 0}, new int[]{1, 1});
    assertEquals(" ", game.board[0][0]);
    assertEquals(" ", game.board[1][1]);
    assertEquals("A", game.board[0][1]);
}

@Test
public void testIsGameOver() {
    int[] size = {2, 2};
    List<String> icons = Arrays.asList("A");
    MahjongConnect game = new MahjongConnect(size, icons);

    assertFalse(game.isGameOver());

    game.removeIcons(new int[]{0, 0}, new int[]{0, 1});
    game.removeIcons(new int[]{1, 0}, new int[]{1, 1});

    assertTrue(game.isGameOver());
}

}
