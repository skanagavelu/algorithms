package dynamicProgramming;

/**
 * https://www.youtube.com/watch?v=Y0lT9Fck7qI
 * https://leetcode.com/problems/climbing-stairs/
 *
 *
 * Number of ways to reach the end goal when there are possible choices
 * Mostly choices are part of decision tree
 * For every given level there are all possibility of choices
 * At each node will represent how many steps we reached so far by choosing that choice at parent level
 * if path reached goal or went beyond the goal at any node, that is the base case to return
 *
 * Good think is we will get sub problem at every node which can be memomised
 *
 */
public class ClimbingStairs {

}
