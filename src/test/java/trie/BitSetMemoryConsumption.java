package trie;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static trie.Util.generateRandomString;

public class BitSetMemoryConsumption {

    public static void main(String[] args) {

//        memoryUsedByHashMap(20000);
                memoryUsedByTrieMap(20000);

        /*
            memoryUsedByHashMap: used 3,154,512 bytes for size 50000
            memoryUsedByTrieMap: used 1,341,936 bytes for size 50000

            memoryUsedByTrieMap: used 514,160 bytes for size 20000
            memoryUsedByHashMap: used 1,057,360 bytes for size 20000
         */
    }

    public static void memoryUsedByTrieMap(int size) {

        List<Bit32Set> list = new ArrayList<>();
        long before = memoryUsed();
        for (int i = 0; i < size; i++) {
            list.add(new Bit32Set());
        }

        long used = memoryUsed() - before;
        if (used == 0) {
            throw new AssertionError("You need to run this with -XX:-UseTLAB for accurate accounting");
        }
        System.out.printf("memoryUsedByTrieMap: used %,d bytes for size %d %n", used, size);

        //        System.out.println(ClassLayout.parseInstance(trieMap).toPrintable());
    }

    public static void memoryUsedByHashMap(int size) {

        List<BitSet> list = new ArrayList<>();
        long before = memoryUsed();
        for (int i = 0; i < size; i++) {
            list.add(new BitSet());
        }

        long used = memoryUsed() - before;
        if (used == 0) {
            throw new AssertionError("You need to run this with -XX:-UseTLAB for accurate accounting");
        }
        System.out.printf("memoryUsedByHashMap: used %,d bytes for size %d %n", used, size);

        //        System.out.println(ClassLayout.parseInstance(hashMap).toPrintable());

    }

    public static long memoryUsed() {

        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
