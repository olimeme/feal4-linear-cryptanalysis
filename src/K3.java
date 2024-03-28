public class K3 {
    public static int innerBytes(int wordIndex, int key, int k0, int k1, int k2) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 5)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 21);
        int a2 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 15);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int y2 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ k2);
        int a3 = BitOperations.getBit(FEAL.f((int) FEALData.L0 ^ y0 ^ y2 ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    public static int outerBytes(int wordIndex, int k0, int k1, int k2, int k3) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13);
        int a2 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 7)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 15)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 23)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 31);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int y2 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ k2);
        int y3 = FEAL.f((int) FEALData.L0 ^ y0 ^ y2 ^ k3);
        int a3 = BitOperations.getBit(y3, 7) ^ BitOperations.getBit(y3, 15) ^ BitOperations.getBit(y3, 23)
                ^ BitOperations.getBit(y3, 31);

        return a1 ^ a2 ^ a3;
    }

    public static void solveForK3(int key0, int key1, int key2) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int key_tilda = BitOperations.get12BitKeyForInnerBytes(k1);
            int first_a1 = innerBytes(0, key_tilda, key0, key1, key2);

            for (int w1 = 1; w1 < FEALData.PAIRS; w1++) {
                if (first_a1 != innerBytes(w1, key_tilda, key0, key1, key2))
                    break;

                if (w1 == FEALData.PAIRS - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key3 = BitOperations.get20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = outerBytes(0, key0, key1, key2, key3);

                        for (int w2 = 1; w2 < FEALData.PAIRS; w2++) {
                            if (first_a2 != outerBytes(w2, key0, key1, key2, key3))
                                break;

                            if (w2 == FEALData.PAIRS - 1) {
                                BitOperations.validate(key0, key1, key2, key3);
                            }
                        }
                    }
                }
            }
        }
    }

}
