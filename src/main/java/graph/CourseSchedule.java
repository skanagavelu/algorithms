package graph;

import java.util.ArrayList;

/**
 * 207. Course Schedule
 *
 * Example 1:
 *
 * <p>Input: numCourses = 2, prerequisites = [[1,0]] Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0.
 * So it is possible.
 *
 * Example 2:
 *
 * <p>Input: numCourses = 2, prerequisites = [[1,0],[0,1]] Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take
 * course 0 you should also have finished course 1. So it is impossible.
 *
 * Detect Cycle in Directed Graph Algorithm
 * https://www.youtube.com/watch?v=rKQaZuoUR4M
 */
public class CourseSchedule {

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Create Array of lists -> adjecency matrix of graph
    ArrayList<Integer>[] adj = new ArrayList[numCourses];

    // Fill all the nodes (0 to numCourses - 1) as array index holding newly created arraylists
    for (int i = 0; i < numCourses; i++) {
      adj[i] = new ArrayList<>();
    }

    // Fill the arraylists of each nodes with their outgoing edges/connected nodes
    for (int[] pre : prerequisites) {
      adj[pre[0]].add(pre[1]);
    }

    // Define an array of visited (0 -> unvisited, 1 -> visited, 2 -> completed), initially filled
    // with 0's
    int[] visited = new int[numCourses];

    // Do DFS for each of the array nodes to check a cycle
    for (int i = 0; i < numCourses; i++) {
      if (!dfs(i, visited, adj)) return false;
    }

    return true;
  }

  public boolean dfs(int node, int[] visited, ArrayList<Integer>[] adj) {
    // Return false if the node is visited and viewed again before completion
    if (visited[node] == 1) {
      return false;
    }

    // Return true if the node is completed processing
    if (visited[node] == 2) {
      return true;
    }

    // Mark the node as visited/in-progress
    visited[node] = 1;

    // DFS of all the other nodes current "node" is connected to
    for (int n : adj[node]) {
      if (!dfs(n, visited, adj)) return false;
    }

    // If no more other nodes for the current "node" mark as completed and return true
    visited[node] = 2;

    return true;
  }
}
