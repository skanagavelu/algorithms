package misc;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CourseOrderTest  {

    CourseOrder instance = new CourseOrder();

    @Test
    public void testInvalidCourseNumber() {

        int[][] preReqs = {{1,0},{2,0},{3,1},{3,2}};
        int[] result1  = instance.getCourseOrder(-2, preReqs);
        int[] result2  = instance.getCourseOrder(1, preReqs);
        assertArrayEquals(result1, new int[]{});
        assertArrayEquals(result2, new int[]{});
    }

    @Test
    public void testDuplicatePreReqs() {
        int numberOfCourses = 4;
        int[][] preReqs = {{1,0},{1,0}};
        int[] result  = instance.getCourseOrder(numberOfCourses, preReqs);
        Assertions.assertArrayEquals(result, new int[]{});
    }

    @Test
    public void testInvalidPreReqs() {
        int numberOfCourses = 4;
        int[][] preReqs = {{6,0},{7,6}};
        int[] result  = instance.getCourseOrder(numberOfCourses, preReqs);
        Assertions.assertArrayEquals(result, new int[]{});
    }

    @Test
    public void testValidInputs() {
        int numberOfCourses = 4;
        int[][] preReqs = {{1,0},{2,0},{3,1},{3,2}};
        int[] result  = instance.getCourseOrder(numberOfCourses, preReqs);
        Assertions.assertArrayEquals(result, new int[]{0,1,2,3});
    }
}
