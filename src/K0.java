public class K0 {
    public static int innerBytes(int wordIndex, int key, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 5)
                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 21);
        int a2 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15);
        int a3 = BitOperations.getBit(BitOperations.f(data.getL0() ^ data.getR0() ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    static int outerBytes(int wordIndex, int key, FEALData data) {
        BitOperations.splitPairs(wordIndex, data);
        int a1 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13);
        int a2 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 7)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 23)
                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 31);
        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key);
        int a3 = BitOperations.getBit(y0, 7) ^ BitOperations.getBit(y0, 15) ^ BitOperations.getBit(y0, 23)
                ^ BitOperations.getBit(y0, 31);

        return a1 ^ a2 ^ a3;
    }

    public static void calcK0(FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            int firstA1 = innerBytes(0, keyTilda, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != innerBytes(w1, keyTilda, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key0 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = outerBytes(0, key0, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != outerBytes(w2, key0, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                K1.calcK1(key0, data);
                        }
                    }
                }
            }
        }
    }
}
