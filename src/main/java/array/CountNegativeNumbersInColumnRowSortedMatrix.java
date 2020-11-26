package array;

/**
 * http://www.geeksforgeeks.org/count-negative-numbers-in-a-column-wise-row-wise-sorted-matrix/
 *
 * @author sugumar
 */
public class CountNegativeNumbersInColumnRowSortedMatrix {

    public static void main(String[] args) {

        countNegativeNumbers(sortedArray);
    }

    private static int[][] sortedArray = {
            { -3, -2, -1, 1 },
            { -2,  2,  3, 4 },
            {  4,  5,  7, 8 }
    };

    private static void countNegativeNumbers(int[][] sortedArray) {

        int negativeCount = 0;

        /*
         *  We start from the top right corner and find the position of the last negative number in the first row.
         *  Using this information, we find the position of the last negative number in the second row.
         *  We keep repeating this process until we either run out of negative numbers or we get to the last row.
         */
        for (int col = sortedArray[0].length - 1, row = 0; col >= 0 && row < sortedArray.length; ) {

            if (sortedArray[row][col] < 0) {
                negativeCount += col + 1;
                row ++;
            } else {
                col--;
            }
        }
        System.out.println("negativeCount: " + negativeCount);
    }
}











