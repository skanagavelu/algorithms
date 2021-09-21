package trie;

import java.io.Serializable;

public class Bit32Set implements Cloneable, Serializable {

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

    private int bin = 0;

    public void flip(int bitIndex) {

        checkBitIndex(bitIndex);
        bin ^= (1 << bitIndex);
    }

    public void set(int bitIndex) {

        checkBitIndex(bitIndex);
        bin |= (1 << bitIndex);
    }

    public void clear(int bitIndex) {

        checkBitIndex(bitIndex);
        bin &= ~(1 << bitIndex);
    }

    public boolean get(int bitIndex) {

        checkBitIndex(bitIndex);
        return ((bin & (1 << bitIndex)) != 0);
    }

    public int cardinality() {

        return cardinality(31);
    }

    /**
     * @param firstXBits inclusive, starts from 0
     * @return
     */
    public int cardinality(int firstXBits) {

        checkBitIndex(firstXBits);
        return Integer.bitCount(bin & masks32[firstXBits]);
    }

    /**
     * Returns the index of the first bit that is set to true that occurs on or after the specified starting index.
     * If no such bit exists then -1 is returned.
     *
     * @param fromIndex inclusive
     * @return
     */
    public int nextSetBit(int fromIndex) {

        checkBitIndex(fromIndex);
        int word = bin & (masks32[31] << fromIndex);
        if (word != 0) { return Integer.numberOfTrailingZeros(word); };
        return -1;
    }

    private void checkBitIndex(int bitIndex) {

        if (bitIndex < 0 || bitIndex > 31)
            throw new IndexOutOfBoundsException("Index < 0 || Index > 31: " + bitIndex);
    }


    @Override
    public String toString() {

        return "Bit32Set{" +
               "bin=" + bin +
               " [binary =" + Integer.toBinaryString(bin) + "]" +
               '}';
    }
}
