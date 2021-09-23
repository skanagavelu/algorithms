package trie;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static trie.BitSet32Util.bitAt;
import static trie.BitSet32Util.elementCount;
import static trie.BitSet32Util.nextSetBit;
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
        return baseEdge.put(key, value, hash);
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
    Vertex<K, V> next;

    public Vertex(K key, V value) {

        this.key = key;
        this.value = value;
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
    abstract void setElement(int index, Node<K, V> node);
    abstract void updateElement(int index, Node<K, V> oldNode, Node<K, V> newNode);
    abstract Node<K, V> removeElement(int index);
    abstract int size();

    V get(Object key) {

        int hash = key.hashCode();
        Node<K, V> nodeAtIndex = this;
        int level = 0;
        //Iterate till maximum levels
        while(true) {

            nodeAtIndex = ((Edge<K, V>) nodeAtIndex).getElement(BASE16.getBaseXValueOnAtLevel(hash, ++level));
            if (nodeAtIndex == null) {

                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertex = (Vertex<K, V>) nodeAtIndex;
                for (; vertex != null; vertex = vertex.next) {
                    // vertex.hashCode() == hash is never needed as there is no collision of two different hashes
                    // shares same array index. and vertex.hashCode() == hash is always true.
                    if (vertex.key == key || vertex.key.equals(key)) {
                        return vertex.value;
                    }
                }
                return null;
            }
        }
    }

    V put(K key, V value, int hash) {

        Node<K, V> nodeAtIndex;
        Edge<K, V> edgeAtLevel = this;
        int level = 0;

        //Iterate till maximum levels
        while(true) {

            int index = BASE16.getBaseXValueOnAtLevel(hash, ++level);
            nodeAtIndex = edgeAtLevel.getElement(index);
            if (nodeAtIndex == null) {

                edgeAtLevel.setElement(index, new Vertex<>(key, value));
                return null;
            } else if (nodeAtIndex instanceof Vertex) {

                Vertex<K, V> vertexAtIndex = (Vertex<K, V>) nodeAtIndex;
                int vertexAtIndexHash = vertexAtIndex.key.hashCode();
                if (vertexAtIndexHash == hash) {

                    if (vertexAtIndex.key.equals(key)) {

                        V oldValue = vertexAtIndex.value;
                        vertexAtIndex.value = value;
                        return oldValue;
                    } else { // Not same key but hash same

                        Vertex<K, V> temp = vertexAtIndex.next;
                        vertexAtIndex.next = new Vertex<>(key, value);
                        vertexAtIndex.next.next = temp;
                        return null;
                    }
                }

                Edge<K, V> newEdge = new PartialArrayEdge<>();
                edgeAtLevel.updateElement(index, vertexAtIndex, newEdge);
                edgeAtLevel = newEdge;

                level = level + 1;
                int newIndex = BASE16.getBaseXValueOnAtLevel(hash, level);
                int vertexIndex = BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
                while (vertexIndex == newIndex) {

                    newEdge = new PartialArrayEdge<>();
                    edgeAtLevel.setElement(newIndex, newEdge);
                    edgeAtLevel = newEdge;

                    level = level + 1;
                    newIndex = BASE16.getBaseXValueOnAtLevel(hash, level); //newVertex.key.hashCode()
                    vertexIndex = BASE16.getBaseXValueOnAtLevel(vertexAtIndexHash, level);
                }

                edgeAtLevel.setElement(newIndex, new Vertex<>(key, value));
                edgeAtLevel.setElement(vertexIndex, nodeAtIndex);
                return null;
            } else {

                edgeAtLevel = (Edge<K, V>) nodeAtIndex;
            }
        }
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

            int rotation = bitCount;
            int maskTill = mask;

            if (level > 1) {
                rotation = (level - 1) * rotation;
                maskTill = maskTill << rotation;
            } else {
                rotation = 0;
            }
            return (on & maskTill) >>> rotation;
        }
    }
}


class PartialArrayEdge<K, V> extends Edge<K, V> {

    int bitset;
    Node[] elements;

    @Override
    Node<K, V> getElement(int index) {

        if (elements != null) {

            if (!bitAt(bitset, index)) {
                // Not set
                return null;
            }

            if (elements.length == 16) return elements[index];
            return elements[elementCount(bitset, index) - 1];
        }

        return null;
    }


    // Update never happens, only insert
    @Override
    void setElement(int index, Node<K, V> node) {

        if (elements == null) {

            elements = new Node[1];
            elements[0] = node;
            setBitAt(index);
            return;
        }

        // handle setting lower index before existing set indexes
        int firstSetBit = nextSetBit(bitset, 0);
        Node[] newElements = new Node[elements.length + 1];
        if (index < firstSetBit) {

            newElements[0] = node;
            System.arraycopy(elements, 0, newElements, 1, elements.length);
        } else { // index > firstSetBit

            int elementCount = elementCount(bitset, index);
            System.arraycopy(elements, 0, newElements, 0, elementCount);
            newElements[elementCount] = node;
            if (elements.length > elementCount) {

                System.arraycopy(elements, elementCount, newElements, elementCount + 1, elements.length - elementCount);
            }
        }
        elements = newElements;
        setBitAt(index);
    }

    @Override
    void updateElement(int index, Node<K, V> oldNode, Node<K, V> newNode) {

        elements[elementCount(bitset, index) - 1] = newNode;
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
        int elementCount = elementCount(bitset, index);
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

    @Override
    int size() {

        return elements.length;
    }

    private void setBitAt(int bitIndex) {

        bitset |= (1 << bitIndex);
    }

    private void clearBitAt(int bitIndex) {

        bitset &= ~(1 << bitIndex);
    }
}

class BitSet32Util {

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

    static int nextSetBit(int bitset, int fromIndex) {

        int word = bitset & (masks32[31] << fromIndex);
        if (word != 0) { return Integer.numberOfTrailingZeros(word); };
        return -1;
    }

    static boolean bitAt(int bitset, int bitIndex) {

        return ((bitset & (1 << bitIndex)) != 0);
    }

    /**
     * @param firstXBits inclusive, starts from 0
     * @return
     */
     static int elementCount(int bitset, int firstXBits) {

        return Integer.bitCount(bitset & masks32[firstXBits]);
    }
}