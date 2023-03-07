package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class CourseOrder {

    public static void main(String[] args) {

        int numberOfCourses = 4;
        int[][] preReqs = {{1,0},{2,0},{3,1},{3,2}};
        CourseOrder instance = new CourseOrder();
        System.out.println(Arrays.toString(instance.getCourseOrder(numberOfCourses, preReqs)));
    }

    public int[] getCourseOrder(int numberOfCourses, int[][] preReqs) {

        Map<Integer, PreRequiredCourses> courseDependencies = buildCourseDependencies(numberOfCourses, preReqs);
        if (courseDependencies == null) {
            return new int[]{};
        }

        LinkedHashSet<Integer> courseOrder = new LinkedHashSet<>();
        for (int courseNumber : courseDependencies.keySet()) {
            buildCourseOrder(courseNumber, courseDependencies, courseOrder);
        }

        return courseOrder.stream().mapToInt(i->i).toArray();
    }

    /*
     * From preReqs {{1,0},{2,0},{3,1},{3,2}}
     * To CourseDependencies [{1,[0]},{2,[0]},{3,[1, 2]}}
     *
     * returns null when preReqs a, b < 0 || a, b > n
     *              when preReqs contains duplicates
     *              when numberOfCourses <= 1
     */
    private static Map<Integer, PreRequiredCourses> buildCourseDependencies(int numberOfCourses, int[][] preReqs) {

        if (numberOfCourses <= 1) {
            return null;
        }

        // Build pre required courses
        Map<Integer, PreRequiredCourses> courseDependencies = new HashMap<>();
        for (int[] courseDependency : preReqs) {

            int course = courseDependency[0];
            int reqCourse = courseDependency[1];
            if (course < 0 || reqCourse < 0 ||
                course > numberOfCourses || reqCourse > numberOfCourses) {
                // Invalid preReqs entry
                return null;
            }
            courseDependencies.computeIfAbsent(course,  k -> new PreRequiredCourses());
            PreRequiredCourses requiredCourses = courseDependencies.get(course);
            if (requiredCourses.requiredCourses.contains(reqCourse)) {
                // Duplicate preReqs entry
                return null;
            } else {
                requiredCourses.requiredCourses.add(reqCourse);
            }
        }
        return courseDependencies;
    }

    private static void buildCourseOrder(int courseNumber, Map<Integer, PreRequiredCourses> courseDependencies,
                                         LinkedHashSet<Integer> courseOrder) {

        if (courseOrder.contains(courseNumber)) {
            return;
        }
        PreRequiredCourses preRequiredCourses = courseDependencies.get(courseNumber);
        if (preRequiredCourses == null) {
            courseOrder.add(courseNumber);
            return;
        }
        if (!preRequiredCourses.visited) {
            preRequiredCourses.visited = true;
            List<Integer> preReqCourses = preRequiredCourses.requiredCourses;
            for (int course : preReqCourses) {
                buildCourseOrder(course, courseDependencies, courseOrder);
            }
            courseOrder.add(courseNumber);
        }
    }

    private static class PreRequiredCourses {
        boolean visited = false;
        final List<Integer> requiredCourses = new ArrayList<>();
    }
}
