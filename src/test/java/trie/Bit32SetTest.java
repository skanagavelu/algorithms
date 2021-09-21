package trie;

import java.util.BitSet;

import junit.framework.TestCase;
import org.junit.Test;

public class Bit32SetTest extends TestCase {

    @Test
    public void testValidateSetAndGet() {

        BitSet bitSet = new BitSet();
        Bit32Set bit32Set = new Bit32Set();

        bit32Set.set(0);
        bitSet.set(0);
        assertEquals(bit32Set.get(0), bitSet.get(0));

        bit32Set.set(3);
        bitSet.set(3);
        assertEquals(bit32Set.get(3), bitSet.get(3));

        bit32Set.flip(1);
        bitSet.flip(1);
        assertEquals(bit32Set.get(1), bitSet.get(1));

        bit32Set.clear(1);
        bitSet.clear(1);
        assertEquals(bit32Set.get(1), bitSet.get(1));

        bit32Set.flip(1);
        bitSet.flip(1);
        assertEquals(bit32Set.get(1), bitSet.get(1));


        assertEquals(bit32Set.cardinality(), bitSet.cardinality());

        assertEquals(bit32Set.nextSetBit(6), bitSet.nextSetBit(6));
        assertEquals(bit32Set.nextSetBit(0), bitSet.nextSetBit(0));
        assertEquals(bit32Set.nextSetBit(2), bitSet.nextSetBit(2));


        bit32Set.clear(1);
        bitSet.clear(1);
        assertEquals(bit32Set.get(1), bitSet.get(1));

        assertEquals(bit32Set.cardinality(), bitSet.cardinality());
    }

    @Test
    public void testCardinalityUpto() {

        Bit32Set bit32Set = new Bit32Set();
        bit32Set.set(0);
        assertEquals(bit32Set.cardinality(3), 1);
        bit32Set.set(1);
        assertEquals(bit32Set.cardinality(3), 2);
        bit32Set.set(2);
        assertEquals(bit32Set.cardinality(3), 3);
        bit32Set.clear(2);
        assertEquals(bit32Set.cardinality(3), 2);
    }

    @Test
    public void testNextSetBit() {

        Bit32Set bit32Set = new Bit32Set();
        bit32Set.set(0);
        assertEquals(bit32Set.nextSetBit(0), 0);

        bit32Set.clear(0);
        bit32Set.set(1);
        assertEquals(bit32Set.nextSetBit(0), 1);

        bit32Set.clear(0);
        bit32Set.clear(1);
        bit32Set.set(2);
        assertEquals(bit32Set.nextSetBit(0), 2);


        bit32Set.set(0);
        bit32Set.set(1);
        bit32Set.set(2);
        assertEquals(bit32Set.nextSetBit(0), 0);
        assertEquals(bit32Set.nextSetBit(1), 1);
        assertEquals(bit32Set.nextSetBit(2), 2);
    }


}