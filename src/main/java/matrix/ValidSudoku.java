package matrix;

import java.util.HashSet;

/*
36. Valid Sudoku

Note:
  A Sudoku board (partially filled) could be valid but is not necessarily solvable.
  Only the filled cells need to be validated according to the mentioned rules.
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] rows = new HashSet[9];  // WRONG => new HashSet<>[9]
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    continue;
                }

                char value = board[r][c];
                int boxIndex = (r / 3) * 3 + (c / 3);

                if (rows[r].contains(value) || cols[c].contains(value) || boxes[boxIndex].contains(value)) {
                    return false;
                }

                rows[r].add(value);
                cols[c].add(value);
                boxes[boxIndex].add(value);
            }
        }

        return true;
    }
}
