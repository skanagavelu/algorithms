package slidingwindow;

/**
 * https://www.youtube.com/watch?v=p-ss2JNynmw
 * https://www.youtube.com/watch?v=DfljaUwZsOk
 * 209. Minimum Size Subarray Sum
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0, right = 1, minSum = Integer.MAX_VALUE;
        if (nums[left] >= target) return 1;
        int sum = nums[left];
        while(right < nums.length) {

            sum += nums[right];
            if (sum >= target) {
                // Remove all the left, since added right may too big
                while (sum >= target) { // account the left removed +1
                    sum -= nums[left++];
                }
                minSum = Math.min(minSum, (right - left + 2)); // +1 == including left
            }
            right++;
        }
        return minSum == Integer.MAX_VALUE ? 0 : minSum;
    }
}
