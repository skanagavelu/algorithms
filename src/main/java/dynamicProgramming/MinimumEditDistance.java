package dynamicProgramming;

import java.util.Arrays;

public class MinimumEditDistance {

    public static void main(String[] args) {

        char[] source = "abcdef".toCharArray();
        char[] target = "azced".toCharArray();

        int[][] editDistanceMatrix = buildEditDistanceMatrix(source, target);
//        System.out.println(Arrays.deepToString(editDistanceMatrix));
        System.out.println("source : " + Arrays.toString(source));
        System.out.println("target : " + Arrays.toString(target));
        printTraversalBottom2Top(editDistanceMatrix, source, target);
    }

    //Totally 3 matrix; editDistMatrix, source, target
    //Note: source is column, target is row
    //source has to converted similar to target for that we need some edits on source.
    //source is column, target is row of editDistMatrix.
    public static int[][] buildEditDistanceMatrix(char[] source, char[] target) {


        //Has additional +1 on editDistMatrix length so we can use source/target.length on editDistMatrix.
        //But careful add "-1" while calling source/target
        int[][] editDistMatrix = new int[target.length + 1] [source.length + 1];

        //fill the first column, left to right
        for (int col = 0; col < source.length + 1; col++) { //editDistMatrix[0].length == source.length + 1
            editDistMatrix[0][col] = col;
        }

        //fill the first row with top to bottom
        for (int row = 0; row < target.length + 1; row++) { //editDistMatrix.length == row length == target.length + 1
            editDistMatrix[row][0] = row;
        }

        // editDistMatrix
        //[[0, 1, 2, 3, 4, 5, 6],
        // [1, 0, 0, 0, 0, 0, 0],
        // [2, 0, 0, 0, 0, 0, 0],
        // [3, 0, 0, 0, 0, 0, 0],
        // [4, 0, 0, 0, 0, 0, 0],
        // [5, 0, 0, 0, 0, 0, 0]]

        //m*n operation
        for (int i = 1; i < target.length + 1; i++) { //row

            for (int j = 1; j < source.length + 1; j++) { //column

                if (source[j-1] == target[i-1]) {

                    //move diagonal, both chars are same, so no edits (+1)
                    editDistMatrix[i][j] = editDistMatrix[i-1][j-1];
                } else {

                    //1 is edit operation required
                    //may be diagonal but still substitution, so +1
                    editDistMatrix[i][j] = 1 + smallestOfThree(editDistMatrix[i-1][j-1], //Diagonal : Substitution
                                                               editDistMatrix[i-1][j],   //Top : Insertion
                                                               editDistMatrix[i][j-1]);  //Left : Deletion
                }
            }
        }

        //[[0, 1, 2, 3, 4, 5, 6],
        // [1, 0, 1, 2, 3, 4, 5],
        // [2, 1, 1, 2, 3, 4, 5],
        // [3, 2, 2, 1, 2, 3, 4],
        // [4, 3, 3, 2, 2, 2, 3],
        // [5, 4, 4, 3, 2, 3, 3]]
        return editDistMatrix;
    }

    static void printTraversalBottom2Top(int[][] editDistMatrix, char[]source, char[] target) {

        int col = source.length; //col length of matrix, not for source, when doing add "-1"
        int row = target.length;

        while(!(row == 0 || col == 0)) {

            //Note: access with -1.
            // move diagonally, since chars are same
            if(source[col-1] == target[row-1]) {
                row --;
                col --;
            }
            //move diagonally still add Substitution penalty +1
            else if(editDistMatrix[row][col] == editDistMatrix[row-1][col-1] + 1) {
                System.out.println("Substitution: Source " + source[col - 1] + " changed " +
                                   "to same as of target " + target[row - 1]);

                row--;
                col--;
            }
            // Top move: Insertion
            else if(editDistMatrix[row][col] == editDistMatrix[row-1][col] + 1) {
                System.out.println("Insertion: target is inserted with " + target[row-1]);

                row--;
            }
            //Left move: Deletion
            else if(editDistMatrix[row][col] == editDistMatrix[row][col-1] + 1) {

                System.out.println("Deletion: source " + source[col-1] + " is deleted");
                col--;
            }
        }
    }
    private static int smallestOfThree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
