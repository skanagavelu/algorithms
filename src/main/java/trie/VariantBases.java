package trie;

import java.util.HashMap;

public class VariantBases {

    Base10ToBaseX.Base[] bases = null;

    VariantBases(Base10ToBaseX.Base[] bases){
        this.bases = bases;
    }

    public  int getBaseXValueOnAtLevel(int on, int level) {

        if(level > bases.length || level < 1) {
            return 0; //INVALID Input
        }

        int rotation = 0;
        int mask = bases[level-1].getLevelZeroMask();

        for(int i = 1; i < level; i++) {

            int localrotation = bases[i-1].getLevelOneRotation();
            mask = mask << localrotation;
            rotation += localrotation;
        }

        return (on & mask) >>> rotation;
    }

    public static void main(String[] args) {
        Base10ToBaseX.Base[] bases = {Base10ToBaseX.Base.Base65536, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
        VariantBases vb = new VariantBases(bases);
        System.out.println(vb.getBaseXValueOnAtLevel(235, 1)); //1110 1011 = 235
        System.out.println(vb.getBaseXValueOnAtLevel(235, 2));
        System.out.println(vb.getBaseXValueOnAtLevel(235, 3));
        System.out.println(vb.getBaseXValueOnAtLevel(235, 4));
        System.out.println(vb.getBaseXValueOnAtLevel(235, 5));
//        System.out.println(vb.getBaseXValueOnAtLevel(1550201624, 1));
//        System.out.println(vb.getBaseXValueOnAtLevel(1550201624, 2));
//        System.out.println(vb.getBaseXValueOnAtLevel(1550201624, 3));
//        System.out.println(vb.getBaseXValueOnAtLevel(1550201624, 4));
    }

    public int getLevelZeroMask(int i) {
        return bases[i-1].getLevelZeroMask();
    }

    private static class Base10ToBaseX {

        public enum Base {
            /**
             * Integer is represented in 32 bit in 32 bit machine.
             * There we can split this integer no of bits into multiples of 1,2,4,8,16 bits
             */
            BASE2(1, 1, 32),
            BASE4(3, 2, 16),
            BASE8(7, 3, 11),
            BASE16(15, 4, 8) {
                public String getFormattedValue(int val) {

                    switch (val) {
                        case 10:
                            return "A";
                        case 11:
                            return "B";
                        case 12:
                            return "C";
                        case 13:
                            return "D";
                        case 14:
                            return "E";
                        case 15:
                            return "F";
                        default:
                            return "" + val;
                    }
                }
            }, /*BASE32(31,5,1),*/
            BASE256(255, 8, 4), /*BASE512(511,9),*/
            Base65536(65535, 16, 2);

            private final int MASK;
            private final int BIT_COUNT;
            private final int MAX_ROTATION;

            Base(int levelZeroMask, int levelOneRotation, int maxPossibleRotation) {

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
        }

        /**
         *
         */
        public static int getBaseXValueOnAtLevel(Base10ToBaseX.Base base, int on, int level) {

            //        if (level > base.getMaxRotation() || level < 1) {
            //            return 0; //INVALID Input
            //        }
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

        public static void main(String[] args) {

            System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.Base.BASE4, 235, 1)); //1110 1011 = 235
            System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.Base.BASE4, 235, 2));
            System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.Base.BASE4, 235, 3));
            System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.Base.BASE4, 235, 4));
            System.out.println(Base10ToBaseX.getBaseXValueOnAtLevel(Base10ToBaseX.Base.BASE4, 235, 5));
        }
    }
    
}
