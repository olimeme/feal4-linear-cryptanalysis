public class BitManipulation {

    public static int getBit(int num, int n) {
        return (num >> (31 - n)) & 1;
    }

    public static int f(int num) {
        return (FEAL.f((num)));
    }

    public static int generate12BitKeyForInnerBytes(int k) {
        return (((k >> 6) & 0x3F) << 16) + ((k & 0x3F) << 8);
    }

    public static int generate20BitKeyForOuterBytes(int k, int key_tilda) {
        int a0 = (((k & 0xF) >> 2) << 6) + ((key_tilda >> 16) & 0xFF);
        int a1 = ((k & 0x3) << 6) + ((key_tilda >> 8) & 0xFF);
        int b0 = (k >> 12) & 0xFF;
        int b3 = (k >> 4) & 0xFF;
        int b1 = b0 ^ a0;
        int b2 = b3 ^ a1;

        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }

    static void splitPairs(int index, FEALData data) {
        data.setL0((int) Long.parseLong(data.getPlaintext()[index].substring(0, 8), 16));
        data.setR0((int) Long.parseLong(data.getPlaintext()[index].substring(8), 16));
        data.setL4((int) Long.parseLong(data.getCiphertext()[index].substring(0, 8), 16));
        data.setR4((int) Long.parseLong(data.getCiphertext()[index].substring(8), 16));
    }

    static int calcInnerBytesKey0(int wordIndex, int key, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 5)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 21);
        int a2 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15);
        int a3 = BitManipulation.getBit(BitManipulation.f(data.getL0() ^ data.getR0() ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    static int calcOuterBytesKey0(int wordIndex, int key, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13);
        int a2 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 7)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 23)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 31);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ key);
        int a3 = BitManipulation.getBit(y0, 7) ^ BitManipulation.getBit(y0, 15) ^ BitManipulation.getBit(y0, 23)
                ^ BitManipulation.getBit(y0, 31);

        return a1 ^ a2 ^ a3;
    }

    static int calcInnerBytesKey1(int wordIndex, int key, int k0, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int a2 = BitManipulation.getBit(BitManipulation.f(data.getL0() ^ y0 ^ key), 15);

        return a1 ^ a2;
    }

    static int calcOuterBytesKey1(int wordIndex, int k0, int k1, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitManipulation.f(data.getL0() ^ y0 ^ k1);
        int a2 = BitManipulation.getBit(y1, 7) ^ BitManipulation.getBit(y1, 15) ^ BitManipulation.getBit(y1, 23)
                ^ BitManipulation.getBit(y1, 31);

        return a1 ^ a2;
    }

    static int calcInnerBytesKey2(int wordIndex, int key, int k0, int k1, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 5)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 21);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitManipulation.f(data.getL0() ^ y0 ^ k1);
        int a2 = BitManipulation.getBit(BitManipulation.f(data.getL0() ^ data.getR0() ^ y1 ^ key), 15);

        return a1 ^ a2;
    }

    static int calcOuterBytesKey2(int wordIndex, int k0, int k1, int k2, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitManipulation.f(data.getL0() ^ y0 ^ k1);
        int y2 = BitManipulation.f(data.getL0() ^ data.getR0() ^ y1 ^ k2);
        int a2 = BitManipulation.getBit(y2, 7) ^ BitManipulation.getBit(y2, 15) ^ BitManipulation.getBit(y2, 23)
                ^ BitManipulation.getBit(y2, 31);

        return a1 ^ a2;
    }

    static int calcInnerBytesKey3(int wordIndex, int key, int k0, int k1, int k2, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                ^ BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21);
        int a2 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitManipulation.f(data.getL0() ^ y0 ^ k1);
        int y2 = BitManipulation.f(data.getL0() ^ data.getR0() ^ y1 ^ k2);
        int a3 = BitManipulation.getBit(BitManipulation.f(data.getL0() ^ y0 ^ y2 ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    static int calcOuterBytesKey3(int wordIndex, int k0, int k1, int k2, int k3, FEALData data) {
        splitPairs(wordIndex, data);
        int a1 = BitManipulation.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13);
        int a2 = BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 7)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 23)
                ^ BitManipulation.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 31);
        int y0 = BitManipulation.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitManipulation.f(data.getL0() ^ y0 ^ k1);
        int y2 = BitManipulation.f(data.getL0() ^ data.getR0() ^ y1 ^ k2);
        int y3 = BitManipulation.f(data.getL0() ^ y0 ^ y2 ^ k3);
        int a3 = BitManipulation.getBit(y3, 7) ^ BitManipulation.getBit(y3, 15) ^ BitManipulation.getBit(y3, 23)
                ^ BitManipulation.getBit(y3, 31);

        return a1 ^ a2 ^ a3;
    }

}
