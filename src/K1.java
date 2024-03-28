public class K1 {
    public static int innerBytes(int wordIndex, int key, int k0) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 5)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 21);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int a2 = BitOperations.getBit(FEAL.f((int) FEALData.L0 ^ y0 ^ key), 15);

        return a1 ^ a2;
    }

    public static int outterBytes(int wordIndex, int k0, int k1) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int a2 = BitOperations.getBit(y1, 7) ^ BitOperations.getBit(y1, 15) ^ BitOperations.getBit(y1, 23)
                ^ BitOperations.getBit(y1, 31);

        return a1 ^ a2;
    }

    public static void solveForK1(int k) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int key_tilda = BitOperations.get12BitKeyForInnerBytes(k1);
            int first_a1 = innerBytes(0, key_tilda, k);

            for (int w1 = 1; w1 < FEALData.PAIRS; w1++) {
                if (first_a1 != innerBytes(w1, key_tilda, k))
                    break;

                if (w1 == FEALData.PAIRS - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key1 = BitOperations.get20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = outterBytes(0, k, key1);

                        for (int w2 = 1; w2 < FEALData.PAIRS; w2++) {
                            if (first_a2 != outterBytes(w2, k, key1))
                                break;

                            if (w2 == FEALData.PAIRS - 1) {
                                K2.solveForK2(k, key1);
                            }
                        }
                    }
                }
            }
        }
    }

}
