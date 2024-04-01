public class K1 {
    public static int innerBytes(int wordIndex, int key, int k0, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21);
        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ k0);
        int a2 = BitOperations.getBit(BitOperations.f(data.getL0() ^ y0 ^ key), 15);

        return a1 ^ a2;
    }

    public static int outerBytes(int wordIndex, int k0, int k1, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13);
        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ k0);
        int y1 = BitOperations.f(data.getL0() ^ y0 ^ k1);
        int a2 = BitOperations.getBit(y1, 7) ^ BitOperations.getBit(y1, 15) ^ BitOperations.getBit(y1, 23)
                ^ BitOperations.getBit(y1, 31);

        return a1 ^ a2;
    }

    public static void calcK1(int key0, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            int firstA1 = innerBytes(0, keyTilda, key0, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != innerBytes(w1, keyTilda, key0, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key1 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = outerBytes(0, key0, key1, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != outerBytes(w2, key0, key1, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                K2.calcK2(key0, key1, data);
                        }
                    }
                }
            }
        }
    }
}
