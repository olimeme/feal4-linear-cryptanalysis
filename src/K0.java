public class K0 {
    public static void calcK0(FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            BitOperations.splitPairs(0, data);
            int firstA1 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 5)
                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 21)
                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                    ^ BitOperations.getBit(BitOperations.f(data.getL0() ^ data.getR0() ^ keyTilda), 15);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                BitOperations.splitPairs(w1, data);
                int first = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 5)
                        ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                        ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 21)
                        ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                        ^ BitOperations.getBit(BitOperations.f(data.getL0() ^ data.getR0() ^ keyTilda), 15);
                if (firstA1 != first)
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key0 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        BitOperations.splitPairs(0, data);
                        int y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
                        int firstA2 = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 7)
                                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 23)
                                ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 31)
                                ^ BitOperations.getBit(y0, 7) ^ BitOperations.getBit(y0, 15)
                                ^ BitOperations.getBit(y0, 23)
                                ^ BitOperations.getBit(y0, 31);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            BitOperations.splitPairs(w2, data);
                            y0 = BitOperations.f(data.getL0() ^ data.getR0() ^ key0);
                            int last = BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 13)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 7)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 15)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 23)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 31)
                                    ^ BitOperations.getBit(y0, 7) ^ BitOperations.getBit(y0, 15)
                                    ^ BitOperations.getBit(y0, 23)
                                    ^ BitOperations.getBit(y0, 31);
                            if (firstA2 != last)
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
