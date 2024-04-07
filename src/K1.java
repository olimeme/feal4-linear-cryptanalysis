public class K1 {
    public static void calcK1(int key0, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            BitOperations.splitPairs(0, data);
            int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
            int firstA1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21)
                    ^ BitOperations.getBit(BitOperations.f(data.getL0() ^ y0 ^ keyTilda), 15);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                BitOperations.splitPairs(w1, data);
                y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
                int first = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                        ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                        ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21)
                        ^ BitOperations.getBit(BitOperations.f(data.getL0() ^ y0 ^ keyTilda), 15);
                if (firstA1 != first)
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key1 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        BitOperations.splitPairs(0, data);
                        y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
                        int y1 = BitOperations.f(data.getL0() ^ y0 ^ key1);
                        int firstA2 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                                ^ BitOperations.getBit(y1, 7) ^ BitOperations.getBit(y1, 15)
                                ^ BitOperations.getBit(y1, 23)
                                ^ BitOperations.getBit(y1, 31);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            BitOperations.splitPairs(w2, data);
                            y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
                            y1 = BitOperations.f(data.getL0() ^ y0 ^ key1);
                            int last = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                                    ^ BitOperations.getBit(y1, 7) ^ BitOperations.getBit(y1, 15)
                                    ^ BitOperations.getBit(y1, 23)
                                    ^ BitOperations.getBit(y1, 31);
                            if (firstA2 != last)
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
