package divideConquer;

public class BinarySearchOnRotatedArray {

    public static void main(String[] args) {

        int[] input = { 33, 41, 77, 503, 10, 25 }; // EVEN inputs
        //		 int[] input = {  33, 41, 77, 88, 503, 10, 22, 23, 24}; //ODD inputs
        System.out.println(binarySearch(input, 33));
        System.out.println(binarySearch(input, 41));
        System.out.println(binarySearch(input, 77));
        System.out.println(binarySearch(input, 503));
        System.out.println(binarySearch(input, 10));
        System.out.println(binarySearch(input, 25));

        System.out.println(binarySearch(input, 32));
        System.out.println(binarySearch(input, 34));
        System.out.println(binarySearch(input, 43));
        System.out.println(binarySearch(input, 78));
        System.out.println(binarySearch(input, 504));
        System.out.println(binarySearch(input, 11));
        System.out.println(binarySearch(input, 26));

    }

    /*
     * https://stackoverflow.com/a/12941612/912319
     */
    public static int binarySearch(int[] array, int key) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            // Avoid overflow, same as M=(L+R)/2 ===> M=low + ((high - low) / 2)
            int mid = (low + high) >>> 1;

            if (array[mid] == key) {
                return mid;
            }

            // the first half is sorted
            if (array[low] <= array[mid]) {
                if (array[low] <= key && key < array[mid])  // key is in sorted range
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            // the second half is sorted
            else {
                if (array[mid] < key && key <= array[high]) // key is in sorted range
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -low;
    }

    public static int binarySearch1(int[] array, int key) {

        int low = 0;
        int high = array.length - 1;
        while (low <= high) {

            int mid = (low + high) >>> 1; //Left shift will double the value, right shit divides by 2
            long midVal = array[mid];
            long lowVal = array[low];
            long highVal = array[high];

            //Sorted
            if (midVal < key) {              // Go right
                if (lowVal <= midVal) {      // Is left sorted
                    low = mid + 1;           // Sure then will go right
                } else {                     // left is unsorted we don't know can we go right

                    if (highVal >= key) {    // Right must sorted, since left unsorted, so check key fall within right?
                        low = mid + 1;       // will go right
                    } else {
                        high = mid - 1;      //Go left as key is not part right sorted range
                    }
                }
            } else if (midVal > key) {        // Go left
                if (highVal >= midVal) {      // Is right sorted

                    high = mid - 1;           // Sure then will go left
                } else {                      // Right is unsorted we don't know can we go left

                    if (lowVal <= key) {      // Left must sorted, since right unsorted, so check key fall within left?
                        high = mid - 1;       //will go left
                    } else {
                        low = mid + 1;        //Go Right as key is not part left sorted range
                    }
                }
            } else {
                return mid;                   // key found
            }
        }
        return -(low);              // key not found; till which direction with index
    }
}

