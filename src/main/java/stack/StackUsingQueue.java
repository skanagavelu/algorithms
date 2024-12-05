package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class StackUsingQueue {
    private Queue<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.add(x);
        // Every time We add, move the added element at Head
        // This makes stack
        for (int i=1; i<queue.size(); i++)
            queue.add(queue.remove());
    }

    public void pop() {
        queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
