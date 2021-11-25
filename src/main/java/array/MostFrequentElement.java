package array;


// https://afteracademy.com/blog/find-the-most-frequent-element-in-an-array

/**
 * Understanding the Problem
 * Problem Description: Given an array A[] of size n, find the most frequent element in the array, i.e. the element
 * which occurs the most number of times. It is assured that at least one element is repeated.
 *
 * For example:
 *
 * Input: A[] = {3, 9, 1, 3, 6, 3, 8, 1, 6}
 *
 * Output: 3
 *
 * Input: A[] = {1, 9, 1, 3, 2, 3, 10}
 *
 * Output: 1
 *
 *
 *
 * We will discuss three possible solutions for this problem:-
 *
 * 1. Brute Force: Calculate frequency using nested loops
 *
 * For each element, scan the entire array to find its duplicates.
 * Maintain two variables max_freq and ans to store the maximum frequency and the element with that frequency respectively.
 * Time Complexity: O(n²)
 * Space Complexity: O(1)
 *
 * When memory is constraint
 * 2. Using Sorting: Calculate frequency linearly after sorting
 * If we sort the array, all the duplicate elements will get lined up next to each other. We can now linearly find the frequency of all elements in the array.
 * This approach also ensures that frequency is calculated only once for each unique element.
 * Time Complexity: Sorting the array + Linear Traversal of array
 * = O(nlogn) + O(n) = O(nlogn)
 * Space Complexity: O(n), if we use merge sort and O(1) if we use heap sort
 *
 * When throughput is a constraint
 * 3. Using Hash Table : Use a Hash Table to find the frequency of elements
 * Time Complexity: O(n) (Why?)
 * Space Complexity: O(n), for storing the Hash Table
 *
 */
public class MostFrequentElement {

}
