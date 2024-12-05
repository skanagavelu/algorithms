package cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * http://javarticles.com/2012/06/lfu-cache.html
 * http://stackoverflow.com/questions/17759560/what-is-the-difference-between-lru-and-lfu
 *
 * Dont use priority queue that degrades performance
 * Use Map for all the purposes
 */
public class LFU<K, V> {
    private final int capacity;
    private final Map<K, V> valueMap;          // Key -> Value
    private final Map<K, Integer> freqMap;    // Key -> Frequency
    private final TreeMap<Integer, LinkedHashSet<K>> freqBuckets; // Frequency -> Keys

    public LFU(int capacity) {
        this.capacity = capacity;
        this.valueMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.freqBuckets = new TreeMap<>();
    }

    // Get the value for a key
    public V get(K key) {
        if (!valueMap.containsKey(key)) {
            return null; // Key not found
        }
        increaseFrequency(key);
        return valueMap.get(key);
    }

    // Put a key-value pair into the cache
    public void put(K key, V value) {
        if (capacity == 0) return; // No capacity

        if (valueMap.containsKey(key)) {
            valueMap.put(key, value); // Update value
            increaseFrequency(key);  // Update frequency
            return;
        }

        if (valueMap.size() >= capacity) {
            evictLFU(); // Evict least frequently used element
        }

        // Add new key-value pair
        valueMap.put(key, value);
        freqMap.put(key, 1);
        freqBuckets.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }

    // Helper to increase the frequency of a key
    private void increaseFrequency(K key) {
        int freq = freqMap.get(key);
        freqMap.put(key, freq + 1);

        // Remove key from current frequency bucket
        freqBuckets.get(freq).remove(key);
        if (freqBuckets.get(freq).isEmpty()) {
            freqBuckets.remove(freq);
        }

        // Add key to next frequency bucket
        freqBuckets.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);
    }

    // Helper to evict the least frequently used element
    private void evictLFU() {
        Map.Entry<Integer, LinkedHashSet<K>> leastFreqEntry = freqBuckets.firstEntry();
        LinkedHashSet<K> keys = leastFreqEntry.getValue();

        K evictKey = keys.iterator().next(); // Get the first element
        keys.remove(evictKey);
        if (keys.isEmpty()) {
            freqBuckets.remove(leastFreqEntry.getKey());
        }

        valueMap.remove(evictKey);
        freqMap.remove(evictKey);
    }

    // Display the cache state (for debugging)
    public void printCache() {
        System.out.println("Cache: " + valueMap);
        System.out.println("Frequencies: " + freqMap);
        System.out.println("Frequency Buckets: " + freqBuckets);
    }

    public static void main(String[] args) {
        LFU<Integer, String> lfuCache = new LFU<>(3);

        lfuCache.put(1, "A");
        lfuCache.put(2, "B");
        lfuCache.put(3, "C");

        System.out.println("Get 1: " + lfuCache.get(1)); // Access key 1
        lfuCache.put(4, "D"); // Evicts key 2 (LFU)

        lfuCache.printCache();
    }
}
