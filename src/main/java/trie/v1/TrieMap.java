package trie.v1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import trie.Bit32Set;

public class TrieMap<K, V> implements Map<K, V> {

    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    Lock readLock = rwLock.readLock();
    Lock writeLock = rwLock.writeLock();
    public Edge<K, V> baseEdge = new LinkedEdge<>();

    @Override
    public V get(Object key) {

        return baseEdge.get(key);

//        readLock.lock();
//        try {
//            return baseEdge.get(key);
//        } finally {
//            readLock.unlock();
//        }
    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    public boolean isEmpty() {

        return false;
    }

    @Override
    public boolean containsKey(Object key) {

        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public V put(K key, V value) {

        return baseEdge.put(key, value);

//        writeLock.lock();
//        try {
//            return baseEdge.put(key, value);
//        } finally {
//            writeLock.unlock();
//        }
    }

    @Override
    public V remove(Object key) {

        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {

        return null;
    }

    @Override
    public Collection<V> values() {

        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {

        return null;
    }

    public static void main(String[] args) {


    }

    @Override
    public String toString() {

        return "TrieMap{" +
               "baseEdge=" + baseEdge +
               '}';
    }
}

abstract class LinkedNode<K, V> {

    LinkedNode<K, V> next;

    public LinkedNode<K, V> getNext() {

        return next;
    }

    public void setNext(LinkedNode<K, V> node) {

        this.next = node;
    }
}

class Vertex<K, V> extends LinkedNode<K, V> {

    K key;
    volatile V val;
    volatile Vertex<K, V> nextVertex;

    public Vertex(K key, V val) {

        this.key = key;
        this.val = val;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Vertex<K, V> that = (Vertex<K, V>) obj;
        return this.key.equals(that.key);
    }

    @Override
    public int hashCode() {

        return key.hashCode();
    }

    @Override
    public String toString() {

        return key + "#" + val;
    }
}

abstract class Edge<K, V> extends LinkedNode<K, V> {

//    public Edge (Edge other) {
//
//    }
    public abstract V get(Object key);
    public abstract V put(K key, V value);
}

class LinkedEdge<K, V> extends Edge<K, V> {

    private Bit32Set bin;
    private LinkedNode<K, V> elements;

    public LinkedEdge() {
        
        bin = new Bit32Set();
    }

    private LinkedNode<K, V> getElement(int index) {

        if (!bin.get(index)) {
            // Not set
            return null;
        }
        if (index == 0) {

            // if bin is set for 0th index then it should be the first
            return this.elements;
        }

        int elementCount = bin.cardinality( index + 1);
        LinkedNode<K, V> element = this.elements;
        for (int i = 1; i < elementCount; i++) {

            element = element.getNext();
        }
        return element;
    }

    private void setElement(int index, LinkedNode<K, V> node) {

        bin.set(index);
        if (this.elements == null) {

            this.elements = node;
            return;
        }
        if (index <= bin.nextSetBit(0)) {

            node.setNext(this.elements);
            this.elements = node;
            return;
        }

        int elementCount = bin.cardinality(index);
        LinkedNode<K, V> element = this.elements;
        for (int i = 1; i < elementCount; i++) {

            element = element.getNext();
        }

        LinkedNode<K, V> tempNode = element.getNext();
        node.setNext(tempNode);
        element.setNext(node);
    }

    private LinkedNode<K, V> removeElement(int index) {

        bin.clear(index);
        LinkedNode<K, V> result;
        if (this.elements.next == null || index <= bin.nextSetBit(0)) {

            result = this.elements;
            this.elements = this.elements.next;
            result.next = null;
            return result;
        }

        int elementCount = bin.cardinality(index);
        LinkedNode<K, V> element = this.elements;
        for (int i = 1; i < elementCount; i++) {

            element = element.getNext();
        }

        LinkedNode<K, V> tempNode = element.getNext();
        element.setNext(tempNode.getNext());
        tempNode.setNext(null);
        return tempNode;
    }

    public V get(Object key) {


        int hash = key.hashCode();
        LinkedNode<K, V> nodeAtIndex;
        LinkedEdge<K, V> edgeAtIndex = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = Base10ToBaseX.BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, hash, level);
            nodeAtIndex = edgeAtIndex.getElement(index);
            if (nodeAtIndex == null) {

                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertex = (Vertex<K, V>) nodeAtIndex;
                for (; vertex != null; vertex = vertex.nextVertex) {
                    if (vertex.key.equals(key)) { //vertex.hashCode() == hash &&
                        return vertex.val;
                    }
                }
                return null;
            }
            edgeAtIndex = (LinkedEdge<K, V>) nodeAtIndex;
        }
        return null;
    }

    public V put(K key, V value) {

        int hash = key.hashCode();
        LinkedNode<K, V> nodeAtIndex;
        LinkedEdge<K, V> edgeAtLevel = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = Base10ToBaseX.BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, hash, level);
            nodeAtIndex = edgeAtLevel.getElement(index);
            if (nodeAtIndex == null) {

                edgeAtLevel.setElement(index, new Vertex<>(key, value));
                return null;
            } else if (nodeAtIndex instanceof Vertex) {


                Vertex<K, V> vertexAtIndex = (Vertex<K, V>) nodeAtIndex;
                if (vertexAtIndex.key.equals(key)) {

                    V oldValue = vertexAtIndex.val;
                    vertexAtIndex.val = value;
                    return oldValue;
                }
                
                int vertexAtIndexHash = vertexAtIndex.key.hashCode();
                if (vertexAtIndexHash == hash) { // Not same key but hash same

                    while(vertexAtIndex.nextVertex != null) {
                        vertexAtIndex = vertexAtIndex.nextVertex;
                    }
                    vertexAtIndex.nextVertex = new Vertex<>(key, value);
                    return null;
                }

                vertexAtIndex = (Vertex<K, V>) edgeAtLevel.removeElement(index);
                LinkedEdge<K, V> newEdge = new LinkedEdge<>();
                edgeAtLevel.setElement(index, newEdge);
                edgeAtLevel = newEdge;

                level = level + 1;
                int newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, hash, level);
                int vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, vertexAtIndexHash, level);
                while (vertexIndex == newIndex && level < maxRotation) {

                    newEdge = new LinkedEdge<>();
                    edgeAtLevel.setElement(newIndex, newEdge);
                    edgeAtLevel = newEdge;

                    level = level + 1;
                    newIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, hash, level); //newVertex.key.hashCode()
                    vertexIndex = Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.BASE16, vertexAtIndexHash, level);
                }

                if (level == maxRotation) {

                    Vertex<K, V> newVertex = new Vertex<>(key, value);
                    Vertex<K, V> temp = vertexAtIndex.nextVertex;
                    vertexAtIndex.nextVertex = newVertex;
                    newVertex.nextVertex = temp;
                    edgeAtLevel.setElement(newIndex, vertexAtIndex);
                } else {

                    edgeAtLevel.setElement(newIndex, new Vertex<>(key, value));
                    edgeAtLevel.setElement(vertexIndex, nodeAtIndex);
                }
                return null;
            } else {

                edgeAtLevel = (LinkedEdge<K, V>) nodeAtIndex;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        return "LinkedEdge{" +
               "next=" + next +
               ", bin=" + bin +
               ", elements=" + elements +
               '}';
    }


    private enum Base10ToBaseX {

        BASE16(15, 4, 8);

        private final int MASK;
        private final int BIT_COUNT;
        private final int MAX_ROTATION;

        Base10ToBaseX(int levelZeroMask, int levelOneRotation, int maxPossibleRotation) {

            this.MASK = levelZeroMask;        // 111.. for masking
            this.BIT_COUNT = levelOneRotation; //Max no of bits touched
            this.MAX_ROTATION = maxPossibleRotation;
        }

        int getLevelZeroMask() {

            return MASK;
        }

        int getLevelOneRotation() {

            return BIT_COUNT;
        }

        int getMaxRotation() {

            return MAX_ROTATION;
        }

        public static int getBaseXValueOnAtLevel(Base10ToBaseX base, int on, int level) {

            int rotation = base.getLevelOneRotation();
            int mask = base.getLevelZeroMask();

            if (level > 1) {
                rotation = (level - 1) * rotation;
                mask = mask << rotation;
            } else {
                rotation = 0;
            }
            return (on & mask) >>> rotation;
        }
    }
}

