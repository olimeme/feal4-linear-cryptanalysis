public class BitOperations {
    public static void splitPairs(int wordIndex) {
        FEALData.R0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(8), 16);
        FEALData.L0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(0, 8), 16);
        FEALData.R4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(8), 16);
        FEALData.L4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(0, 8), 16);
    }

    public static int getBit(long word, int bitIndex) {
        return (int) ((word >> bitIndex) & 1);
    }

    public static int get12BitKeyForInnerBytes(int k1) {
        return (((k1 >> 6) & 0x3F) << 16) + ((k1 & 0x3F) << 8);
    }

    public static int get20BitKeyForOutterBytes(int k2, int keyTilda) {
        int a0 = (((k2 & 0xF) >> 2) << 6) + ((keyTilda >> 16) & 0xFF);
        int a1 = ((k2 & 0x3) << 6) + ((keyTilda >> 8) & 0xFF);
        int b0 = (k2 >> 12) & 0xFF;
        int b3 = (k2 >> 4) & 0xFF;
        int b1 = b0 ^ a0;
        int b2 = b3 ^ a1;

        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }
}
