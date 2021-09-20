//package trie;
//
//import java.util.BitSet;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//import trie.Base10ToBaseX.Base;
//
//public class TrieMap<K, V> implements Map<K, V> {
//
//    ReadWriteLock rwLock = new ReentrantReadWriteLock();
//    Lock readLock = rwLock.readLock();
//    Lock writeLock = rwLock.writeLock();
//    public Edge<K, V> baseEdge = new Edge<>(8);
//
//    @Override
//    public V get(Object key) {
//
//        return baseEdge.get(key);
//
////        readLock.lock();
////        try {
////            return baseEdge.get(key);
////        } finally {
////            readLock.unlock();
////        }
//    }
//
//    @Override
//    public int size() {
//
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//
//        return false;
//    }
//
//    @Override
//    public boolean containsKey(Object key) {
//
//        return false;
//    }
//
//    @Override
//    public boolean containsValue(Object value) {
//
//        return false;
//    }
//
//    @Override
//    public V put(K key, V value) {
//
//        return baseEdge.put(key, value);
//
////        writeLock.lock();
////        try {
////            return baseEdge.put(key, value);
////        } finally {
////            writeLock.unlock();
////        }
//    }
//
//    @Override
//    public V remove(Object key) {
//
//        return null;
//    }
//
//    @Override
//    public void putAll(Map m) {
//
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Set<K> keySet() {
//
//        return null;
//    }
//
//    @Override
//    public Collection<V> values() {
//
//        return null;
//    }
//
//    @Override
//    public Set<Entry<K, V>> entrySet() {
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//        Map<String, String> mm = new HashMap<>();
//        mm.put("hello", "hello");
//
//        TrieMap<String, String> map = new TrieMap<>();
//        map.toString();
//        map.put("hello", "world");
//        assert map.get("hello").equals("world");
//        map.put("hello", "replace world");
//        assert map.get("hello").equals("replace world");
//
//        map.put("hello_big_hash", "big hash world");
//        assert map.get("hello_big_hash").equals("big hash world");
//
//        map.put("he", "small hash world");
//        assert map.get("he").equals("small hash world");
//
//        map.put("AaAa", "hashCode-AaAa");
//        assert map.get("AaAa").equals("hashCode-AaAa");
//
//        map.put("BBBB", "hashCode-BBBB");
//        assert map.get("BBBB").equals("hashCode-BBBB");
//        assert map.get("hello").equals("replace world");
//    }
//
//    @Override
//    public String toString() {
//
//        return "TrieMap{" +
//               "baseEdge=" + baseEdge +
//               '}';
//    }
//}
//
//abstract class LinkedNode<K, V> {
//
//    LinkedNode<K, V> next;
//
//    public LinkedNode<K, V> getNext() {
//
//        return next;
//    }
//
//    public void setNext(LinkedNode<K, V> node) {
//
//        this.next = node;
//    }
//}
//
//class Vertex<K, V> extends LinkedNode<K, V> {
//
//    K key;
//    volatile V val;
//    volatile Vertex<K, V> nextVertex;
//
//    public Vertex(K key, V val) {
//
//        this.key = key;
//        this.val = val;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || this.getClass() != obj.getClass()) {
//            return false;
//        }
//        Vertex<K, V> that = (Vertex<K, V>) obj;
//        return this.key.equals(that.key);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return key.hashCode();
//    }
//
//    @Override
//    public String toString() {
//
//        return key + "#" + val;
//    }
//}
//
//class Edge<K, V> extends LinkedNode<K, V> {
//
////    volatile BitSet bin;
//    volatile Bit32Set bin;
//    volatile LinkedNode<K, V> elements;
//
//    public Edge(int size) {
//
////        bin = new BitSet(size);
//        bin = new Bit32Set();
//    }
//
//    public LinkedNode<K, V> getElement(int index) {
//
//        if (!bin.get(index)) {
//            // Not set
//            return null;
//        }
//        if (index == 0) {
//
//            // if bin is set for 0th index then it should be the first
//            return this.elements;
//        }
////        int elementCount = bin.get(0, index + 1).cardinality(); // +1 for exclusiveness
////        int elementCount = cardinality(bin, index + 1);
//        int elementCount = bin.cardinality( index + 1);
//
//
//        LinkedNode<K, V> element = this.elements;
//        for (int i = 1; i < elementCount; i++) {
////        index = index + 1;
////        int itr = -1;
////        while((itr = bin.nextSetBit(itr + 1)) < index && itr >= 0) {
//
//            element = element.getNext();
//        }
//        return element;
//    }
//
//    private int cardinality(BitSet bin, int till) {
//        int sum = 0;
//        int itr = -1;
//        while((itr = bin.nextSetBit(itr + 1)) < till && itr >= 0) {
//            sum++;
//        }
//        return sum;
//    }
//
//    public void setElement(int index, LinkedNode<K, V> node) {
//
//        bin.set(index);
//        if (this.elements == null) {
//
//            this.elements = node;
//            return;
//        }
//        if (index <= bin.nextSetBit(0)) {
//
//            node.setNext(this.elements);
//            this.elements = node;
//            return;
//        }
//
////        int elementCount = bin.get(0, index).cardinality();
////        int elementCount = cardinality(bin, index);
//        int elementCount = bin.cardinality(index);
//
//        LinkedNode<K, V> element = this.elements;
//        for (int i = 1; i < elementCount; i++) {
//            element = element.getNext();
//        }
//
//        LinkedNode<K, V> tempNode = element.getNext();
//        node.setNext(tempNode);
//        element.setNext(node);
//    }
//
//    public LinkedNode<K, V> removeElement(int index) {
//
//        bin.clear(index);
//        LinkedNode<K, V> result;
//        if (this.elements.next == null || index <= bin.nextSetBit(0)) {
//
//            result = this.elements;
//            this.elements = this.elements.next;
//            result.next = null;
//            return result;
//        }
//
////        int elementCount = bin.get(0, index).cardinality();
////        int elementCount = cardinality(bin, index);
//        int elementCount = bin.cardinality(index);
//
//        LinkedNode<K, V> element = this.elements;
//        for (int i = 1; i < elementCount; i++) {
//            element = element.getNext();
//        }
//
//        LinkedNode<K, V> tempNode = element.getNext();
//        element.setNext(tempNode.getNext());
//        tempNode.setNext(null);
//        return tempNode;
//    }
//
//    public V get(Object key) {
//
//
//        int hash = key.hashCode();
//        LinkedNode<K, V> nodeAtIndex;
//        Edge<K, V> edgeAtIndex = this;
//
//
//        //Iterate till maximum levels
//        for (int level = 1, maxRotation = Base.BASE16.getMaxRotation(); level <= maxRotation; level++) {
//
//            long start = System.nanoTime();
//            int index = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, hash, level);
//            nodeAtIndex = edgeAtIndex.getElement(index);
//            if (nodeAtIndex == null) {
//
//                return null;
//            } else if (nodeAtIndex instanceof Vertex) {
//
//                Vertex<K, V> vertex = (Vertex<K, V>) nodeAtIndex;
//                for (; vertex != null; vertex = vertex.nextVertex) {
//                    if ( vertex.key.equals(key)) { //vertex.hashCode() == hash &&
//                        return vertex.val;
//                    }
//                }
//                return null;
//            }
//            edgeAtIndex = (Edge<K, V>) nodeAtIndex;
//        }
//        return null;
//    }
//
//    public V put(K key, V value) {
//
//        int hash = key.hashCode();
//        Vertex<K, V> newVertex = new Vertex<>(key, value);
//        LinkedNode<K, V> nodeAtIndex;
//        Edge<K, V> edgeAtLevel = this;
//
//        //Iterate till maximum levels
//        for (int level = 1; level <= Base.BASE16.getMaxRotation(); level++) {
//
//            int index = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, hash, level);
//            nodeAtIndex = edgeAtLevel.getElement(index);
//            if (nodeAtIndex == null) {
//
//                edgeAtLevel.setElement(index, newVertex);
//                return null;
//            } else if (nodeAtIndex instanceof Vertex) {
//
//
//                Vertex<K, V> vertexAtIndex = (Vertex<K, V>) nodeAtIndex;
//                if (vertexAtIndex.key.equals(newVertex.key)) {
//
//                    V oldValue = vertexAtIndex.val;
//                    vertexAtIndex.val = newVertex.val;
//                    return oldValue;
//                }
//
//                vertexAtIndex = (Vertex<K, V>) edgeAtLevel.removeElement(index);
//                int vertexAtIndexHash = vertexAtIndex.key.hashCode();
//                Edge<K, V> newEdge = new Edge<>(8);
//                edgeAtLevel.setElement(index, newEdge);
//                edgeAtLevel = newEdge;
//
//                level = level + 1;
//                int newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, hash, level);
//                int vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, vertexAtIndexHash, level);
//                while (vertexIndex == newIndex && level < Base.BASE16.getMaxRotation()) {
//
//                    newEdge = new Edge<>(8);
//                    edgeAtLevel.setElement(newIndex, newEdge);
//                    edgeAtLevel = newEdge;
//
//                    level = level + 1;
//                    newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, hash, level); //newVertex.key.hashCode()
//                    vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE16, vertexAtIndexHash, level);
//                }
//
//                if (level == Base.BASE16.getMaxRotation()) {
//
//                    Vertex<K, V> temp = vertexAtIndex.nextVertex;
//                    vertexAtIndex.nextVertex = newVertex;
//                    newVertex.nextVertex = temp;
//                    edgeAtLevel.setElement(newIndex, vertexAtIndex);
//                } else {
//
//                    edgeAtLevel.setElement(newIndex, newVertex);
//                    edgeAtLevel.setElement(vertexIndex, nodeAtIndex);
//                }
//                return null;
//            } else {
//
//                edgeAtLevel = (Edge<K, V>) nodeAtIndex;
//            }
//        }
//        return null;
//    }
//
////    public V remove(Object key) {
////
////        Vertex<K, V> newVertex = new Vertex<>(key, value);
////        LinkedNode<K, V> nodeAtIndex;
////        Edge<K, V> edgeAtLevel = this;
////
////        //Iterate till maximum levels
////        for (int level = 1; level <= Base.BASE256.getMaxRotation(); level++) {
////
////            int index = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE256, key.hashCode(), level);
////            nodeAtIndex = edgeAtLevel.getElement(index);
////            if (nodeAtIndex == null) {
////
////                edgeAtLevel.setElement(index, newVertex);
////                return null;
////            } else if (nodeAtIndex instanceof Vertex) {
////
////
////                Vertex<K, V> vertexAtIndex = (Vertex<K, V>) nodeAtIndex;
////                if (vertexAtIndex.key.equals(newVertex.key)) {
////
////                    V oldValue = vertexAtIndex.val;
////                    vertexAtIndex.val = newVertex.val;
////                    return oldValue;
////                }
////
////                vertexAtIndex = (Vertex<K, V>) edgeAtLevel.removeElement(index);
////                Edge<K, V> newEdge = new Edge<>(8);
////                edgeAtLevel.setElement(index, newEdge);
////                edgeAtLevel = newEdge;
////
////                level = level + 1;
////                int newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE256, newVertex.key.hashCode(), level);
////                int vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE256, vertexAtIndex.key.hashCode(), level);
////                while (vertexIndex == newIndex && level < Base.BASE256.getMaxRotation()) {
////
////                    newEdge = new Edge<>(8);
////                    edgeAtLevel.setElement(newIndex, newEdge);
////                    edgeAtLevel = newEdge;
////
////                    level = level + 1;
////                    newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE256, newVertex.key.hashCode(), level);
////                    vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE256, vertexAtIndex.key.hashCode(), level);
////                }
////
////                if (level == Base.BASE256.getMaxRotation()) {
////
////                    Vertex<K, V> temp = vertexAtIndex.nextVertex;
////                    vertexAtIndex.nextVertex = newVertex;
////                    newVertex.nextVertex = temp;
////                    edgeAtLevel.setElement(newIndex, vertexAtIndex);
////                } else {
////
////                    edgeAtLevel.setElement(newIndex, newVertex);
////                    edgeAtLevel.setElement(vertexIndex, nodeAtIndex);
////                }
////                return null;
////            } else {
////
////                edgeAtLevel = (Edge<K, V>) nodeAtIndex;
////            }
////        }
////        return null;
////    }
//
//    @Override
//    public String toString() {
//
//        return "Edge{" +
//               "next=" + next +
//               ", bin=" + bin +
//               ", elements=" + elements +
//               '}';
//    }
//}
//
//class Base10ToBaseX {
//
//    public enum Base {
//        /**
//         * Integer is represented in 32 bit in 32 bit machine.
//         * There we can split this integer no of bits into multiples of 1,2,4,8,16 bits
//         */
//        BASE2(1, 1, 32),
//        BASE4(3, 2, 16),
//        BASE8(7, 3, 11),
//        BASE16(15, 4, 8) {
//            public String getFormattedValue(int val) {
//
//                switch (val) {
//                    case 10:
//                        return "A";
//                    case 11:
//                        return "B";
//                    case 12:
//                        return "C";
//                    case 13:
//                        return "D";
//                    case 14:
//                        return "E";
//                    case 15:
//                        return "F";
//                    default:
//                        return "" + val;
//                }
//            }
//        }, /*BASE32(31,5,1),*/
//        BASE256(255, 8, 4), /*BASE512(511,9),*/
//        Base65536(65535, 16, 2);
//
//        private final int MASK;
//        private final int BIT_COUNT;
//        private final int MAX_ROTATION;
//
//        Base(int levelZeroMask, int levelOneRotation, int maxPossibleRotation) {
//
//            this.MASK = levelZeroMask;        // 111.. for masking
//            this.BIT_COUNT = levelOneRotation; //Max no of bits touched
//            this.MAX_ROTATION = maxPossibleRotation;
//        }
//
//        int getLevelZeroMask() {
//
//            return MASK;
//        }
//
//        int getLevelOneRotation() {
//
//            return BIT_COUNT;
//        }
//
//        int getMaxRotation() {
//
//            return MAX_ROTATION;
//        }
//    }
//
//    /**
//     *
//     */
//    public static int getBaseXValueOnAtLevel(Base base, int on, int level) {
//
////        if (level > base.getMaxRotation() || level < 1) {
////            return 0; //INVALID Input
////        }
//        int rotation = base.getLevelOneRotation();
//        int mask = base.getLevelZeroMask();
//
//        if (level > 1) {
//            rotation = (level - 1) * rotation;
//            mask = mask << rotation;
//        } else {
//            rotation = 0;
//        }
//        return (on & mask) >>> rotation;
//    }
//
//    public static void main(String[] args) {
//
//        System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE4, 235, 1)); //1110 1011 = 235
//        System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE4, 235, 2));
//        System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE4, 235, 3));
//        System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE4, 235, 4));
//        System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base.BASE4, 235, 5));
//    }
//}
