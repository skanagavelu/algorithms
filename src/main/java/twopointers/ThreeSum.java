package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://fizzbuzzed.com/top-interview-questions-1/
 * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 *
 * https://leetcode.com/problems/two-sum/solution/ with hashmap to check the complement (complement = target - nums[i])
 * also thee hashTable contains index to check we are not working on the same index.
 */
public class ThreeSum {

    public static void main(String[] args) {

        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] duplicateNums = {0, 1, 1, 2, 3, 4, 5, 5, 6, 7, 7};

        List<List<Integer>> threeSums = findThreeSum(nums, 8);
        System.out.println(threeSums);

        List<List<Integer>> threeSumsDuplicates = findThreeSum(duplicateNums, 8);
        System.out.println(threeSumsDuplicates);

    }

    public static List<List<Integer>> findThreeSum(int[] nums, int sum) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>(4);

        /*
         * The way to find three sum in n^2 time after sorting
         * O(n * log n) for sorting + O(n^2) time for iteration
         * so total complexity is O(n^2)
         */
        for (int i = 0; i < nums.length; i++) {

            int j = i + 1, k = nums.length - 1;

            // Skip duplicates creation, but above give chance of j try with duplicate
            while (i < nums.length - 1 && nums[i] == nums[i+1]) { i++; }

            /*
             * The way to find two sum in n * log n time after sorting
             * O(n * log n) for sorting + O(n) time for iteration
             * so total complexity is O(n * log n)
             *
             * two pointer solution, the array must first be sorted,
             * then we can use the sorted structure to cut down the number of comparisons we do.
             * The idea is
             * [X, X, X, X, X]
             * A[]
             */
            while ( j < k) {

                int tripletSum = nums[i] + nums[j] + nums[k];

                if (tripletSum == sum) {

                    result.add(List.of(nums[i], nums[j], nums[k]));

                    while (j < nums.length - 1 && nums[j] == nums[j+1]) { j++; }
                    while (k > 0 && nums[k] == nums[k-1]) { k--; } // ensure guarding k > 0, to avoid index out of
                    j++;
                    k--;
                } else if (tripletSum < sum) {

                    while (j < nums.length - 1 && nums[j] == nums[j+1]) { j++; } //For duplicate
                    j++;
                } else {

                    while (k > 0 && nums[k] == nums[k-1]) { k--; }
                    k--;
                }
            }
        }
        return new ArrayList<>(result);
    }
}
