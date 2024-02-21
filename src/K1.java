public class K1 {
    public static int calculateConstInnerBytesk1(int wordIndex, int key, int k0) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 5)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13)
                ^ BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 21);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int a2 = BitOperations.getBit(FEAL.f((int) FEALData.L0 ^ y0 ^ key), 15);

        return a1 ^ a2;
    }

    public static int calcConstOutteBytesk1(int wordIndex, int k0, int k1) {
        BitOperations.splitPairs(wordIndex);
        int a1 = BitOperations.getBit(FEALData.L0 ^ FEALData.L4 ^ FEALData.R4, 13);
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ k0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ k1);
        int a2 = BitOperations.getBit(y1, 7) ^ BitOperations.getBit(y1, 15) ^ BitOperations.getBit(y1, 23)
                ^ BitOperations.getBit(y1, 31);

        return a1 ^ a2;
    }

}
