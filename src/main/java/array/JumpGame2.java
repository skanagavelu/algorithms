package array;

/** 45. Jump Game II Find the minimum jump required */
public class JumpGame2 {

  public int canJump(int[] nums) {

    if (nums == null || nums.length < 2) return 0;

    int jumps = 0;
    for (int i = 0; i < nums.length - 1; ) {
      if (i + nums[i] >= nums.length) return ++jumps;
      int maxJumpIndex = nextMaxJumpIndex(nums, i);
      i = maxJumpIndex;
      jumps++;
    }

    return jumps;
  }

  private int nextMaxJumpIndex(int[] nums, int i) {

    int indexVal = nums[i];
    int nextMaxJumpIndex = i;
    for (int j = i + 1, k = 0; j <= i + nums[i] && j < nums.length; j++, k++) {
      if (indexVal <= (nums[j] + k)) {
        nextMaxJumpIndex = j;
        indexVal = (nums[j] + k);
      }
    }
    return nextMaxJumpIndex;
  }

  public int canJump1(int[] nums) {
    if (nums == null || nums.length < 2) return 0;

    int jumpRequired = 1;
    for (int i = 0; i < nums.length - 1; ) {

      if (nums[i] >= nums.length - i - 1) {
        return jumpRequired;
      }

      int maxNumberValueIndex = i + 1;
      // find the max value in the num[i] range
      for (int j = i + 1; j < i + nums[i] + 1 && j < nums.length; j++) {
        // Choose the higher index
        if (nums[maxNumberValueIndex] <= (nums[j] + (j - i))) {
          maxNumberValueIndex = j;
        }
      }
      if (maxNumberValueIndex == 0) {
        return 0;
      } else if (nums[maxNumberValueIndex] + maxNumberValueIndex <= nums[i]) {
        jumpRequired += 1;
        i += nums[i];
      } else {
        jumpRequired += 1;
        i = maxNumberValueIndex;
      }
    }
    return jumpRequired;
  }
}
