package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE066 {

@Test public void playerSymbolIsCapitalO() {
 PushBoxGame g=new PushBoxGame(new char[][]{{'#','#','#','#'},{'#','O',' ','#'},{'#','G','X','#'},{'#','#','#','#'}});
 assertEquals(1,g.getPlayerRow()); assertEquals(1,g.getPlayerCol());
 assertEquals(1,g.getTargetCount()); assertFalse(g.checkWin());
 assertFalse(g.move('d')); assertEquals(2,g.getPlayerCol());
}
@Test public void pushBoxOntoGoalReturnsWin() {
 PushBoxGame g=new PushBoxGame(new char[][]{
 {'#','#','#','#','#'}, {'#','O','X','G','#'}, {'#','#','#','#','#'}});
 assertFalse(g.checkWin());assertTrue(g.move('d'));
 assertEquals(2,g.getPlayerCol());assertTrue(g.isGameOver());
 assertArrayEquals(new int[]{1,3},g.getBoxes().get(0));
}
@Test public void blockedPushKeepsPosition() {
 PushBoxGame g=new PushBoxGame(new char[][]{{'#','#','#','#'},{'#','O','X','#'},{'#','#','#','#'}});
 assertFalse(g.move('d'));assertEquals(1,g.getPlayerCol());
}

}
