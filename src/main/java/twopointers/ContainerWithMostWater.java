package twopointers;

/**
 * 11. Container With Most Water
 *
 * Walls are thin, does not occupy space.
 * So breadth * height is the max capacity; assume if there is nothing in between.
 * Two pointer moves towards short to tall, and calculates profit
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, maxAreaSoFar = 0;

        while (i < j) {
            //            breadth * height
            int areaNow = (j - i) * Math.min(height[i], height[j]);
            if (maxAreaSoFar < areaNow) {
                maxAreaSoFar = areaNow;
            }
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxAreaSoFar;
    }
}
