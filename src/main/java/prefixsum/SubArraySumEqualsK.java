package prefixsum;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/solutions/803317/java-solution-with-detailed-explanation/
 * There is better approach using hashmap
 *
 * 560. Subarray Sum Equals K
 *
 *  Given an array of integers nums and an integer k,
 *  return the total number of subarrays whose sum equals to k.
 *  Example 1:
 *  Input: nums = [1,1,1], k = 2
 *  Output: 2
 *  *
 *  Example 2:
 *  Input: nums = [1,2,3], k = 3
 *  Output: 2
 */
public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        /*
          Building prefix sum
             [1, 2, 3,  4,  5]
          [0, 1, 3, 6, 10, 15] <-- Prefix sum starts with zero

          There is one index difference between num and prefixSum
          prefix[end] - prefix[start] = Sum{num[start+1], num[start+2], num[end]}
         */
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        for (int start = 0; start < sum.length; start++) {
            for (int end = start + 1; end < sum.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }

        return count;
    }
}
