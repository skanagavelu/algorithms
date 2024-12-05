package array;

/**
 * 55. Jump Game Input: nums = [2,3,1,1,4] Output: true
 * Explanation: Jump 1 step from index 0 to 1,
 * then 3 steps to the last index.
 */
public class JumpGame {

  public boolean canJump(int[] nums) {
    int jumpRequired = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] >= jumpRequired) {
        jumpRequired = 1;
      } else {
        jumpRequired++;
      }
    }
    return jumpRequired == 1;
  }
}
