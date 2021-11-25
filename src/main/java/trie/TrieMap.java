package trie;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static trie.BitSet16Util.bitAt;
import static trie.BitSet16Util.elementCount;
import static trie.BitSet16Util.nextSetBit;
/**
 * An object that maps key to value.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class TrieMap<K, V> implements Map<K, V> {

    public PartialArrayEdge<K, V> baseEdge = new PartialArrayEdge<>();

    @Override
    public V get(Object key) {

        int hash = key.hashCode();
        Node<K, V> node = baseEdge;
        int level = 0;

        //Iterate till maximum levels
        while(true) {

            node = ((PartialArrayEdge<K, V>) node).getElement(get4BitsAtLevel(hash, ++level));
            if (node == null) {

                return null;
            } else if (node.getClass() == Vertex.class) {

                Vertex<K, V> vertex = (Vertex<K, V>) node;
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

    @Override
    public V put(K key, V value) {

        int hash = key.hashCode();
        Node<K, V> nodeAtIndex;
        PartialArrayEdge<K, V> edgeAtLevel = baseEdge;
        int level = 0;

        //Iterate till maximum levels
        while(true) {

            int index = get4BitsAtLevel(hash, ++level);
            nodeAtIndex = edgeAtLevel.getElement(index);
            if (nodeAtIndex == null) {

                edgeAtLevel.setElement(index, new Vertex<>(key, value));
                return null;
            } else if (nodeAtIndex.getClass() == Vertex.class) {

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

                PartialArrayEdge<K, V> newEdge = new PartialArrayEdge<>();
                edgeAtLevel.updateElement(index, vertexAtIndex, newEdge);
                edgeAtLevel = newEdge;


                level = level + 1;
                int newIndex = get4BitsAtLevel(hash, level);
                int vertexIndex = get4BitsAtLevel(vertexAtIndexHash, level);
                while (vertexIndex == newIndex) {

                    newEdge = new PartialArrayEdge<>();
                    edgeAtLevel.setElement(newIndex, newEdge);
                    edgeAtLevel = newEdge;

                    level = level + 1;
                    newIndex = get4BitsAtLevel(hash, level); //newVertex.key.hashCode()
                    vertexIndex = get4BitsAtLevel(vertexAtIndexHash, level);
                }

                edgeAtLevel.setElement(newIndex, new Vertex<>(key, value));
                edgeAtLevel.setElement(vertexIndex, nodeAtIndex);
                return null;
            } else {

                edgeAtLevel = (PartialArrayEdge<K, V>) nodeAtIndex;
            }
        }
    }

    @Override
    public V remove(Object key) {

        return null; //baseEdge.removeElement(key.hashCode());
    }

    @Override
    public int size() { return 0; }

    @Override
    public boolean isEmpty() { return false; }

    @Override
    public boolean containsKey(Object key) { return false; }

    @Override
    public boolean containsValue(Object value) { return false; }

    @Override
    public void putAll(Map m) {}

    @Override
    public void clear() {}

    @Override
    public Set<K> keySet() { return null; }

    @Override
    public Collection<V> values() { return null; }

    @Override
    public Set<Entry<K, V>> entrySet() { return null; }

    public static void main(String[] args) {
    }

    @Override
    public String toString() {
        return "TrieMap{" +
               "baseEdge=" + baseEdge +
               '}';
    }

    private static int get4BitsAtLevel(int on, int level) {

        int rotation = 4;
        int maskTill = 15;

        if (level > 1) {
            rotation = (level - 1) * rotation;
            maskTill = maskTill << rotation;
        } else {
            rotation = 0;
        }
        return (on & maskTill) >>> rotation;
    }
}

interface Node<K, V> {}

class Leaf<K, V> implements Node<K, V> {
    K key;
    V value;

    public Leaf(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "#" + value;
    }
}

class Vertex<K, V> extends Leaf<K, V> {

    Vertex<K, V> next;

    public Vertex(K key, V value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return key + "#" + value;
    }
}


class PartialArrayEdge<K, V> implements Node<K, V> {

    int bitset;
    Node<K, V>[] elements;

    Node<K, V> getElement(int index) {

        if (elements != null) {

            if (elements.length == 16) return elements[index];

            if (!bitAt(bitset, index)) {
                // Not set
                return null;
            }
            return elements[elementCount(bitset, index) - 1];
        }
        return null;
    }


    // Update never happens, only insert
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

    void updateElement(int index, Node<K, V> oldNode, Node<K, V> newNode) {

        elements[elementCount(bitset, index) - 1] = newNode;
    }

    @SuppressWarnings("unchecked")
    Node<K, V> removeElement(int index) {

        if (elements.length == 1) {

            Node<K, V> result = (Node<K, V>) elements[0];
            elements = null;
            clearBitAt(index);
            return result;
        }

        // elementCount cannot be less than 1, since index is referring existing element
        int elementCount = elementCount(bitset, index);
        Node<K, V> result = (Node<K, V>) elements[elementCount - 1];
        Node<K, V>[] newElements = new Node[elements.length - 1];
        System.arraycopy(elements, 0, newElements, 0, elementCount - 1);
        if (elements.length > elementCount) {

            System.arraycopy(elements, elementCount, newElements, elementCount - 1, elements.length - elementCount);
        }
        elements = newElements;
        clearBitAt(index);
        return result;
    }

    int size() { return elements.length; }

    private void setBitAt(int bitIndex) { bitset |= (1 << bitIndex); }

    private void clearBitAt(int bitIndex) { bitset &= ~(1 << bitIndex); }
}

class BitSet16Util {

    private static int[] masks16 = {
            0x1,         0x3,          0x7,         0xF,
            0x1F,        0x3F,         0x7F,        0xFF,
            0x1FF,       0x3FF,        0x7FF,       0xFFF,
            0x1FFF,      0x3FFF,       0x7FFF,      0xFFFF
    };

    static int nextSetBit(int bitset, int fromIndex) {
        int word = bitset & (0xFFFF << fromIndex); //max only 16 bits were set
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
        return Integer.bitCount(bitset & masks16[firstXBits]);
    }
}