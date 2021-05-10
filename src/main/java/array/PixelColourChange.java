package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PixelColourChange {

    public static void main(String[] args) {
        int[][] pixels = {
                            {  1, 2, 2, 3, 5, 5, 6  },
                            {  2, 2, 2, 3, 4, 5, 5  },
                            {  3, 2, 1, 4, 4, 4, 6  },
                            {  6, 6, 6, 5, 5, 5, 6  }
                        };
        System.out.println("Before");
        for (int[] row : pixels) {
            System.out.println(Arrays.toString(row));
        }

        Point startAt = new Point(1, 1);
        int from = pixels[1][1];
        performColourChange(pixels, startAt,  from, 8);

        System.out.println("After");

        for (int[] row : pixels) {
            System.out.println(Arrays.toString(row));
        }

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


    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {

            this.row = row;
            this.col = col;
        }
    }
}
