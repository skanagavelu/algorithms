package backtracking;

import java.util.Arrays;

// https://www.youtube.com/watch?v=xouin83ebxE
public class NQueens {

    public int[] solveNQueenOneSolution(int n) {

        //Queen column position on every index row
        int[] positions = new int[n];
        boolean hasSolution = solveNQueenOneSolutionUtil(0, positions);
        if (hasSolution) {
            return positions;
        } else {
            return new int[0];
        }
    }

    //Starts from first row, and go till end of the row
    private boolean solveNQueenOneSolutionUtil(int row, int[] positions) {

        if (positions.length == row) {
            // All rows reached; means all queens are placed
            return true;
        }

        //Check for next valid column in current row
        for (int col = 0; col < positions.length; col++) {

            if (isSafe(row, col, positions)) {
                positions[row] = col;

                /* Backtracking Pattern:
                 * 1. Call with incremental value || Find the column for next row
                 * 2. returns (ends stack) success or failure to the caller
                 */
                if (solveNQueenOneSolutionUtil(row + 1, positions)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int[] positions) {

        //check if this row and col is not under attack from any previous queen position
        for (int row0 = 0; row0 < row; row0++) {

            if (positions[row0] == col ||   // Same column?

                //Two diagonal check
                // 1. (previous queen row + col) == (current queen row + col)?
                // 2. (previous queen row - col) == (current queen row - col)?
                row0 - positions[row0] == row - col ||
                row0 + positions[row0] == row + col) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        NQueens s = new NQueens();
        int[] positions = s.solveNQueenOneSolution(4);
        Arrays.stream(positions).forEach(column -> System.out.println(column));
    }


    /*         0    1    2    3
           0   q1   -    -    q
           1   -    -    -    q2
           2   -    q    q3   -
           3   -    -    -    -

           q3 is under attack by q1 due to row - col
           q3 is under attack by q2 due to row + col
     */
}