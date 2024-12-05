package sorting;

import java.util.Arrays;

/**
 * Heap Sort
 * Given an array sort it using heap sort
 *
 * Solution:
 * 1. buildHeap (max/min)  n * log(n)
 * 2. delete each root, and add it to size-1 th element. O(n)
 * 3. heapify :: Ensure newly changed position is ensuring heap property, by checking its children

 * Time complexity
 * O(nlogn) + O(n) = O(nlogn)
 *
 * Test cases
 */
public class HeapSort {

    public void sort(int arr[]){

        // Build max heap, then move the max to end
        buildHeap(arr);

        //HeapSort: Delete 0th index and add it to last
        for(int i = arr.length - 1; i >= 0; i--){
            swap(arr, 0, i);

            //heapify only non deleted nodes.
            heapify(arr, 0, i);
        }
    }

    private void swap(int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * We can build the heap by adding one by one element, n(log(n))
     * But this reverse order does comparison of only half the elements
     * So I believe (n/2) log(n)
     */
    private void buildHeap(int arr[]) {

        for (int i = arr.length - 1; i > -1; i--) {
            heapify(arr, i, arr.length); //size is same.
        }
    }

    /*
     * heapify on the element, if changed then heapify on its new child position.
     */
    private void heapify(int arr[], int start, int end) {

        int leftChildIdx = 2 * start + 1;
        int rightChildIdx = 2 * start + 2;

        if (leftChildIdx < end) {
            if (rightChildIdx < end) {
                if(arr[start] >= Math.max(arr[leftChildIdx], arr[rightChildIdx])) {
                    //do nothing
                    return;
                }
                if (arr[leftChildIdx] < arr[rightChildIdx]) {

                    swap(arr, start, rightChildIdx);
                    heapify(arr, rightChildIdx, end);
                } else  {

                    swap(arr, start, leftChildIdx);
                    heapify(arr, leftChildIdx, end);
                }
            } else {

                if (arr[start] < arr[leftChildIdx]) {

                    swap(arr, start, leftChildIdx);
                    heapify(arr, leftChildIdx, end);
                }
            }
        }
    }


    public static void main(String args[]){
        HeapSort hs = new HeapSort();
        int arr[] = {-1,5,8,2,-6,-8,11,5};
        hs.sort(arr);
        // [-8, -6, -1, 2, 5, 5, 8, 11]
        System.out.println(Arrays.toString(arr));
    }
}