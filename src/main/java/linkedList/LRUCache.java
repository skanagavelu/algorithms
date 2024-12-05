package linkedList;

import java.util.LinkedList;

public class LRUCache {
  public static void main(String[] args){
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4
  }

  static class Node {
    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }

    int key;
    int value;

    @Override
    public String toString() {
      return "[" + key + "," + value + ']';
    }
  }

  LinkedList<Node> cache = new LinkedList<>();
  int maxSize;

  @Override
  public String toString() {
    return cache.toString();
  }

  public LRUCache(int capacity) {
    this.maxSize = capacity;
  }

  public int get(int key) {
    
    for (Node node : cache) {
      if (node.key == key) {
        cache.remove(node);
        cache.addLast(node);
        return node.value;
      }
    }
    return -1;
  }

  public void put(int key, int value) {
    for (Node node : cache) {
      if (node.key == key) {
        node.value = value;
        cache.remove(node);
        cache.addLast(node);
        return;
      }
    }
    cache.addLast(new Node(key, value));
    if (cache.size() > maxSize) {
      cache.removeFirst();
    }
  }
}
