public class K3 {
    static int innerBytes(int wordIndex, int key, int k0, int k1, int k2, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21);
        int a2 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15);
        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitOperations.f(data.getL0() ^ y0 ^ k1);
        int y2 = BitOperations.f(data.getL0() ^ data.getR0() ^ y1 ^ k2);
        int a3 = BitOperations.getBit(BitOperations.f(data.getL0() ^ y0 ^ y2 ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    static int outerBytes(int wordIndex, int k0, int k1, int k2, int k3, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13);
        int a2 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 7)
                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 23)
                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 31);
        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitOperations.f(data.getL0() ^ y0 ^ k1);
        int y2 = BitOperations.f(data.getL0() ^ data.getR0() ^ y1 ^ k2);
        int y3 = BitOperations.f(data.getL0() ^ y0 ^ y2 ^ k3);
        int a3 = BitOperations.getBit(y3, 7) ^ BitOperations.getBit(y3, 15) ^ BitOperations.getBit(y3, 23)
                ^ BitOperations.getBit(y3, 31);

        return a1 ^ a2 ^ a3;
    }

    public static void calcK3(int key0, int key1, int key2, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            int firstA1 = innerBytes(0, keyTilda, key0, key1, key2, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != innerBytes(w1, keyTilda, key0, key1, key2, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key3 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = outerBytes(0, key0, key1, key2, key3, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != outerBytes(w2, key0, key1, key2, key3, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                BitOperations.validate(key0, key1, key2, key3, data);
                        }
                    }
                }
            }
        }
    }
}
