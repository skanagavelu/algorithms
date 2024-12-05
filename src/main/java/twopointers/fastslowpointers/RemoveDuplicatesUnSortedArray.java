package twopointers.fastslowpointers;

import java.util.Arrays;

/**
 *
 * https://www.youtube.com/watch?v=3OamzN90kPg
 * https://leetcode.com/problems/contains-duplicate/
 *
 * Remove duplicates from array in linear time and without extra arrays
 * If the integers are limited 0 to size of the array
 *
 *  OR
 *
 * The simplest way to remove duplicates is by sorting an array.
 * We first sort an array. Once the array is sorted, We can easily remove duplicates by comparing current element
 * with the next element of an array.
 */
public class RemoveDuplicatesUnSortedArray {
    public static void removeDuplicateUsingSorting(int arr[]) {

        //Sort an unsorted array
        Arrays.sort(arr);
        int j = 0;

        //Traverse an array
        for (int i = 0, size = arr.length - 1; i < size; i++) {

            //if value present at i and i+1 index is not equal  <-- Important
            if (arr[i] != arr[i + 1]) {
                arr[j++] = arr[i];
            }
        }

        arr[j++] = arr[arr.length - 1]; // for the last element

        //  Print the unique elements
        //        for (int k = 0; k < j; k++) {
        //            System.out.print(arr[k] + " ");
        //        }

        /*
        You can't trim an array. The fastest approach is just to copy it into a smaller one,
        using System.arraycopy, which is almost always much faster than a for loop:
        System.arraycopy(arr, 0, arr, 0, j); // but there is no trim until you pass another array

         But the below works

         arr = Arrays.copyOfRange(arr,0,j);
         */

        arr = Arrays.copyOfRange(arr,0, j); //TRIMMING
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        int arr[] = { 5, 1, 2, 6, 4, 4, 5, 6, 8, 7 };
        removeDuplicateUsingSorting(arr);
    }
}
