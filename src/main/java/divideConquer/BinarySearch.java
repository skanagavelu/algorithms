package divideConquer;

/**
 * Have a look at Arrays.binarySearch(int[] a, int fromIndex, int toIndex,
                                   int key)
 * 
 * 1. This is a static method
 * 2. Implementation is done using private api (binarySearch0);
 * 		 so that method with/without rangeCheck can call this common private api. 
 * 3. They do have     private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
 * 
 * @author ksugumar
 *
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] input = { 10, 25, 33, 41, 77, 503}; // EVEN inputs
//		 int[] input = { 10, 22, 23, 24, 33, 41, 77, 88, 503}; //ODD inputs

		System.out.println(binarySearch(input, 10));
		System.out.println(binarySearch(input, 25));
		System.out.println(binarySearch(input, 33));
		System.out.println(binarySearch(input, 41));
		System.out.println(binarySearch(input, 77));
		System.out.println(binarySearch(input, 503));

		System.out.println("OutOfRange");
		System.out.println(binarySearch(input, 8));
		System.out.println(binarySearch(input, 15));
		System.out.println(binarySearch(input, 30));
		System.out.println(binarySearch(input, 40));
		System.out.println(binarySearch(input, 70));
		System.out.println(binarySearch(input, 80));
		System.out.println(binarySearch(input, 600));
	}

	public static int binarySearch(int[] array, int key) {

		int low = 0;
		int high = array.length - 1;
		while (low <= high) {

			int mid = (low + high) >>> 1; //Left shift will double the value, right shit divides by 2
			long midVal = array[mid];

			if (midVal == key) return mid;

			if (midVal < key)
				low = mid + 1;      //NOTE remember this
			else
				high = mid - 1;     //NOTE remember this
		}
		return -(low);              // key not found; till which direction with index
	}


	/* can not use ternary when equal needs return and other cases are void.
	  midVal.compareTo(key) < low = mid + 1 : a : a.compareTo(b) > 0 ? b : 0

	  But if else works
	  		if (midVal.compareTo(key) == 0 ) return mid;

			if (midVal.compareTo(key) < 0)
				low = mid + 1;      //NOTE remember this
			else
				high = mid - 1;     //NOTE remember this
	 */

}
