package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/** 23. Merge k Sorted Lists */
public class MergeKSortedLists {

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;

    PriorityQueue<ListNode> queue =
        new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));

    ListNode dummyHead = new ListNode(0);
    ListNode tail = dummyHead;

    // Add only head of the multiple ListNode
    for (ListNode node : lists) if (node != null) queue.add(node);


    while (!queue.isEmpty()) {
      tail.next = queue.poll();
      tail = tail.next;

      // Consume from the List where the poll() happened
      if (tail.next != null) queue.add(tail.next);
    }
    return dummyHead.next;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
