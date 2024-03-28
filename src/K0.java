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

}
