package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

  JumpGame jumpGame = new JumpGame();

  @Test
  void canJump() {
    int[] input = {2,3,1,1,4};
    assertEquals(true, jumpGame.canJump(input));
  }

  @Test
  void cannotJump() {
    int[] input = {3,2,1,0,4};
    assertEquals(false, jumpGame.canJump(input));
  }
}
