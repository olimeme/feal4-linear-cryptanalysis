public class K2 {
    public static int calcConstInnerBytesk2(int wordIndex, int key, int k0, int k1) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 5)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 13)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 21);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int a2 = BitOperations.getBit(FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ key), 15);

        return a1 ^ a2;
    }

    public static int calcConstOutteBytesk2(int wordIndex, int k0, int k1, int k2) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.R0 ^ FEALData.L4, 13);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int y2 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ k2);
        int a2 = BitOperations.getBit(y2, 7) ^ BitOperations.getBit(y2, 15) ^ BitOperations.getBit(y2, 23)
                ^ BitOperations.getBit(y2, 31);

        return a1 ^ a2;
    }
}
