package interview.snakegame;

public class Game {

  public Board board;
  public Snake snake;
  public volatile boolean isGameOver = false;

  public Game() {

    this.board = new Board(10, 10);
    this.snake = new Snake();
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.moveSnake(Direction.RIGHT);
    game.moveSnake(Direction.LEFT);
    game.moveSnake(Direction.UP);
    game.moveSnake(Direction.DOWN);
    game.moveSnake(Direction.RIGHT);
    game.moveSnake(Direction.RIGHT);
  }

  public void moveSnake(Direction direction) {
    snake.move(direction);
  }

  public boolean isGameOver() {
    return isGameOver;
  }
}
