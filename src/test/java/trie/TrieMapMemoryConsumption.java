package trie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static trie.Util.generateRandomString;

public class TrieMapMemoryConsumption {

    public static void main(String[] args) {

        Set<String> tokens = new HashSet<>();
        int size = 100000;
        while (tokens.size() < size) {

            String token = generateRandomString();
            tokens.add(token);
        }

//        memoryUsedByHashMap(tokens);
                memoryUsedByTrieMap(tokens);

        /*
            memoryUsedByHashMap: used 536,128 bytes for size 10000
            memoryUsedByTrieMap: used 1,008,728 bytes for size 10000


            memoryUsedByTrieMap: used 505,392 bytes for size 10000

            memoryUsedByHashMap: used 3,649,080 bytes for size 50000
            memoryUsedByTrieMap: used 2,097,136 bytes for size 50000
         */
    }

    public static void memoryUsedByTrieMap(Set<String> tokens) {

        Map<String, String> trieMap = new TrieMap<>();
        long before = memoryUsed();
        for (String token : tokens) {

            trieMap.put(token, token);
        }

        long used = memoryUsed() - before;
        if (used == 0) {
            throw new AssertionError("You need to run this with -XX:-UseTLAB for accurate accounting");
        }
        System.out.printf("memoryUsedByTrieMap: used %,d bytes for size %d %n", used, tokens.size());

        //        System.out.println(ClassLayout.parseInstance(trieMap).toPrintable());
    }

    public static void memoryUsedByHashMap(Set<String> tokens) {

        Map<String, String> hashMap = new ConcurrentHashMap<>();
        long before = memoryUsed();
        for (String token : tokens) {

            hashMap.put(token, token);
        }

        long used = memoryUsed() - before;
        if (used == 0) {
            throw new AssertionError("You need to run this with -XX:-UseTLAB for accurate accounting");
        }
        System.out.printf("memoryUsedByHashMap: used %,d bytes for size %d %n", used, tokens.size());

        //        System.out.println(ClassLayout.parseInstance(hashMap).toPrintable());

    }

    public static long memoryUsed() {

        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
