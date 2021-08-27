package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://fizzbuzzed.com/top-interview-questions-1/
 * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 */
public class ThreeSum {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] duplicateNums = {0, 1, 1, 2, 3, 4, 5, 5, 6, 7, 7};

        List<ThreeSumValues> threeSums = findThreeSum(nums, 8);
        System.out.println(threeSums);

        List<ThreeSumValues> threeSumsDuplicates = findThreeSum(duplicateNums, 8);
        System.out.println(threeSumsDuplicates);

    }


    private static List<ThreeSumValues> findThreeSum(int[] nums, int sum) {

        Arrays.sort(nums);
        List<ThreeSumValues> result = new ArrayList<>();

        /*
         * The way to find two sum in n^2 time after sorting
         * O(n * log n) for sorting + O(n^2) time for iteration
         * so total complexity is O(n^2)
         */
        for (int i = 0; i < nums.length; i++) {
            while (i < nums.length - 1 && nums[i] == nums[i+1]) { i++; }

            /*
             * The way to find two sum in n * log n time after sorting
             * O(n * log n) for sorting + O(n) time for iteration
             * so total complexity is O(n * log n)
             */
            for (int j = i + 1, k = nums.length -1; j < k;) {

                if (nums[i] + nums[j] + nums[k] == sum) {

                    result.add(new ThreeSumValues(nums[i], nums[j], nums[k]));
                    while (j < nums.length - 1 && nums[j] == nums[j+1]) { j++; }
                    j++;
                } else if (nums[i] + nums[j] + nums[k] < sum) {

                    while (j < nums.length - 1 && nums[j] == nums[j+1]) { j++; }
                    j++;
                } else {

                    while (nums[k] == nums[k-1]) { k--; }
                    k--;
                }
            }
        }
        return result;
    }

    public static class ThreeSumValues {

        int first, second, third;

        public ThreeSumValues(int first, int second, int third) {

            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {

            return "ThreeSumValues{" +
                   "first=" + first +
                   ", second=" + second +
                   ", third=" + third +
                   '}';
        }
    }
}
