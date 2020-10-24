package sorting;

import java.util.Arrays;

//With while loop instead of regression
public class HeapSort1 {

    public void sort(int arr[]){

        System.out.println(Arrays.toString(arr));

        buildHeap(arr);

        //Swap 0th index max value to last
        for(int i = arr.length - 1; i > -1; i--){

            swap(arr, 0, i);

            //heapify only non deleted nodes.
            heapifyLoop(arr, 0, i);   // i == size to ignore ith element
            System.out.println(Arrays.toString(arr));

        }
        System.out.println(Arrays.toString(arr));
    }

    private void swap(int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    private void buildHeap(int arr[]) {

        for (int i = arr.length - 1; i > -1; i--) {
//            heapify(arr, i, arr.length); //size is same.
            System.out.println(Arrays.toString(arr));

        }

        System.out.println("Heap built");

    }

    /*
     * heapify on the element, if changed then heapify on its new child position.
     */
    private void heapifyLoop(int arr[], int element, int size) {

        while (true) {

            int leftChildIdx = 2 * element + 1;
            int rightChildIdx = 2 * element + 2;

            if (leftChildIdx < size) {
                if (rightChildIdx < size) {
                    if (arr[element] > Math.max(arr[leftChildIdx], arr[rightChildIdx])) {
                        break;
                    }
                    if (arr[leftChildIdx] < arr[rightChildIdx]) {
                        swap(arr, element, rightChildIdx);
                        element = rightChildIdx;
                    } else {
                        swap(arr, element, leftChildIdx);
                        element = leftChildIdx;
                    }
                } else if (arr[element] < arr[leftChildIdx]) { //if false (already ordered), should go with else and terminate loop

                    swap(arr, element, leftChildIdx);
                    element = leftChildIdx;
                } else {
                    //should break, else loop will ever continue
                    break;
                }
            } else {

                break;
            }
        }
    }

    public static void main(String args[]){
        HeapSort1 hs = new HeapSort1();
        int arr[] = {-1,5,8,2,-6,-8,11,5};
        hs.sort(arr);
        for(int a : arr){
            System.out.println(a);
        }
    }
}