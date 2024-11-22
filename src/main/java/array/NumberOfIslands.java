package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Find out total number of Islands
 */
public class NumberOfIslands {

    record Point(int row, int col) {}

    public static void main(String[] args) {

        int[][] pixels = {
                            {  1, 1, 0, 0, 1, 0, 0  },
                            {  1, 1, 0, 0, 1, 1, 1  },
                            {  0, 0, 0, 0, 0, 0, 0  },
                            {  0, 0, 0, 0, 0, 0, 1  }
                        };
        System.out.println("Before");

//        deepToString is sufficient
//        for (int[] row : pixels) {
//            System.out.println(Arrays.toString(row));
//        }

        System.out.println(Arrays.deepToString(pixels).replace("],", "],\n"));
        Point startAt = new Point(1, 1);
        int from = pixels[1][1];
        System.out.println("getTotalNumberOfIslands; "+  getTotalNumberOfIslands(pixels));

        System.out.println("After");

        for (int[] row : pixels) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int getTotalNumberOfIslands(int[][] pixels) {

        int counter = 0;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (pixels[i][j] == 1) {
                    ++ counter;
                    performColourChange(pixels, new Point(i, j), 1, 0);
                }
            }
        }
        return counter;
    }

    private static void performColourChange(int[][] pixels, Point startAt, int fromColour, int toColour) {

        int currentColour = pixels[startAt.row][startAt.col];
        if (currentColour != fromColour  || currentColour == toColour) {
            return;
        }

        pixels[startAt.row][startAt.col] = toColour;
        List<Point> neighbours = getNeighbours(pixels, startAt);
        for (Point neighbour : neighbours) {
            performColourChange(pixels, neighbour, fromColour, toColour);
        }
    }

    private static List<Point> getNeighbours(int[][] pixels, Point startAt) {

        List<Point> neighbours = new ArrayList<>();

        // Add left
        if(startAt.row > 0) {
            neighbours.add(new Point(startAt.row - 1, startAt.col));
        }

        // Add right
        if(startAt.row < pixels.length - 1) {
            neighbours.add(new Point(startAt.row + 1, startAt.col));

        }

        // Add top
        if(startAt.col > 0) {
            neighbours.add(new Point(startAt.row, startAt.col - 1));

        }

        // Add bottom
        if(startAt.col < pixels[0].length - 1) {
            neighbours.add(new Point(startAt.row, startAt.col + 1));
        }

        return neighbours;
    }
}
