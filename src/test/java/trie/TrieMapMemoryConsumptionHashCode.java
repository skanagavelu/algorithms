package trie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import trie.Util.Sequence;
import static trie.Util.generateRandomString;

public class TrieMapMemoryConsumptionHashCode {

    public static void main(String[] args) {

        Set<Sequence> tokens = new HashSet<>();
        Set<Integer> hashes = new HashSet<>();

        int size = 1000;
        while (tokens.size() < size) {

            Sequence token = new Sequence(generateRandomString());
            tokens.add(token);
            if (hashes.contains(token.hashCode())) {
                System.out.println("hashcode already present:" + token);
            } else {
                hashes.add(token.hashCode());
            }
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

    public static void memoryUsedByTrieMap(Set<Sequence> tokens) {

        System.out.println("\n\n\n\n\n\n");
        System.gc();
        Set<Integer> hashes = new HashSet<>();
        Map<Sequence, Sequence> trieMap = new TrieMap<>();
        long before = memoryUsed();
        for (Sequence token : tokens) {

            if (!hashes.add(token.hashCode())) {
                System.out.println("hashcode already present:" + token);
            }
            trieMap.put(token, token);
            trieMap.get(token);
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
