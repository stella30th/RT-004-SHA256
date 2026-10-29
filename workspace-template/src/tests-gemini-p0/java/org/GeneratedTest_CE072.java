package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedTest_CE072 {

@Test
public void testInitialState() {
    Snake.Position food = new Snake.Position(100, 100);
    Snake snake = new Snake(500, 500, 20, food);

    assertEquals(1, snake.getLength());
    assertEquals(0, snake.getScore());
    assertEquals(1, snake.getPositions().size());
    assertEquals(new Snake.Position(250, 250), snake.getPositions().get(0));
    assertEquals(food, snake.getFoodPosition());
}

@Test
public void testMoveStandard() {
    Snake.Position food = new Snake.Position(0, 0);
    Snake snake = new Snake(500, 500, 20, food);

    // Move right (1, 0)
    snake.move(new Snake.Position(1, 0));
    assertEquals(1, snake.getLength());
    assertEquals(new Snake.Position(270, 250), snake.getPositions().get(0));
}

@Test
public void testEatFoodIncreasesLengthAndScore() {
    // Place food directly adjacent
    Snake.Position food = new Snake.Position(270, 250);
    Snake snake = new Snake(500, 500, 20, food);

    snake.move(new Snake.Position(1, 0));
    assertEquals(2, snake.getLength());
    assertEquals(100, snake.getScore());
    assertEquals(2, snake.getPositions().size());
}

@Test
public void testPositionEqualsAndHashCode() {
    Snake.Position p1 = new Snake.Position(10, 20);
    Snake.Position p2 = new Snake.Position(10, 20);
    Snake.Position p3 = new Snake.Position(20, 10);

    assertEquals(p1, p2);
    assertNotEquals(p1, p3);
    assertEquals(p1.hashCode(), p2.hashCode());
    assertEquals(10, p1.getX());
    assertEquals(20, p1.getY());
}

}
