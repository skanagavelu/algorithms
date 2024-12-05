package prefixsum;

/**
 * 303. Range Sum Query - Immutable
 *
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 */
public class RangeSumQueryImmutable {

    int[] sums;

    public RangeSumQueryImmutable(int[] nums) {
        sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    /*
     left and right are inclusive
             [1, 2, 3,  4,  5]
          [0, 1, 3, 6, 10, 15]

          so (0, 3) == 1 + 2 + 3 + 4 = 10
                    == sum(4) - sum(0)
                    == 10 - 0
     */
    public int sumRange(int left, int right) {
    // There is one index difference between num and prefixSum
    return sums[right + 1] - sums[left];
    }
}
