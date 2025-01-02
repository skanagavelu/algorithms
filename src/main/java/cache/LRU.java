package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Use DoublyLinkedList and HashMap
 * So whenever node is accessed then push it to tail of the queue,
 * So the head will have LeastRecentlyAccessed.
 */
public class LRU {

    private LinkedHashMap<Integer,Integer> cache;
    public LRU(int capacity) {
        this.cache = new LinkedLRUMap(capacity);
    }

    public int get(int key) {
        Integer val = cache.get(key);
        return val == null ? -1 : val;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }

    class LinkedLRUMap extends LinkedHashMap<Integer, Integer> {

        int capacity;
        LinkedLRUMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
            return size() > capacity;
        }
    }
}
