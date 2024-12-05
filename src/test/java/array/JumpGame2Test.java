package array;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpGame2Test {

  JumpGame2 jumpGame2 = new JumpGame2();

  @Test
  void canJump() {
    int[] input = {2,3,1,1,4};
    assertEquals(2, jumpGame2.canJump(input));
  }

  @Test
  void canJumpDescending() {
    int[] input = {3,2,1};
    assertEquals(1, jumpGame2.canJump(input));
  }

  @Test
  void canJumpByChoosingMaxIndex() {
    int[] input = {1,2,1,1,1};
    assertEquals(3, jumpGame2.canJump(input));
  }

  @Test
  @Disabled
  void canJumpDescendingLong() {
    int[] input = {10,9,8,7,6,5,4,3,2,1,1,0};
    assertEquals(2, jumpGame2.canJump(input));
  }

  @Test
  void canJumpCornerCase() {
    int[] input = {4,1,1,3,1,1,1};
    assertEquals(2, jumpGame2.canJump(input));
  }

  @Test
  @Disabled
  void canJumpCornerCase1() {
    int[] input = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
    assertEquals(2, jumpGame2.canJump(input));
  }
}
