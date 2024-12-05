package interview.snakegame;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Rules: Every time moveSnake() is called, the snake moves up, down, left or right The snake’s
 * initial size is 3 and grows by 1 every 5 moves
 *
 * <p>The game ends when the snake hits itself Snake shoud move other direction when hits the wall
 *
 * <p>interface SnakeGame {
 *
 * <p>moveSnake(snakeDirection); isGameOver(); }
 */
public class Snake {

  Cell head;
  Cell tail;
  Point currentPosition;
  Direction direction = Direction.RIGHT;
  AtomicInteger movement = new AtomicInteger(0);

  public Snake() {
    this.currentPosition = new Point(0, 0);
    this.head = new Cell(null, null, currentPosition);
    this.tail = this.head;
    initiateBody();
  }

  private void initiateBody() {
    for (int j = 1; j < 2; j++) {
      Cell newBodyCell = new Cell(this.tail, null, new Point(this.tail.currentPosition.x(), j));
      this.tail = newBodyCell;
    }
  }

  private void grow() {
    // TODO
  }

  public void move(Direction direction) {
    switch (direction) {
      case UP -> currentPosition = new Point(currentPosition.y() - 1, currentPosition.y());
      case DOWN -> currentPosition = new Point(currentPosition.y() + 1, currentPosition.y());
      case LEFT -> currentPosition = new Point(currentPosition.x(), currentPosition.y() - 1);
      case RIGHT -> currentPosition = new Point(currentPosition.x(), currentPosition.y() + 1);
    }
    movement.incrementAndGet();
    this.head = new Cell(null, this.head, currentPosition);
    this.tail = tail.previous;
    tail.next = null;
    grow();
  }

  private static class Cell {
    Cell previous;
    Cell next;
    Point currentPosition;

    public Cell(Cell previous, Cell next, Point currentPosition) {
      this.previous = previous;
      this.next = next;
      this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
      return "Cell{"
          + "previous="
          + previous
          + ", next="
          + next
          + ", currentPosition="
          + currentPosition
          + '}';
    }
  }
}
