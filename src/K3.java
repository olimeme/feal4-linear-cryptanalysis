public class K3 {
    public static int calcConstInnerBytesk3(int wordIndex, int key, int k0, int k1, int k2) {
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

    public static int calConstOutteBytesk3(int wordIndex, int k0, int k1, int k2, int k3) {
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

}
