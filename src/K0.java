public class K0 {
    public static int innerBytes(int i, int keyTilda) {
        BitOperations.splitPairs(i);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 13);
        int a2 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 7)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 15)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 23)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 31);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ keyTilda);
        int a3 = BitOperations.getBit(y0, 7) ^ BitOperations.getBit(y0, 15) ^ BitOperations.getBit(y0, 23)
                ^ BitOperations.getBit(y0, 31);

        return a1 ^ a2 ^ a3;
    }

    public static int outterBytes(int wordIndex, int key) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 5)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 13)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 21);
        int a2 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 15);
        int a3 = BitOperations.getBit(FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ key), 15);

        return a1 ^ a2 ^ a3;
    }

    public static void solveForK0() {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitOperations.get12BitKeyForInnerBytes(k1);
            int firstA1 = K0.innerBytes(0, keyTilda);

            System.out.println(FEALData.L0 + " " + FEALData.R0 + " " + FEALData.L4 + " " + FEALData.R4);

            for (int w1 = 1; w1 < FEALData.PAIRS; w1++) {
                // System.out.println("running loop for value " + w1);
                if (firstA1 != K0.innerBytes(w1, keyTilda))
                    break;

                if (w1 == FEALData.PAIRS - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key0 = BitOperations.get20BitKeyForOutterBytes(k2, keyTilda);
                        int firstA2 = K0.outterBytes(0, key0);

                        for (int w2 = 1; w2 < FEALData.PAIRS; w2++) {
                            if (firstA2 != K0.outterBytes(w2, key0))
                                break;

                            if (w2 == FEALData.PAIRS - 1)
                                K1.solveForK1(key0);
                        }
                    }
                }
            }
        }
    }
}
