import java.util.Arrays;

// https://www.youtube.com/watch?v=xouin83ebxE
public class NQueens {

    //Queen position
    class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public Position[] solveNQueenOneSolution(int n) {
        
        //Queen column position on each row
        Position[] positions = new Position[n];
        boolean hasSolution = solveNQueenOneSolutionUtil(0, positions);
        if (hasSolution) {
            return positions;
        } else {
            return new Position[0];
        }
    }

    private boolean solveNQueenOneSolutionUtil(int row, Position[] positions) {
        if (positions.length == row) {
            // All rows; queens are placed
            return true;
        }

        //Check for next valid column in this row
        for (int col = 0; col < positions.length; col++) {

            if (isSafe(row, col, positions)) {
                positions[row] = new Position(row, col);

                // Backtracking Pattern: returns success or failure to the caller
                if (solveNQueenOneSolutionUtil(row + 1, positions)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isSafe(int row, int col, Position[] positions) {

        //check if this row and col is not under attack from any previous queen position
        for (int pos = 0; pos < row; pos++) {
            if (positions[pos].col == col ||
                positions[pos].row - positions[pos].col == row - col ||
                positions[pos].row + positions[pos].col == row + col) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        NQueens s = new NQueens();
        Position[] positions = s.solveNQueenOneSolution(4);
        Arrays.stream(positions).forEach(position -> System.out.println(position.row + " " + position.col));
    }
}
