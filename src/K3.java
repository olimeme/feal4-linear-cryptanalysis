public class K3 {
    public static void calcK3(int key0, int key1, int key2, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.generate12BitKeyForInnerBytes(k1);
            BitOperations.splitPairs(0, data);
            int y0 = FEAL.f(data.getL0() ^ data.getR0() ^ key0);
            int y1 = FEAL.f(data.getL0() ^ y0 ^ key1);
            int y2 = FEAL.f(data.getL0() ^ data.getR0() ^ y1 ^ key2);
            int firstA1 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                    ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21)
                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                    ^ BitOperations.getBit(FEAL.f(data.getL0() ^ y0 ^ y2 ^ keyTilda), 15);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                BitOperations.splitPairs(w1, data);
                y0 = FEAL.f(data.getL0() ^ data.getR0() ^ key0);
                y1 = FEAL.f(data.getL0() ^ y0 ^ key1);
                y2 = FEAL.f(data.getL0() ^ data.getR0() ^ y1 ^ key2);
                int first = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 5)
                        ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                        ^ BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 21)
                        ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                        ^ BitOperations.getBit(FEAL.f(data.getL0() ^ y0 ^ y2 ^ keyTilda), 15);
                if (firstA1 != first)
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key3 = BitOperations.generate20BitKeyForOuterBytes(k2, keyTilda);
                        BitOperations.splitPairs(0, data);
                        y0 = FEAL.f(data.getL0() ^ data.getR0() ^ key0);
                        y1 = FEAL.f(data.getL0() ^ y0 ^ key1);
                        y2 = FEAL.f(data.getL0() ^ data.getR0() ^ y1 ^ key2);
                        int y3 = FEAL.f(data.getL0() ^ y0 ^ y2 ^ key3);

                        int firstA2 = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 7)
                                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 23)
                                ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 31)
                                ^ BitOperations.getBit(y3, 7) ^ BitOperations.getBit(y3, 15)
                                ^ BitOperations.getBit(y3, 23)
                                ^ BitOperations.getBit(y3, 31);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            BitOperations.splitPairs(w2, data);
                            y0 = FEAL.f(data.getL0() ^ data.getR0() ^ key0);
                            y1 = FEAL.f(data.getL0() ^ y0 ^ key1);
                            y2 = FEAL.f(data.getL0() ^ data.getR0() ^ y1 ^ key2);
                            y3 = FEAL.f(data.getL0() ^ y0 ^ y2 ^ key3);

                            int last = BitOperations.getBit(data.getL0() ^ data.getL4() ^ data.getR4(), 13)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 7)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 15)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 23)
                                    ^ BitOperations.getBit(data.getL0() ^ data.getR0() ^ data.getL4(), 31)
                                    ^ BitOperations.getBit(y3, 7) ^ BitOperations.getBit(y3, 15)
                                    ^ BitOperations.getBit(y3, 23)
                                    ^ BitOperations.getBit(y3, 31);

                            if (firstA2 != last)
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
