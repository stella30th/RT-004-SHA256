package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE035 {

@Test
public void testFindBlank() {
    int[][] state = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8}
    };
    EightPuzzle puzzle = new EightPuzzle(state);
    int[] blank = puzzle.findBlank(state);

    assertNotNull(blank);
    assertEquals(1, blank[0]);
    assertEquals(1, blank[1]);
}

@Test
public void testGetPossibleMoves() {
    // Blank at center (1, 1) has all 4 moves
    int[][] centerState = {
            {1, 2, 3},
            {4, 0, 5},
            {6, 7, 8}
    };
    EightPuzzle puzzle = new EightPuzzle(centerState);
    List<String> moves = puzzle.getPossibleMoves(centerState);

    assertEquals(4, moves.size());
    assertTrue(moves.contains("up"));
    assertTrue(moves.contains("down"));
    assertTrue(moves.contains("left"));
    assertTrue(moves.contains("right"));

    // Blank at top-left corner (0, 0) has only down and right
    int[][] cornerState = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };
    List<String> cornerMoves = puzzle.getPossibleMoves(cornerState);
    assertEquals(2, cornerMoves.size());
    assertTrue(cornerMoves.contains("down"));
    assertTrue(cornerMoves.contains("right"));
}

@Test
public void testMove() {
    int[][] state = {
            {1, 2, 3},
            {4, 0, 5},
            {6, 7, 8}
    };
    EightPuzzle puzzle = new EightPuzzle(state);

    int[][] movedUp = puzzle.move(state, "up");
    assertEquals(0, movedUp[0][1]);
    assertEquals(2, movedUp[1][1]);

    int[][] movedRight = puzzle.move(state, "right");
    assertEquals(0, movedRight[1][2]);
    assertEquals(5, movedRight[1][1]);
}

@Test
public void testSolveAlreadySolvedOrOneStep() {
    // Goal state: {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}
    int[][] goal = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };
    EightPuzzle puzzleSolved = new EightPuzzle(goal);
    List<String> solution = puzzleSolved.solve();
    assertNotNull(solution);
    assertTrue(solution.isEmpty());

    // One move away: blank at (2, 1), 8 at (2, 2) -> moving "right" solves it
    int[][] oneMove = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}
    };
    EightPuzzle puzzleOneMove = new EightPuzzle(oneMove);
    List<String> oneMoveSolution = puzzleOneMove.solve();
    assertNotNull(oneMoveSolution);
    assertEquals(1, oneMoveSolution.size());
    assertEquals("right", oneMoveSolution.get(0));
}

}
