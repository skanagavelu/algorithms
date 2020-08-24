package sorting;

import java.util.Arrays;

/**
 * Heap Sort
 * Given an array sort it using heap sort
 * 
 * Solution:
 * 1. buildHeap (max/min)  n * log(n)
 * 2. delete each root, and add it to size-1 th element. O(n)
 * 
 * Time complexity
 * O(nlogn) + O(n) = O(nlogn)
 * 
 * Test cases
 */
public class HeapSort {

    public void sort(int arr[]){

        buildHeap(arr);

        //Delete 0th index and add it to last
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


    private void buildHeap(int arr[]) {

        for (int i = arr.length - 1; i > -1; i--) {
            heapify(arr, i, arr.length); //size is same.
        }
    }

    /*
     * heapify on the element, if changed then heapify on its new child position.
     */
    private void heapify(int arr[], int element, int size) {

        int leftChildIdx = 2 * element + 1;
        int rightChildIdx = 2 * element + 2;

        if (leftChildIdx < size) {
            if (rightChildIdx < size) {
                if(arr[element] >= Math.max(arr[leftChildIdx], arr[rightChildIdx])) {
                    //do nothing
                    return;
                }
                if (arr[leftChildIdx] < arr[rightChildIdx]) {

                    swap(arr, element, rightChildIdx);
                    heapify(arr, rightChildIdx, size);
                } else  {

                    swap(arr, element, leftChildIdx);
                    heapify(arr, leftChildIdx, size);
                }
            } else {

                if (arr[element] < arr[leftChildIdx]) {

                    swap(arr, element, leftChildIdx);
                    heapify(arr, leftChildIdx, size);
                }
            }
        }
    }

    
    public static void main(String args[]){
        HeapSort1 hs = new HeapSort1();
        int arr[] = {-1,5,8,2,-6,-8,11,5};
        hs.sort(arr);
        System.out.println(Arrays.toString(array));
    }
}
