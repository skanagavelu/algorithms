package trie;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TrieMap<K, V> implements Map<K, V> {

    public Edge<K, V> baseEdge = new LinkedEdge<>();

    @Override
    public V get(Object key) {

        return baseEdge.get(key);
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

        V result = baseEdge.put(key, value);

        Edge<K, V> newEdge = baseEdge.ensureEfficientAccess(null, key.hashCode(), 1);
        if (newEdge != null) {

            baseEdge = newEdge;
        }

        return result;
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
    V value;
    Vertex<K, V> nextVertex;

    public Vertex(K key, V value) {

        this.key = key;
        this.value = value;
    }

    @Override
    @SuppressWarnings("unchecked")
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

        return key + "#" + value;
    }
}

abstract class Edge<K, V> extends LinkedNode<K, V> {

    abstract LinkedNode<K, V> getElement(int index);
    abstract LinkedNode<K, V> removeElement(int index);
    abstract void setElement(int index, LinkedNode<K, V> node);
    abstract Edge<K, V> ensureEfficientAccess(Edge<K, V> parent, int hash, int level);
    abstract int size();

    V get(Object key) {

        int hash = key.hashCode();
        LinkedNode<K, V> nodeAtIndex;
        Edge<K, V> edgeAtIndex = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = Base10ToBaseX.BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level);
            nodeAtIndex = edgeAtIndex.getElement(index);
            if (nodeAtIndex == null) {

                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertex = (Vertex<K, V>) nodeAtIndex;
                for (; vertex != null; vertex = vertex.nextVertex) {
                    if (vertex.key.equals(key)) { //vertex.hashCode() == hash &&
                        return vertex.value;
                    }
                }
                return null;
            }
            edgeAtIndex = (Edge<K, V>) nodeAtIndex;
        }

        return null;
    }

    V put(K key, V value) {

        int hash = key.hashCode();
        LinkedNode<K, V> nodeAtIndex;
        Edge<K, V> parentEdge = null;
        Edge<K, V> edgeAtLevel = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = Base10ToBaseX.BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level);
            nodeAtIndex = edgeAtLevel.getElement(index);
            if (nodeAtIndex == null) {

                edgeAtLevel.setElement(index, new Vertex<>(key, value));
                if (level > 1) edgeAtLevel.ensureEfficientAccess(parentEdge, hash, level - 1);
                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertexAtIndex = (Vertex<K, V>) nodeAtIndex;
                if (vertexAtIndex.key.equals(key)) {

                    V oldValue = vertexAtIndex.value;
                    vertexAtIndex.value = value;
                    return oldValue;
                }

                int vertexAtIndexHash = vertexAtIndex.key.hashCode();
                if (vertexAtIndexHash == hash) { // Not same key but hash same

                    Vertex<K, V> temp = vertexAtIndex.nextVertex;
                    vertexAtIndex.nextVertex = new Vertex<>(key, value);
                    vertexAtIndex.nextVertex.nextVertex = temp;

                    return null;
                }

                vertexAtIndex = (Vertex<K, V>) edgeAtLevel.removeElement(index);
                Edge<K, V> newEdge = new LinkedEdge<>();
                edgeAtLevel.setElement(index, newEdge);
                edgeAtLevel = newEdge;

                level = level + 1;
                int newIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level);
                int vertexIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
                while (vertexIndex == newIndex && level < maxRotation) {

                    newEdge = new LinkedEdge<>();
                    edgeAtLevel.setElement(newIndex, newEdge);
                    edgeAtLevel = newEdge;

                    level = level + 1;
                    newIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level); //newVertex.key.hashCode()
                    vertexIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
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
                    if (level > 1) edgeAtLevel.ensureEfficientAccess(parentEdge, hash, level - 1);
                }
                return null;
            } else {

                parentEdge = edgeAtLevel;
                edgeAtLevel = (Edge<K, V>) nodeAtIndex;
            }
        }
        return null;
    }

    protected static enum Base10ToBaseX {

        BASE16(15, 4, 8);

        private final int mask;
        private final int bitCount;
        private final int maxRotation;

        Base10ToBaseX(int levelZeroMask, int levelOneRotation, int maxPossibleRotation) {

            this.mask = levelZeroMask;        // 111.. for masking
            this.bitCount = levelOneRotation; //Max no of bits touched
            this.maxRotation = maxPossibleRotation;
        }

        int getLevelZeroMask() {

            return mask;
        }

        int getBitCount() {

            return bitCount;
        }

        int getMaxRotation() {

            return maxRotation;
        }

        int getBaseXValueOnAtLevel(int on, int level) {

            int rotation = getBitCount();
            int mask = getLevelZeroMask();

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

class LinkedEdge<K, V> extends Edge<K, V> {

    private Bit32Set bin;
    private LinkedNode<K, V> elements;

    public LinkedEdge() {

        bin = new Bit32Set();
    }

    LinkedNode<K, V> getElement(int index) {

        if (!bin.get(index)) {
            // Not set
            return null;
        }
        if (index == 0) {

            // if bin is set for 0th index then it should be the first
            return this.elements;
        }

        int elementCount = bin.cardinality(index + 1);
        LinkedNode<K, V> element = this.elements;
        for (int i = 1; i < elementCount; i++) {

            element = element.getNext();
        }
        return element;
    }

    void setElement(int index, LinkedNode<K, V> node) {

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

    @Override
    Edge<K, V> ensureEfficientAccess(Edge<K, V> parent, int hash, int level) {

        if (size() > 11) {

            ArrayEdge<K, V> newEdge = new ArrayEdge<>();
            int itr = -1;
            LinkedNode<K, V> previous;
            while((itr = bin.nextSetBit(itr + 1)) >= 0) {
                newEdge.setElement(itr, elements);
                previous = elements;
                elements = elements.next;
                previous.next = null;
            }
            if (parent != null) {

                int parentIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level);
                parent.setElement(parentIndex, newEdge);
            }
            newEdge.next = this.next; // This has to be fixed at parent.setElement(parentIndex, newEdge); with index 0
            return newEdge;
        }
        return null;
    }

    @Override
    int size() {

        return bin.cardinality();
    }

    LinkedNode<K, V> removeElement(int index) {

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

    @Override
    public String toString() {

        return "LinkedEdge{" +
               "next=" + next +
               ", bin=" + bin +
               ", elements=" + elements +
               '}';
    }
}

class ArrayEdge<K, V> extends Edge<K, V> {

    LinkedNode[] elements; //This is needed to ensure array elements are volatile
    int size;

    public ArrayEdge() {

        elements = new LinkedNode[16];
    }

    @Override
    LinkedNode<K, V> getElement(int index) {

        return elements[index];
    }

    @Override
    void setElement(int index, LinkedNode<K, V> node) {

        elements[index] = node;
        ++size;
    }

    @Override
    Edge<K, V> ensureEfficientAccess(Edge<K, V> parent, int hash, int level) {

        if (size < 8) {

            LinkedEdge<K, V> newEdge = new LinkedEdge<>();
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] != null) {
                    newEdge.setElement(i, elements[i]);
                }
            }
            if (parent != null) {

                int parentIndex = Base10ToBaseX.BASE16.getBaseXValueOnAtLevel(hash, level);
                parent.setElement(parentIndex, newEdge);
            }
            return newEdge;
        }
        return null;
    }

    @Override
    int size() {

        return size;
    }

    @Override
    LinkedNode<K, V> removeElement(int index) {

        LinkedNode<K, V> result = (LinkedNode<K, V>) elements[index];
        elements[index] = null;
        --size;
        return result;
    }
}


