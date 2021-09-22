package trie;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static trie.Edge.Base10ToBaseX.BASE16;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class TrieMap<K, V> implements Map<K, V> {

    public Edge<K, V> baseEdge = new PartialArrayEdge<>();

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

        int hash = key.hashCode();
        V result = baseEdge.put(key, value, hash);

        Edge<K, V> newEdge = baseEdge.ensureEfficientAccess(null, hash, 1);
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

interface Node<K, V> {}

class Vertex<K, V> implements Node<K, V> {

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

/**
 * Place holder (either linked or array based) for {@link Vertex} or {@link Edge} itself.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
abstract class Edge<K, V> implements Node<K, V> {

    // Support methods, implementation is overridden.
    abstract Node<K, V> getElement(int index);
    abstract Node<K, V> removeElement(int index);
    abstract void setElement(int index, Node<K, V> node);
    abstract Edge<K, V> ensureEfficientAccess(Edge<K, V> parent, int hash, int level);
    abstract int size();

    V get(Object key) {

        int hash = key.hashCode();
        Node<K, V> nodeAtIndex;
        Edge<K, V> edgeAtIndex = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = BASE16.getBaseXValueOnAtLevel(hash, level);
            nodeAtIndex = edgeAtIndex.getElement(index);
            if (nodeAtIndex == null) {

                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertex = (Vertex<K, V>) nodeAtIndex;
                for (; vertex != null; vertex = vertex.nextVertex) {
                    // vertex.hashCode() == hash is never needed as there is no collision of two different hashes
                    // shares same array index. and vertex.hashCode() == hash is always true.
                    if (vertex.key.equals(key)) {
                        return vertex.value;
                    }
                }
                return null;
            }
            edgeAtIndex = (Edge<K, V>) nodeAtIndex;
        }

        return null;
    }

    V put(K key, V value, int hash) {

        Node<K, V> nodeAtIndex;
        Edge<K, V> parentEdge = null;
        Edge<K, V> edgeAtLevel = this;

        //Iterate till maximum levels
        for (int level = 1, maxRotation = BASE16.getMaxRotation(); level <= maxRotation; level++) {

            int index = BASE16.getBaseXValueOnAtLevel(hash, level);
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
                Edge<K, V> newEdge = new PartialArrayEdge<>();
                edgeAtLevel.setElement(index, newEdge);
                edgeAtLevel = newEdge;

                level = level + 1;
                int newIndex = BASE16.getBaseXValueOnAtLevel(hash, level);
                int vertexIndex = BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
                while (vertexIndex == newIndex && level < maxRotation) {

                    newEdge = new PartialArrayEdge<>();
                    edgeAtLevel.setElement(newIndex, newEdge);
                    edgeAtLevel = newEdge;

                    level = level + 1;
                    newIndex = BASE16.getBaseXValueOnAtLevel(hash, level); //newVertex.key.hashCode()
                    vertexIndex = BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
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

        BASE16(15, 4, 8, 11, 8);

        private final int mask;
        private final int bitCount;
        private final int maxRotation;
        private final int linkToArraySize;
        private final int arrayToLinkSize;


        Base10ToBaseX(int levelZeroMask, int levelOneRotation,
                      int maxPossibleRotation, int linkToArraySize, int arrayToLinkSize) {

            this.mask = levelZeroMask;        // 111.. for masking
            this.bitCount = levelOneRotation; //Max no of bits touched
            this.maxRotation = maxPossibleRotation;
            this.linkToArraySize = linkToArraySize;
            this.arrayToLinkSize = arrayToLinkSize;
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

        public int getLinkToArraySize() {

            return linkToArraySize;
        }

        public int getArrayToLinkSize() {

            return arrayToLinkSize;
        }

        int getBaseXValueOnAtLevel(int on, int level) {

            int rotation = bitCount;
            int maskTill = mask;

            // May be the below if else works better than the code commented
            if (level > 1) {
                rotation = (level - 1) * rotation;
                maskTill = maskTill << rotation;
            } else {
                rotation = 0;
            }

            //            int rotation = (level - 1) * bitCount;
            //            int maskTill = mask << rotation;
            return (on & maskTill) >>> rotation;
        }
    }
}


class PartialArrayEdge<K, V> extends Edge<K, V> {

    int bitset;
    Node[] elements;
    private static int[] masks32 = {

            0x1,         0x3,          0x7,         0xF,
            0x1F,        0x3F,         0x7F,        0xFF,
            0x1FF,       0x3FF,        0x7FF,       0xFFF,
            0x1FFF,      0x3FFF,       0x7FFF,      0xFFFF,
            0x1FFFF,     0x3FFFF,      0x7FFFF,     0xFFFFF,
            0x1FFFFF,    0x3FFFFF,     0x7FFFFF,    0xFFFFFF,
            0x1FFFFFF,   0x3FFFFFF,    0x7FFFFFF,   0xFFFFFFF,
            0x1FFFFFFF,  0x3FFFFFFF,   0x7FFFFFFF,  0xFFFFFFFF
    };

    public PartialArrayEdge() { }

    @Override
    Node<K, V> getElement(int index) {

        if (!bitAt(index)) {
            // Not set
            return null;
        }

        if (elements.length == 16) {

            return elements[index];
        }

        return elements[elementCount(index) - 1];
    }

    private boolean bitAt(int bitIndex) {

        return ((bitset & (1 << bitIndex)) != 0);
    }

    private int elementCount() {

        return elementCount(31);
    }

    private void setBitAt(int bitIndex) {

        bitset |= (1 << bitIndex);
    }

    private void clearBitAt(int bitIndex) {

        bitset &= ~(1 << bitIndex);
    }

    /**
     * @param firstXBits inclusive, starts from 0
     * @return
     */
    public int elementCount(int firstXBits) {

        return Integer.bitCount(bitset & masks32[firstXBits]);
    }

    @Override
    void setElement(int index, Node<K, V> node) {

        int elementCount = elementCount(index);
        //        Update never happens, hence commented
        //        if (bitAt(index)) {
        //            elements[bitIndex] = node;
        //        }

        if (elements == null) {

            elements = new Node[1];
            elements[0] = node;
            setBitAt(index);
            return;
        }

        // handle setting lower index before existing set indexes
        int firstSetBit = nextSetBit(0);
        Node[] newElements = new Node[elements.length + 1];
        if (index < firstSetBit) {  // change <= to < and uncomment else if
            newElements[0] = node;
            System.arraycopy(elements, 0, newElements, 1, elements.length);

        } else { // index > firstSetBit

            System.arraycopy(elements, 0, newElements, 0, elementCount);
            newElements[elementCount] = node;
            if (elements.length > elementCount) {

                System.arraycopy(elements, elementCount, newElements, elementCount + 1, elements.length - elementCount);
            }
        }
        elements = newElements;
        setBitAt(index);
    }

    public int nextSetBit(int fromIndex) {

        int word = bitset & (masks32[31] << fromIndex);
        if (word != 0) { return Integer.numberOfTrailingZeros(word); };
        return -1;
    }

    @Override
    Edge<K, V> ensureEfficientAccess(Edge<K, V> parent, int hash, int level) {

        return null;
    }

    @Override
    int size() {

        return elements.length;
    }

    @Override
    Node<K, V> removeElement(int index) {

        if (elements.length == 1) {

            Node<K, V> result = (Node) elements[0];
            elements = null;
            clearBitAt(index);
            return result;
        }

        // elementCount cannot be less than 1, since index is referring existing element
        int elementCount = elementCount(index);
        Node<K, V> result = (Node) elements[elementCount - 1];
        Node[] newElements = new Node[elements.length - 1];
        System.arraycopy(elements, 0, newElements, 0, elementCount - 1);
        if (elements.length > elementCount) {

            System.arraycopy(elements, elementCount, newElements, elementCount - 1, elements.length - elementCount);
        }
        elements = newElements;
        clearBitAt(index);
        return result;
    }
}