package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * For a directed <B>acyclic graph</B>, do a topological sort on this graph.
 * This is same as DFS; only change is add stack.
 *
 * <p>Do DFS by keeping visited. Put the vertex which are
 * completely explored into a stack. Pop from
 * stack to get sorted order.
 *
 * <p>Space and time complexity is O(n).
 *
 * @author ksugumar
 */
public class TopologicalSort {

  public static void main(String args[]) {
    SimpleGraph<Integer> graph = new SimpleGraph<Integer>();
    graph.addEdge(1, 3, true);
    graph.addEdge(1, 2, true);
    graph.addEdge(3, 4, true);
    graph.addEdge(5, 6, true);
    graph.addEdge(6, 3, true);
    graph.addEdge(3, 8, true);
    graph.addEdge(8, 11, true);

    TopologicalSort g = new TopologicalSort();
    System.out.println(g.doTopologicalSort(graph));
  }

  /**
   * Note that DFS needs two iteration, one is recursive, another is for loop
   * This is needed we need to check all the
   * nodes are traversed. e.g) A ---> B <---- C
   *
   * <p>A has children B, if i choose only A then only A and B will be counted.
   * And C will be missed. So iterate through all the nodes, outer loop does this.
   *
   * @param graph
   */
  private <T> Stack<T> doTopologicalSort(SimpleGraph<T> graph) {
    Set<T> visited = new HashSet<T>();
    Stack<T> stack = new Stack();
    for (T vertexOuter : graph.getAllVertex()) {
      doTopologicalSortUtil(graph, vertexOuter, visited, stack);
    }
    return stack;
  }

  /*
   * Topological sort won't consistently work if there is a cycle
   * But wont go for loop since we maintain the visited set.
   */
  private <T> void doTopologicalSortUtil(
      SimpleGraph<T> graph, T vertex, Set<T> visited, Stack<T> stack) {
    if (!visited.add(vertex)) { // This is important Base condition
      return;
    }
    System.out.println("Node Visited: " + vertex);
    List<T> children = graph.getAdjacencyNodes(vertex);
    for (T child : children) {
      doTopologicalSortUtil(graph, child, visited, stack);
    }
    stack.push(vertex);
  }
}
