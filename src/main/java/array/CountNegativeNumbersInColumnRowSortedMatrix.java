package array;

/**
 * 
 * http://www.geeksforgeeks.org/count-negative-numbers-in-a-column-wise-row-wise-sorted-matrix/
 * @author KanagaveluSugumar
 *
 */
public class CountNegativeNumbersInColumnRowSortedMatrix {
	
	public static void main(String[] args) {
		findSqureSubMatrix(inputArray);
	}

	private static int[][] inputArray = {
			                       {0,  1,  1,  0,  1},
			                       {1,  1,  0,  1,  0},
			                       {0,  1,  1,  1,  0},
			                       {1,  1,  1,  1,  0},
			                       {1,  1,  1,  1,  1},
			                       {0,  0,  0,  0,  0},
	                             };
	
	private static void findSqureSubMatrix(int[][] inputArray) {
		
		int[][] inMemory = new int[inputArray.length][inputArray[0].length];
		int maxElementValue = 0;
		int maxElementRow = 0;
		int maxElementCol = 0;

		
//		inMemory = Arrays.copyOf(original, newLength)
		
		for(int i = 1; i < inputArray.length; i++) {
			for(int j = 1; j < inputArray[0].length; j++) {
				if(inputArray[i][j] == 1) {
					inputArray[i][j] = Math.min(Math.min(inputArray[i-1][j], inputArray[i][j-1]), inputArray[i-1][j-1]) + 1;
				} else {
					inputArray[i][j] = 0;
				}
				maxElementValue = Math.max(maxElementValue, inputArray[i][j]);
				if(maxElementValue == inputArray[i][j]) {
					maxElementRow = i;
					maxElementCol = j;
				}
			} 
		}
		System.out.println("maxElementValue: " + maxElementValue);
		
	}
}











