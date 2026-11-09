package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratedTest_CE041 {

@Test public void alternatingMovesAndOccupiedCell() {
 GomokuGame g=new GomokuGame(5);
 assertTrue(g.makeMove(0,0)); assertFalse(g.makeMove(0,0));
 assertTrue(g.makeMove(1,0));
 assertEquals('X',g.board[0][0]); assertEquals('O',g.board[1][0]);
 assertNull(g.checkWinner());
}
@Test public void horizontalFiveWins() {
 GomokuGame g=new GomokuGame(6);
 for(int c=0;c<5;c++){assertTrue(g.makeMove(0,c));if(c<4)assertTrue(g.makeMove(1,c));}
 assertEquals(Character.valueOf('X'),g.checkWinner());
}
@Test(expected=ArrayIndexOutOfBoundsException.class)
public void outsideCoordinatesThrow() {new GomokuGame(3).makeMove(-1,0);}

}
