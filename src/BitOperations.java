public class BitOperations {
    public static void splitPairs(int wordIndex) {
        FEALData.R0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(8), 16);
        FEALData.L0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(0, 8), 16);
        FEALData.R4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(8), 16);
        FEALData.L4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(0, 8), 16);
    }
}
