package trie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import trie.Util.Sequence;
import static trie.Util.generateRandomString;

public class TrieMapMemoryConsumption {

    public static void main(String[] args) {

        Set<Sequence> tokens = new HashSet<>();
        int size = 50000;
        while (tokens.size() < size) {

            Sequence token = new Sequence(generateRandomString());
            tokens.add(token);
        }

//        memoryUsedByHashMap(tokens);
                memoryUsedByTrieMap(tokens);

        /*
            memoryUsedByHashMap: used 2,451,968 bytes for size 50000
            memoryUsedByTrieMap: used 1,981,032 bytes for size 50000
            470.935 KB
         */
    }

    public static void memoryUsedByTrieMap(Set<Sequence> tokens) {

        System.gc();
        Map<Sequence, Sequence> trieMap = new TrieMap<>();
        long before = memoryUsed();
        for (Sequence token : tokens) {

            trieMap.put(token, token);
        }

        System.gc();
        long used = memoryUsed() - before;
        if (used == 0) {
            throw new AssertionError("You need to run this with -XX:-UseTLAB for accurate accounting");
        }
        System.out.printf("memoryUsedByTrieMap: used %,d bytes for size %d %n", used, tokens.size());

        //        System.out.println(ClassLayout.parseInstance(trieMap).toPrintable());
    }

    public static void memoryUsedByHashMap(Set<Sequence> tokens) {

        System.gc();
        Map<Sequence, Sequence> hashMap = new ConcurrentHashMap<>();
        long before = memoryUsed();
        for (Sequence token : tokens) {

            hashMap.put(token, token);
        }

        System.gc();
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
